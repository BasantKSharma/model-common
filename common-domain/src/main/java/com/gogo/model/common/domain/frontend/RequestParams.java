package com.gogo.model.common.domain.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parameters in K,V pair format
 * */
public class RequestParams {

    @JsonProperty("params")
    private Map<String, Object> params;

    public RequestParams() {
        params = new HashMap<>();
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public Object getParam(String key) {
        return params.get(key);
    }
}
