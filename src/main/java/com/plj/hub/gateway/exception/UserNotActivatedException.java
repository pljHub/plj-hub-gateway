package com.plj.hub.gateway.exception;

import com.plj.hub.gateway.global.exception.ErrorCode;
import com.plj.hub.gateway.global.exception.GatewayErrorCode;
import com.plj.hub.gateway.global.exception.PljHubException;

public class UserNotActivatedException extends PljHubException {
    public UserNotActivatedException() {
        super(GatewayErrorCode.USER_NOT_ACTIVATED);
    }
}
