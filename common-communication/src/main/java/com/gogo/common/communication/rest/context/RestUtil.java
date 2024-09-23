package com.gogo.common.communication.rest.context;

import com.gogo.model.common.domain.config.ServiceUrlConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility methods for rest invocations
 */
@Component
public class RestUtil {

    @Autowired
    private ServiceUrlConfig serviceUrlConfig;

    @Autowired
    private RestClient restClient;

    private static RestUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

//    /**
//     * Get Customer from the email
//     */
//    public static CustomerDto getCustomer(HttpServletRequest request, String email) {
//        String url = null;
//        CustomerDto customerDto = null;
//        try {
//            url = CommonUtil.getServiceUrl(instance.serviceUrlConfig, CustomerDto.class, "/email/" + email);
//            ResponseEntity<CustomerDto> response = instance.restClient.get(CustomerDto.class, url, request);
//            customerDto = response.getBody();
//        } catch (Exception e) {
//            LogUtil.logConnectError(url);
//            LogUtil.logError(e.getMessage());
//        }
//        return customerDto;
//    }

}
