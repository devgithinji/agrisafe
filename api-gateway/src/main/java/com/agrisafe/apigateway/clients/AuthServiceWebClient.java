package com.agrisafe.apigateway.clients;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Lazy
public class AuthServiceWebClient {
    private final WebClient webClient;

    public AuthServiceWebClient(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.webClient = webClientBuilder.filter(lbFunction).build();
    }

    public Mono<Long> validateFarmer(String token) {
        return webClient.post()
                .uri("http://auth-service/auth/farmer/validate")
                .bodyValue(token)
                .retrieve()
                .bodyToMono(Long.class);
    }
}
