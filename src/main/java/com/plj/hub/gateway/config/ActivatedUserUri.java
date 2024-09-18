package com.plj.hub.gateway.config;

public enum ActivatedUserUri {

    REQUEST_SECURE_CODE("/api/users/secure-code"),
    ACTIVATE_USER("/api/users/activate"),
    ;
    private final String uri;

    ActivatedUserUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return this.uri;
    }
}
