package com.plj.hub.gateway.config;

public enum PermitUri {
    SIGN_UP("/api/users/signup"),
    SIGN_IN("/api/users/signin"),
    ;

    private final String uri;

    PermitUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return this.uri;
    }
}
