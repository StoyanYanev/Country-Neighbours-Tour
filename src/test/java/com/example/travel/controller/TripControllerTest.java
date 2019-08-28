package com.example.travel.controller;

import com.example.travel.CountryNeighboursTourApplication;
import com.example.travel.model.TripPlan;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utils.MockData;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CountryNeighboursTourApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class TripControllerTest {
    private static final String URL = "/tour";

    @Autowired
    private MockMvc mvc;

    private Gson gson;

    @Before
    public void setUp() {
        gson = new Gson();
    }

    @Test
    public void testTripControllerWithValidData() throws Exception {
        final TripPlan tripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        String result = gson.toJson(tripPlan);
        mvc.perform(MockMvcRequestBuilders.post(URL).content(result).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testTripControllerWithEmptyStartCountry() throws Exception {
        final TripPlan tripPlan = new TripPlan("", MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        String result = gson.toJson(tripPlan);
        mvc.perform(MockMvcRequestBuilders.post(URL).content(result).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testTripControllerWithZeroBudgetPerCountry() throws Exception {
        final TripPlan tripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.ZERO_BUDGET, MockData.NEGATIVE_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        String result = gson.toJson(tripPlan);
        mvc.perform(MockMvcRequestBuilders.post(URL).content(result).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testTripControllerWithNegativeTotalBudget() throws Exception {
        final TripPlan tripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.NEGATIVE_BUDGET, MockData.INITIAL_CURRENCY_CODE);
        String result = gson.toJson(tripPlan);
        mvc.perform(MockMvcRequestBuilders.post(URL).content(result).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void testTripControllerWithNullCurrency() throws Exception {
        final TripPlan tripPlan = new TripPlan(MockData.START_COUNTRY_NAME, MockData.BUDGET_PER_COUNTRY, MockData.TOTAL_BUDGET, null);
        String result = gson.toJson(tripPlan);
        mvc.perform(MockMvcRequestBuilders.post(URL).content(result).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}