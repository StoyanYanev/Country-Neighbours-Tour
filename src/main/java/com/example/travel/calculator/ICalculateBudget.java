package com.example.travel.calculator;

import com.example.travel.model.TripDetails;
import com.example.travel.model.TripPlan;
import com.example.travel.service.exception.CountryServiceException;
import com.example.travel.service.exception.CurrencyServiceException;

public interface ICalculateBudget {

    TripDetails getTravelInformation(final TripPlan tripPlan) throws CountryServiceException, CurrencyServiceException;
}