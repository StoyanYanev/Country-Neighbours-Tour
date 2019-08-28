package com.example.travel.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Validated
public class TripPlan {
    @NotBlank(message = "Invalid start country name")
    private String startCountry;
    @Positive(message = "Invalid budget per country")
    private float budgetPerCountry;
    @Positive(message = "Invalid total budget")
    private float totalBudget;
    @NotBlank(message = "Invalid start country code")
    private String currencyCode;

    public TripPlan() {
        // empty constructor
    }

    public TripPlan(final String startCountry, final float budgetPerCountry, final float totalBudget, final String currencyCode) {
        this.startCountry = startCountry;
        this.budgetPerCountry = budgetPerCountry;
        this.totalBudget = totalBudget;
        this.currencyCode = currencyCode;
    }

    public String getStartCountry() {
        return startCountry;
    }

    public float getBudgetPerCountry() {
        return budgetPerCountry;
    }

    public float getTotalBudget() {
        return totalBudget;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setStartCountry(final String startCountry) {
        this.startCountry = startCountry;
    }

    public void setBudgetPerCountry(final float budgetPerCountry) {
        this.budgetPerCountry = budgetPerCountry;
    }

    public void setTotalBudget(final float totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode.toUpperCase();
    }

    @Override
    public String toString() {
        return "TripPlan{" +
                "startCountry='" + startCountry + '\'' +
                ", budgetPerCountry=" + budgetPerCountry +
                ", totalBudget=" + totalBudget +
                ", currency='" + currencyCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripPlan)) return false;
        TripPlan tripPlan = (TripPlan) o;
        return Float.compare(tripPlan.budgetPerCountry, budgetPerCountry) == 0 &&
                Float.compare(tripPlan.totalBudget, totalBudget) == 0 &&
                startCountry.equals(tripPlan.startCountry) &&
                currencyCode.equals(tripPlan.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startCountry, budgetPerCountry, totalBudget, currencyCode);
    }
}