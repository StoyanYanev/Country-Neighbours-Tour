package com.example.travel.service;

import com.example.travel.model.Country;
import com.example.travel.service.exception.CountryServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CountryService {
    private static final String KEY = "7713e217eamsh6e8845ab91285a6p1d9132jsn1c0990959bd9";
    private static final String HOST = "x-rapidapi-key";

    private final static Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    private final static HttpHeaders HEADERS = new HttpHeaders();

    static {
        HEADERS.add(HOST, KEY);
    }

    private RestTemplate restTemplate;
    private ParameterizedTypeReference<List<Country>> bean;

    public CountryService() {
        restTemplate = new RestTemplate();
        bean = new ParameterizedTypeReference<List<Country>>() {};
    }

    public Country getCountryByName(final String countryName) throws CountryServiceException {
        LOGGER.info("Get country with name " + countryName + " and request type " + RequestCountryType.REQUEST_TYPE_NAME.getValue());
        final HttpEntity<String> entity = new HttpEntity<>(HEADERS);

        final String url = RequestCountryType.REQUEST_TYPE_NAME.getValue() + countryName;

        final ResponseEntity<List<Country>> countryResponseByName = restTemplate.exchange(url, HttpMethod.GET, entity, bean);
        if (countryResponseByName.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("Failed to get country by name " + countryName);
            throw new CountryServiceException("Failed to get country by name " + countryName);
        }

        final List<Country> foundCountries = countryResponseByName.getBody();
        if (CollectionUtils.isEmpty(foundCountries)) {
            LOGGER.info("Failed to get countries by country name " + foundCountries);
            return null;
        }
        return foundCountries.get(0);
    }

    public Country getCountryByCode(final String countryCode) throws CountryServiceException {
        final String url = RequestCountryType.REQUEST_TYPE_CODE.getValue() + countryCode;
        final HttpEntity<String> entity = new HttpEntity<>(HEADERS);

        final ResponseEntity<Country> responseByCode = restTemplate.exchange(url, HttpMethod.GET, entity, Country.class);
        if (responseByCode.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("Fail to get country by code " + countryCode);
            throw new CountryServiceException("Fail to get country by code " + countryCode);
        }
        return responseByCode.getBody();
    }
}