package com.agrisafe.apigateway.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Order(-1)
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus httpStatus = getHttpStatus(ex);

        Map<String, Object> errorAttributesMap = getErrorAttributesMap(ex, httpStatus);

        exchange.getResponse().setStatusCode(httpStatus);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Convert error attributes map to JSON
        String responseBody = convertErrorAttributesToJson(errorAttributesMap);

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(responseBody.getBytes())));
    }

    private HttpStatus getHttpStatus(Throwable ex) {
        if (ex instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof AccessDeniedException) {
            return HttpStatus.FORBIDDEN;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }


    private Map<String, Object> getErrorAttributesMap(Throwable ex, HttpStatus httpStatus) {
        Map<String, Object> errorAttributesMap = new LinkedHashMap<>();
        errorAttributesMap.put("status", httpStatus.value());
        errorAttributesMap.put("message", ex.getMessage());

        return errorAttributesMap;
    }


    private String convertErrorAttributesToJson(Map<String, Object> errorAttributes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(errorAttributes);
        } catch (JsonProcessingException e) {
            System.out.println("here");
            throw new RuntimeException("Error converting error attributes to JSON", e);
        }
    }

}
