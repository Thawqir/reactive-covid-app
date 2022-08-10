package com.example.reactivecovidapp.connector;

import com.example.reactivecovidapp.domain.Cases;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CovidStatsAppConnector {

    private final WebClient webClient;

    private final String baseURL;

    public CovidStatsAppConnector(WebClient.Builder webClientBuilder, String baseURL){
        this.webClient = webClientBuilder.baseUrl(baseURL).defaultHeaders(header -> header.setBasicAuth("admin","password")).build();
        this.baseURL = baseURL;
    }

    public Flux<Cases> getAllCases (){
        String url = baseURL + "/country";

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Cases.class);
    }
}
