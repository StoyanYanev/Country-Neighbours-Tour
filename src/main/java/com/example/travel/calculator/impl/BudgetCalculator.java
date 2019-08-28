package com.example.travel.calculator.impl;

import com.example.travel.calculator.ICalculateBudget;
import com.example.travel.model.Country;
import com.example.travel.model.CountryBudget;
import com.example.travel.model.Currency;
import com.example.travel.model.TripDetails;
import com.example.travel.model.TripPlan;
import com.example.travel.service.CountryService;
import com.example.travel.service.CurrencyService;
import com.example.travel.service.exception.CountryServiceException;
import com.example.travel.service.exception.CurrencyServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BudgetCalculator implements ICalculateBudget {
    @Autowired
    private final CurrencyService currencyService;

    @Autowired
    private final CountryService countryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetCalculator.class);

    public BudgetCalculator(final CurrencyService currencyService, final CountryService countryService) {
        this.currencyService = currencyService;
        this.countryService = countryService;
    }

    /**
     * @param tripPlan start plan for a trip
     * @return information for the trip
     */
    @Override
    public TripDetails getTravelInformation(final TripPlan tripPlan) throws CountryServiceException, CurrencyServiceException {
        LOGGER.info("Calculating travel information for " + tripPlan);
        Country startingCountry = null;
        try {
            startingCountry = countryService.getCountryByName(tripPlan.getStartCountry());
        } catch (CountryServiceException e) {
            LOGGER.error("Failed to get initial country " + startingCountry, e);
            throw e;
        }
        LOGGER.info("Starting country " + startingCountry);

        final List<String> borders = startingCountry.getBorders();
        final int neighborsCount = borders.size();
        final int numberOfVisits = calculateTripVisits(tripPlan, neighborsCount);

        final float budgetPerCountryInOriginalRate = tripPlan.getBudgetPerCountry() * numberOfVisits;
        LOGGER.info("Budget per country in original rate " + budgetPerCountryInOriginalRate);
        final float leftover = tripPlan.getTotalBudget() - (budgetPerCountryInOriginalRate * neighborsCount);
        LOGGER.info("Leftover " + leftover);

        final Set<CountryBudget> countryBudgets = calculateBudgets(budgetPerCountryInOriginalRate, borders, tripPlan.getCurrencyCode());

        return new TripDetails(numberOfVisits, borders, leftover, countryBudgets);
    }

    /**
     * Calculate how many times user can go through all neighbor
     *
     * @param tripPlan start plan for a trip
     * @return numbers of visits
     */
    private int calculateTripVisits(final TripPlan tripPlan, final int neighborsCount) {
        LOGGER.info("Calculating number of visits for " + tripPlan + " with number of country neighbors " + neighborsCount);
        return (int) (tripPlan.getTotalBudget() / (tripPlan.getBudgetPerCountry() * neighborsCount));
    }


    /**
     * Calculate the budget for each country in their respected currencies
     *
     * @param budgetPerCountry    used budget per country in original base
     * @param neighbours          of the start country
     * @param initialCurrencyCode start currency
     * @return set of countries and budgets
     * @throws CountryServiceException
     * @throws CurrencyServiceException
     */
    private Set<CountryBudget> calculateBudgets(final float budgetPerCountry, final List<String> neighbours, final String initialCurrencyCode) throws CurrencyServiceException, CountryServiceException {
        LOGGER.info("Calculating budget per country " + budgetPerCountry + " and number of country neighbors " + neighbours + " and initial initialCurrency code " + initialCurrencyCode);
        final Currency initialCurrency = currencyService.getCurrency(initialCurrencyCode);
        LOGGER.info("Get initialCurrency " + initialCurrency);

        final Set<CountryBudget> countryBudgets = new HashSet<>();
        for (String neighbourCode : neighbours) {
            final Country neighbour;
            try {
                neighbour = countryService.getCountryByCode(neighbourCode);
            } catch (CountryServiceException e) {
                LOGGER.error("Failed to get information for neighbour by code " + neighbourCode);
                throw e;
            }

            if (neighbour != null) {
                LOGGER.info("Found neighbour: " + neighbour);
                final String neighborBase = getCountryBaseCurrency(neighbour.getCurrencies(), initialCurrency);
                final float rate = initialCurrency.getExchangeRate(neighborBase);
                final float neighbourBudget = rate * budgetPerCountry;
                LOGGER.info("neighbour country: {} | country base {} | country budget {}", neighbour.toString(), neighborBase, neighbourBudget);
                countryBudgets.add(new CountryBudget(neighbour.getName(), neighborBase, neighbourBudget));
            } else {
                throw new CountryServiceException("Fail to get information for neighbour by code " + neighbourCode);
            }
        }
        return countryBudgets;
    }

    // if no exchange rates found - return initial country base currency
    private String getCountryBaseCurrency(final List<String> neighbourCurrencies, Currency initialCurrency) {
        if (initialCurrency == null || CollectionUtils.isEmpty(initialCurrency.getRates())
                || CollectionUtils.isEmpty(neighbourCurrencies) || !initialCurrency.getRates().containsKey(neighbourCurrencies.get(0))) {
            return initialCurrency.getBase();
        }
        return neighbourCurrencies.get(0);
    }
}