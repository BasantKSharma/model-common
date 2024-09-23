package com.gogo.model.common.domain.frontend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Custom link
 * */
@Getter
@Setter
@AllArgsConstructor
public class Link implements Serializable {

    /**
     *  Serializable = enable object to convert to byte stream and later reconstruct it like session attributes.
     *  If you do not explicitly declare a serialVersionUID, the Java compiler will automatically generate one
     *  based on various aspects of the class (e.g., fields, methods).
     *  This can lead to unexpected InvalidClassException if the class structure changes, as the automatically generated serialVersionUID may change.
     * */
    @Serial
    private static final long serialVersionUID = 1L;

    private String label;

    private String url;
}