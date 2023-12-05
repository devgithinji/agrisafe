package com.agrisafe.famerservice.config;

import com.agrisafe.common.model.auth.Farmer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Configuration
public class WebclientConfig {

    private final WebClient webClient;

    public WebclientConfig(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.webClient = webClientBuilder.filter(lbFunction).build();
    }

    public Optional<Farmer> getFarmer(String email) {
        return webClient.get()
                .uri("http://auth-service/auth/farmer/{email}", email)
                .retrieve()
                .bodyToMono(Farmer.class)
                .blockOptional();
    }
}
