package com.plj.hub.gateway.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum GatewayErrorCode implements ErrorCode{
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
