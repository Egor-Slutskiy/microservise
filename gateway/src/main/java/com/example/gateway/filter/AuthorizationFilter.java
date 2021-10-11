package com.example.gateway.filter;

import com.example.gateway.security.JwtValidator;
import com.nimbusds.jose.Payload;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Component
public class AuthorizationFilter implements GlobalFilter {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getRequest().mutate().build();
        final JwtValidator validator = new JwtValidator();

        final var auth = exchange.getRequest().getHeaders().get("Authorization");


        if(auth != null && validator.isValid(auth.get(0))){
            final Payload payload = validator.getPayload();

            final var jsonPayload = payload.toJSONObject();

            final var request = exchange.getRequest().mutate()
                    .header("Authn", jsonPayload.get("id").toString())
                    .header("X-Role", jsonPayload.get("role").toString())
                    .build();
            final ServerWebExchange modified = exchange.mutate().request(request).build();
            return chain.filter(modified);
        }

        final ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
