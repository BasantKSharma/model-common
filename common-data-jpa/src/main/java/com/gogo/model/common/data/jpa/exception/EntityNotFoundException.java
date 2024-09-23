package com.gogo.model.common.data.jpa.exception;

import com.gogo.model.common.domain.util.LogUtil;

/**
 * EntityNotFoundException is thrown if no entity is found for the identifier.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
        LogUtil.logError("EntityNotFoundException - " + message);
    }
}
