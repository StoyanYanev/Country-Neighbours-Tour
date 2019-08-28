package com.example.travel.orchestrator;

import com.example.travel.calculator.ICalculateBudget;
import com.example.travel.model.TripDetails;
import com.example.travel.model.TripPlan;
import com.example.travel.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TripOrchestrator {
    @Autowired
    ICalculateBudget calculator;

    private final static Logger LOGGER = LoggerFactory.getLogger(TripOrchestrator.class);

    public TripOrchestrator() {
        // empty constructor
    }

    public ResponseEntity<TripDetails> getTripDetails(final TripPlan tripPlan) {
        try {
            return new ResponseEntity<>(calculator.getTravelInformation(tripPlan), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
