package com.plj.hub.gateway.exception;

import com.plj.hub.gateway.global.exception.ErrorCode;
import com.plj.hub.gateway.global.exception.JwtErrorCode;
import com.plj.hub.gateway.global.exception.PljHubException;

public class JwtNotValidException extends PljHubException {

    public JwtNotValidException() {
        super(JwtErrorCode.TOKEN_NOT_VALID);
    }
}
