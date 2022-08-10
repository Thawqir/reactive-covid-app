package com.example.reactivecovidapp;

import com.example.reactivecovidapp.connector.CovidStatsAppConnector;
import com.example.reactivecovidapp.domain.Cases;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactive")
@RequiredArgsConstructor
public class Controller {

    private final CovidStatsAppConnector covidStatsAppConnector;

    @GetMapping("/countryReact")
    public Flux<Cases> getAllCases(){
        return covidStatsAppConnector.getAllCases();
    }
}
