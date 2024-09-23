package com.gogo.model.common.domain.exception;

import com.gogo.model.common.domain.util.LogUtil;

/**
 * NotImplementedException is thrown if the method is not implemented.
 */
public class NotImplementedException extends RuntimeException {

    public NotImplementedException(String message) {
        super(message);
        LogUtil.logError("NotImplementedException - " + message);
    }
}
