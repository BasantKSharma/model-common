package com.gogo.model.common.data.jpa.exception;

import com.gogo.model.common.domain.util.LogUtil;

/**
 * EntityNotFoundException is thrown if entity is already existing for the identifier.
 */
public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String message) {
        super(message);
        LogUtil.logError("EntityAlreadyExistException - " + message);
    }
}
