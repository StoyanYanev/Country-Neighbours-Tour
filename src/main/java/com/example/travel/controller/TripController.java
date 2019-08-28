package com.example.travel.controller;

import com.example.travel.model.TripDetails;
import com.example.travel.model.TripPlan;
import com.example.travel.orchestrator.TripOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@CrossOrigin
@RestController
@Validated
public class TripController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    @Autowired
    TripOrchestrator tripOrchestrator;

    @GetMapping(path = "/")
    public RedirectView login() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/home");
        return redirectView;
    }

    @PostMapping(path = "/tour", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TripDetails> getDetails(@Valid @RequestBody final TripPlan tripPlan) {
        LOGGER.info("Get details for " + tripPlan);
        return tripOrchestrator.getTripDetails(tripPlan);
    }

    @GetMapping(path = "/tour/v2")
    public ResponseEntity<TripDetails> getDetails2(@RequestParam(required = true) @NotBlank final String country,
                                                   @RequestParam(required = true) @Positive final float budgetPerCountry,
                                                   @RequestParam(required = true) @Positive final float totalBudget,
                                                   @RequestParam(required = true) @NotBlank final String currency) {
        LOGGER.info("Get details for country " + country + " budget per country " + budgetPerCountry + " total budget " + totalBudget + " currency " + currency);
        final TripPlan tripPlan = new TripPlan(country, budgetPerCountry, totalBudget, currency);
        return tripOrchestrator.getTripDetails(tripPlan);
    }

    @GetMapping(path = "/tour/{country}/{budgetPerCountry}/{totalBudget}/{currency}")
    public ResponseEntity<TripDetails> getDetails(@PathVariable @NotBlank final String country,
                                                  @PathVariable @Positive final float budgetPerCountry,
                                                  @PathVariable @Positive final float totalBudget,
                                                  @PathVariable @NotBlank final String currency) {
        LOGGER.info("Get details for country " + country + " budget per country " + budgetPerCountry + " total budget " + totalBudget + " currency " + currency);
        final TripPlan tripPlan = new TripPlan(country, budgetPerCountry, totalBudget, currency);
        return tripOrchestrator.getTripDetails(tripPlan);
    }

    @GetMapping(path = "/home")
    public void home() {
    }
}