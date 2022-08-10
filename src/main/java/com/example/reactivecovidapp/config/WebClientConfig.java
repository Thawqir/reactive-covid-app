package com.example.reactivecovidapp.config;

import com.example.reactivecovidapp.Controller;
import com.example.reactivecovidapp.connector.CovidStatsAppConnector;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
public class WebClientConfig {

    private static final int CONNECTOR_TIMEOUT_MILLIS = 15000;

    @Autowired
    private Environment environment;

    @Bean
    public CovidStatsAppConnector covidStatsAppConnector(){
        return new CovidStatsAppConnector(webClientBuilder(), environment.getProperty("covid.service.url"));
    }

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder()
                .clientConnector(createReactorClientHttpConnector());
    }

    private static ReactorClientHttpConnector createReactorClientHttpConnector() {
        return new ReactorClientHttpConnector(
                HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTOR_TIMEOUT_MILLIS)
                        .doOnConnected(connection -> connection
                                .addHandlerLast(new ReadTimeoutHandler(CONNECTOR_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(CONNECTOR_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)))
        );
    }
}