package com.gogo.model.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gogo.model.common.domain.frontend.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Request DTO containing entity and request parameters.
 * T can be AbstractDto or String in case of simple requests.
 * */
@Getter
@Setter
public class RequestDto<T> implements Serializable {

    /**
     *  Serializable = enable object to convert to byte stream and later reconstruct it like session attributes.
     *  If you do not explicitly declare a serialVersionUID, the Java compiler will automatically generate one
     *  based on various aspects of the class (e.g., fields, methods).
     *  This can lead to unexpected InvalidClassException if the class structure changes, as the automatically generated serialVersionUID may change.
     * */
    @Serial
    private static final long serialVersionUID = 1L;

    private T entity;

    private RequestParams requestParams;

    @JsonCreator
    public RequestDto(@JsonProperty("entity") T entity, @JsonProperty("requestParams") RequestParams requestParams) {
        this.entity = entity;
        this.requestParams = requestParams;
    }
}

