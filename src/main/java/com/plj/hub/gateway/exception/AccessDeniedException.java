package com.plj.hub.gateway.exception;

import com.plj.hub.gateway.global.exception.ErrorCode;
import com.plj.hub.gateway.global.exception.GatewayErrorCode;
import com.plj.hub.gateway.global.exception.PljHubException;

public class AccessDeniedException extends PljHubException {

    public AccessDeniedException() {
        super(GatewayErrorCode.ACCESS_DENIED);
    }
}
