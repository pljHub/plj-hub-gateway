package com.plj.hub.gateway.exception;

import com.plj.hub.gateway.global.exception.GatewayErrorCode;
import com.plj.hub.gateway.global.exception.PljHubException;

public class UserAlreadyActivatedException extends PljHubException {
    public UserAlreadyActivatedException() {
        super(GatewayErrorCode.USER_NOT_ACTIVATED);
    }
}
