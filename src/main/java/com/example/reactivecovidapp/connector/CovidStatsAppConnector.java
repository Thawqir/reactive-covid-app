package com.example.reactivecovidapp.connector;

import com.example.reactivecovidapp.domain.Cases;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CovidStatsAppConnector {

    private final WebClient webClient;

    public CovidStatsAppConnector(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    public Flux<Cases> getAllCases (){
        return webClient.get().uri("/country")
                .retrieve()
                .bodyToFlux(Cases.class);
    }
}
