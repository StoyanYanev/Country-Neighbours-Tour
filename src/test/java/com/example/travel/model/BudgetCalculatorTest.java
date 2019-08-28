package com.example.travel.model;

import com.example.travel.calculator.ICalculateBudget;
import com.example.travel.calculator.impl.BudgetCalculator;
import com.example.travel.service.CountryService;
import com.example.travel.service.CurrencyService;
import com.example.travel.service.exception.CountryServiceException;
import com.example.travel.service.exception.CurrencyServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.MockData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BudgetCalculatorTest {
    @Mock
    private CountryService countryService;

    @Mock
    private CurrencyService currencyService;

    private ICalculateBudget calculateBudget;

    @Before
    public void setUp() throws CountryServiceException, CurrencyServiceException {
        calculateBudget = new BudgetCalculator(currencyService, countryService);

        final Country startCountry = new Country(MockData.START_COUNTRY_NAME, MockData.BOARDERS, MockData.CURRENCIES);
        final Currency currency = new Currency(MockData.INITIAL_CURRENCY_CODE, MockData.RATES);

        when(countryService.getCountryByName(MockData.START_COUNTRY_NAME)).thenReturn(startCountry);
        when(countryService.getCountryByName(MockData.INVALID_COUNTRY_NAME)).thenThrow(CountryServiceException.class);

        when(countryService.getCountryByCode(MockData.CODE_GR)).thenReturn(MockData.COUNTRY_GREECE);
        when(countryService.getCountryByCode(MockData.CODE_RO)).thenReturn(MockData.COUNTRY_ROMANIA);
        when(countryService.getCountryByCode(MockData.CODE_TR)).thenReturn(MockData.COUNTRY_TURKEY);
        when(countryService.getCountryByCode(MockData.CODE_MK)).thenReturn(MockData.COUNTRY_MACEDONIA);
        when(countryService.getCountryByCode(MockData.CODE_SR)).thenReturn(MockData.COUNTRY_SERBIA);

        when(currencyService.getCurrency(MockData.INITIAL_CURRENCY_CODE)).thenReturn(currency);
        when(currencyService.getCurrency(MockData.INVALID_CURRENCY)).thenThrow(CurrencyServiceException.class);
    }

    @Test
    public void testTravelInformationSuccess() throws CurrencyServiceException, CountryServiceException {
        final TripPlan tripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        final TripDetails expectedTripDetails = new TripDetails(MockData.NUMBER_OF_VISITS, MockData.BOARDERS, MockData.LEFTOVER, MockData.COUNTRY_BUDGET);
        final TripDetails actualTripDetails = calculateBudget.getTravelInformation(tripPlan);

        assertEquals(expectedTripDetails.getNumberOfVisits(), actualTripDetails.getNumberOfVisits());
        assertEquals(expectedTripDetails.getNeighbors(), actualTripDetails.getNeighbors());
        assertEquals(expectedTripDetails.getLeftover(), actualTripDetails.getLeftover(), 0.0);
        assertEquals(expectedTripDetails.getCountryBudget(), actualTripDetails.getCountryBudget());
    }

    @Test(expected = CountryServiceException.class)
    public void testTravelInformationWithInvalidCountryNameExpectedException() throws CurrencyServiceException, CountryServiceException {
        final TripPlan invalidTripPlan = new TripPlan(MockData.INVALID_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        calculateBudget.getTravelInformation(invalidTripPlan);
    }

    @Test(expected = CurrencyServiceException.class)
    public void testTravelInformationWithInvalidCurrencyExpectedException() throws CurrencyServiceException, CountryServiceException {
        final TripPlan invalidTripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, MockData.INVALID_CURRENCY);
        calculateBudget.getTravelInformation(invalidTripPlan);
    }
}