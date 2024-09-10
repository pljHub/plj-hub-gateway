package com.plj.hub.gateway.filter;

import com.plj.hub.gateway.config.PermitUri;
import com.plj.hub.gateway.exception.AccessDeniedException;
import com.plj.hub.gateway.exception.JwtNotValidException;
import com.plj.hub.gateway.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (isPermitAllUri(exchange, chain, path)) return chain.filter(exchange);

        String token =  extractToken(exchange);

        if (!jwtUtils.validateToken(token)) {
            throw new JwtNotValidException();
        }

        String currentUserId = jwtUtils.extractUserId(token);
        String currentUserRole = jwtUtils.extractUserRole(token);

        ServerHttpRequest request = setUserToHeader(exchange, currentUserId, currentUserRole);

        ServerWebExchange modifiedExchange = exchange.mutate().request(request).build();

        return chain.filter(modifiedExchange);
    }

    private static ServerHttpRequest setUserToHeader(ServerWebExchange exchange, String currentUserId, String currentUserRole) {
        return exchange.getRequest().mutate()
                .header("X-User-Id", currentUserId)
                .header("X-User-Role", currentUserRole)
                .build();
    }

    private String extractToken(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorization != null && authorization.startsWith(("Bearer "))) {
            return authorization.substring(7);
        }
        throw new AccessDeniedException();
    }

    private boolean isPermitAllUri(ServerWebExchange exchange, GatewayFilterChain chain, String path) {
        List<String> collect = Arrays.stream(PermitUri.values()).map(u -> u.getUri()).collect(Collectors.toList());
        if (collect.contains(path)) {
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
