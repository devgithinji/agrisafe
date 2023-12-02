package com.agrisafe.apigateway.config;

import com.agrisafe.apigateway.clients.AuthServiceWebClient;
import com.agrisafe.common.exception.APIException;
import com.agrisafe.common.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private final AuthServiceWebClient authServiceWebClient;
    private final ObjectMapper objectMapper;

    public CustomGlobalFilter(AuthServiceWebClient authServiceWebClient, ObjectMapper objectMapper) {
        this.authServiceWebClient = authServiceWebClient;
        this.objectMapper = objectMapper;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        if (!path.contains("public") && !path.contains("auth")) {

            try {
                //verify token
                String token = getToken(exchange);

                Mono<String> userIdMono = authServiceWebClient.validateFarmer(token);

                return userIdMono.flatMap(email -> {
                    exchange.getRequest().mutate().header("email", email);
                    return chain.filter(exchange);
                }).switchIfEmpty(handleException(exchange));


            } catch (Exception e) {
                return handleException(exchange);
            }

        }

        return chain.filter(exchange);
    }

    private Mono<Void> handleException(ServerWebExchange exchange) {
        exchange.getResponse().getHeaders().clearContentHeaders();
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);


        ErrorDetails errorDetails = new ErrorDetails(exchange.getRequest().getPath().toString(), "invalid token");

        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(errorDetails);

        } catch (Exception e) {
            jsonBody = "{\"message\":\"Error processing JSON\"}";
        }
        byte[] messageBytes = jsonBody.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(messageBytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    private String getToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || token.isBlank()) throw new APIException("invalid token");
        return token.substring(7);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
