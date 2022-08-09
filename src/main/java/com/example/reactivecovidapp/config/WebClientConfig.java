package com.example.reactivecovidapp.config;

import com.example.reactivecovidapp.connector.CovidStatsAppConnector;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebClientConfig {

    @Bean
    public CovidStatsAppConnector covidStatsAppConnector(){
        return new CovidStatsAppConnector(webClientBuilder());
    }
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder().clientConnector((ClientHttpConnector) client);
    }
    WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
            .build();
}
