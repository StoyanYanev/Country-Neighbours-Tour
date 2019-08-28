package com.example.travel.model;

import java.util.Objects;

public class CountryBudget {
    private String countryName;
    private String currencyCode;
    private float budget;

    public CountryBudget() {
        // empty constructor
    }

    public CountryBudget(final String countryName, final String currencyCode, final float budget) {
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.budget = budget;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public float getBudget() {
        return budget;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setBudget(final float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "CountryBudget{" +
                "countryName='" + countryName + '\'' +
                ", base='" + currencyCode + '\'' +
                ", budget=" + budget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryBudget that = (CountryBudget) o;
        return Float.compare(that.budget, budget) == 0 &&
                Objects.equals(countryName, that.countryName) &&
                Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, currencyCode, budget);
    }
}