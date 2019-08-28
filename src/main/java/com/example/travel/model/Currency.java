package com.example.travel.model;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Currency {
    private String base;
    private Map<String, Float> rates;

    private static final float BASE_RATE = 1;

    public Currency() {
        rates = new HashMap<>();
    }

    public Currency(final String base, final Map<String, Float> rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public float getExchangeRate(final String base) {
        if (CollectionUtils.isEmpty(rates) || !rates.containsKey(base)) {
            return BASE_RATE;
        }
        return rates.get(base);
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setBase(final String base) {
        this.base = base;
    }

    public void setRates(final Map<String, Float> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return base.equals(currency.base) &&
                rates.equals(currency.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, rates);
    }
}