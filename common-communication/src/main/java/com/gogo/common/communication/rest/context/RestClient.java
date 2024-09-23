package com.gogo.common.communication.rest.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.model.common.domain.config.ServiceUrlConfig;
import com.gogo.model.common.domain.dto.AbstractDto;
import com.gogo.model.common.domain.dto.ResponseDto;
import com.gogo.model.common.domain.exception.NotImplementedException;
import com.gogo.model.common.domain.util.CommonUtil;
import com.gogo.model.common.domain.util.LogUtil;
import com.gogo.model.common.domain.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.gogo.model.common.domain.constants.SecurityConstants.AUTHORIZATION_TAG;
import static com.gogo.model.common.domain.constants.SecurityConstants.PREFIX_BEARER;

/**
 * Rest client encapsulating rest template to make rest calls
 */
@Component
public class RestClient {

    private final RestTemplate restTemplate;

    @Autowired
    protected ServiceUrlConfig serviceUrlConfig;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Use this as much
     */
    public <T> ResponseEntity<T> get(Class<T> responseType, String url, HttpServletRequest request) {
        return execute(responseType, url, createRequestEntity(request), HttpMethod.GET);
    }

    public <T> ResponseEntity<T> get(ParameterizedTypeReference<T> responseType, String url, HttpServletRequest request) {
        return executeRequest(() -> restTemplate.exchange(url, HttpMethod.GET, createRequestEntity(request), responseType),
                responseType.getType());
    }

    ///////////////

    public <T> ResponseEntity<T> get(Class<T> responseType, String url, HttpEntity<?> request) {
        return execute(responseType, url, request, HttpMethod.GET);
    }

    public <T> ResponseEntity<T> post(Class<T> responseType, String url, HttpEntity<?> request) {
        return execute(responseType, url, request, HttpMethod.POST);
    }

    public <T> ResponseEntity<T> get(ParameterizedTypeReference<T> responseType, String url, HttpEntity<?> request) {
        return executeRequest(() -> restTemplate.exchange(url, HttpMethod.GET, request, responseType),
                responseType.getType());
    }

    public <T> ResponseEntity<T> post(ParameterizedTypeReference<T> responseType, String url, HttpEntity<?> request) {
        return executeRequest(() -> restTemplate.exchange(url, HttpMethod.POST, request, responseType),
                responseType.getType());
    }

    private <T> ResponseEntity<T> execute(Class<T> responseType, String url, HttpEntity<?> request, HttpMethod method) {
        if (HttpMethod.GET.equals(method)) {
            return executeRequest(() -> restTemplate.exchange(url, HttpMethod.GET, request, responseType), responseType);
        } else if (HttpMethod.POST.equals(method)) {
            return executeRequest(() -> restTemplate.exchange(url, HttpMethod.POST, request, responseType), responseType);
        }
        throw new RuntimeException("Not implemented execute() for method : " + method.name());
    }

    private <T> ResponseEntity<T> executeRequest(RequestExecutor<ResponseEntity<T>> executor, Type responseType) {
        try {
            return executor.execute();
        } catch (HttpClientErrorException ex) {
            LogUtil.logError(ex.getMessage());
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (HttpServerErrorException ex) {
            LogUtil.logError(ex.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (Exception ex) {
            LogUtil.logError(ex.getMessage());
            throw ex;
        }
    }

    /**
     * Handle HttpClientError Exception
     **/
    private <T> ResponseEntity<T> handleHttpClientErrorException(HttpClientErrorException ex, Type responseType) {
        HttpStatusCode statusCode = ex.getStatusCode();
        String message = ex.getResponseBodyAsString();
        if (statusCode == HttpStatus.NOT_FOUND) {
            LogUtil.logError("Resource not found: " + ex.getRawStatusCode());
        } else if (statusCode == HttpStatus.UNAUTHORIZED) {
            LogUtil.logError("Unauthorized request: " + ex.getRawStatusCode());
        } else {
            LogUtil.logError("HTTP error: " + ex.getRawStatusCode());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            T responseBody = objectMapper.convertValue(message, objectMapper.constructType(responseType));
            ResponseDto responseDto = ex.getResponseBodyAs(ResponseDto.class);
            if (responseDto != null) {
                ((ResponseDto) responseBody).setMessageNum(responseDto.getMessageNum());
                ((ResponseDto) responseBody).setMessageText("Client Error - " + responseDto.getMessageText());
                ((ResponseDto) responseBody).setHttpStatusCode(String.valueOf(statusCode.value()));
            } else {
                //message = message.substring(0,100);
                ((ResponseDto) responseBody).setMessageNum(responseDto.getMessageNum());
                ((ResponseDto) responseBody).setMessageText("Client Error - " + statusCode);
                ((ResponseDto) responseBody).setHttpStatusCode(String.valueOf(statusCode.value()));
            }
            return ResponseEntity.status(statusCode).body(responseBody);
        } catch (Exception e) {
            LogUtil.logError("Error deserializing response body: " + e.getMessage());
        }
        return ResponseEntity.status(statusCode).body((T) message);
    }

    /**
     * TODO refactor
     */
    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> handleOtherException(Exception ex, Type responseType) {
        try {
            if (responseType instanceof ParameterizedType) {
                // If the responseType is a ParameterizedType, return ResponseEntity<String>
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) ex.getMessage());
            } else if (responseType instanceof Class<?> && (AbstractDto.class.isAssignableFrom((Class<?>) responseType)
                    || ResponseDto.class.isAssignableFrom((Class<?>) responseType))) {
                // If the responseType is a Class assignable from AbstractDto, create an empty DTO
                String response = CommonUtil.getFormattedMessage(ex.getMessage());
                response = ex.getLocalizedMessage();
                ResolvableType.forClass(ResponseDto.class);
                T responseDto = (T) createResponseDto(response);
                if (responseDto == null) {
                    Class<?> responseClass = (Class<?>) responseType;
                    responseDto = (T) responseClass.getDeclaredConstructor().newInstance();
                    Field messageField = responseClass.getDeclaredField("message");
                    messageField.setAccessible(true);
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
            }
            // Return a generic ResponseEntity for other cases
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) ex.getMessage());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException | NoSuchFieldException e) {
            // Handle any exceptions during instantiation or setting field values
            LogUtil.logError("Failed to create empty DTO: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create ResponseDto object from the json input
     */
    public static ResponseDto createResponseDto(String input) {
        if (input == null || input.length() < 20) {
            throw new NotImplementedException("");
        }

        String json = input.substring(input.indexOf("{"));
        json = json.replaceAll("\\n", " ").replaceAll("\\r", " ");
        json = json.trim();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto responseDto = objectMapper.readValue(json, ResponseDto.class);
            return responseDto;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create ResponseDto object from JSON string: " + json, e);
        }
    }

    /**
     * Create response map
     */
    public Map<String, String> createResponse(ResponseEntity<ResponseDto> responseEntity) {
        Map<String, String> response = new HashMap<>();
        response.put("httpStatusCode", responseEntity.getBody().getHttpStatusCode());
        response.put("messageText", responseEntity.getBody().getMessageText());
        return response;
    }

    /**
     * Create request entity
     */
    public <T> HttpEntity<T> createRequestEntity(HttpServletRequest request) {
        return createRequestEntity(request, null);
    }

    public <T> HttpEntity<T> createRequestEntity(HttpServletRequest request, T body) {
        HttpHeaders headers = new HttpHeaders();
        String token = WebUtil.getToken(request);
        if (token != null) {
            headers.set(AUTHORIZATION_TAG, PREFIX_BEARER + token);
        } else {
            LogUtil.logWarn("Unsecured call, no token passed in the headers");
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    private interface RequestExecutor<T> {
        T execute();
    }
}
