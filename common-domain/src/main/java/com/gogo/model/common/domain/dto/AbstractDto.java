package com.gogo.model.common.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.exception.NotImplementedException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Abstract class for DTO 's
 */
@Getter
@Setter
public class AbstractDto implements Serializable {

    /**
     *  Serializable = enable object to convert to byte stream and later reconstruct it like session attributes.
     *  If you do not explicitly declare a serialVersionUID, the Java compiler will automatically generate one
     *  based on various aspects of the class (e.g., fields, methods).
     *  This can lead to unexpected InvalidClassException if the class structure changes, as the automatically generated serialVersionUID may change.
     * */
    @Serial
    private static final long serialVersionUID = 1L;

    private HashMap<String, Object> customMap = new HashMap<>();

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updatedAt;

    public void addToCustomMap(String key, Object value) {
        customMap.put(key, value);
    }

    public Object getCustomMapProperty(String key) {
        return customMap.get(key);
    }

    @Transient
    @JsonIgnore
    protected String getIdentifier(){
        throw new NotImplementedException("getIdentifier()");
    }
}
