package com.gogo.model.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.constants.ResponseType;
import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

@Schema(name = "Response")
@Data
@NoArgsConstructor
@Builder
public class ResponseDto implements Serializable {

    /**
     * Serializable = enable object to convert to byte stream and later reconstruct it like session attributes.
     * If you do not explicitly declare a serialVersionUID, the Java compiler will automatically generate one
     * based on various aspects of the class (e.g., fields, methods).
     * This can lead to unexpected InvalidClassException if the class structure changes, as the automatically generated serialVersionUID may change.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private String httpStatusCode;

    private String messageText;

    private String messageNum;

    private HashMap<String, String> params;

    private ResponseType responseType;

    public ResponseDto(String messageText) {
        this.messageText = messageText;
        this.responseType = ResponseType.success;
    }

    public ResponseDto(String httpStatusCode, String messageText) {
        this(httpStatusCode, messageText, "-1", new HashMap<>(), ResponseType.success);
    }

    public ResponseDto(String httpStatusCode, String messageText, String messageNum, HashMap<String, String> params, ResponseType responseType) {
        this.httpStatusCode = httpStatusCode;
        this.messageText = messageText;
        this.messageNum = messageNum;
        this.params = params;
        this.responseType = responseType;
    }

    //@Transient
    @JsonIgnore
    @IgnoreForBinding
    public boolean isSuccess() {
        return httpStatusCode != null && httpStatusCode.startsWith("20");
    }
}
