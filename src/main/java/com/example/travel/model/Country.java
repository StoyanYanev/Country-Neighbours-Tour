package com.example.travel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {
    private String name;
    private List<String> borders;
    private List<String> currencies;

    public Country() {
        borders = new ArrayList<>();
        currencies = new ArrayList<>();
    }

    public Country(final String name, final List<String> borders, final List<String> currencies) {
        this.name = name;
        this.borders = borders;
        this.currencies = currencies;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setBorders(final List<String> borders) {
        this.borders = borders;
    }

    public void setCurrencies(final List<String> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", borders=" + borders +
                ", currencies=" + currencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return name.equals(country.name) &&
                borders.equals(country.borders) &&
                currencies.equals(country.currencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, borders, currencies);
    }
}