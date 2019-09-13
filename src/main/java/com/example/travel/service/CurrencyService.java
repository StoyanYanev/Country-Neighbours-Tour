package com.example.travel.service;

import com.example.travel.model.Currency;
import com.example.travel.service.exception.CurrencyServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    private static final String REQUEST_URL = "https://api.exchangeratesapi.io/latest?base=";

    private RestTemplate restTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    public CurrencyService() {
        restTemplate = new RestTemplate();
    }

    public Currency getCurrency(final String base) throws CurrencyServiceException {
        LOGGER.info("Get currency with base " + base);
        final String url = REQUEST_URL + base.toUpperCase();

        try {
            return restTemplate.getForObject(url, Currency.class);
        } catch (Exception e) {
            final String errorMessage = "Fail to get currency " + base;
            LOGGER.error(errorMessage);
            throw new CurrencyServiceException(errorMessage, e);
        }
    }
}
