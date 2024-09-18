package com.plj.hub.gateway.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum GatewayErrorCode implements ErrorCode{
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    USER_NOT_ACTIVATED(HttpStatus.FORBIDDEN, "계정이 활성화 되지 않았습니다."),
    USER_ALREADY_ACTIVATED(HttpStatus.FORBIDDEN, "이미 활성화된 계정입니다."),

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
