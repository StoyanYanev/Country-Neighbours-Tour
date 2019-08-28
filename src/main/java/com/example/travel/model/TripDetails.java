package com.example.travel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class TripDetails {
    private int numberOfVisits;
    private List<String> neighbors;
    private float leftover;
    private Set<CountryBudget> countryBudget;

    public TripDetails() {
        neighbors = new ArrayList<>();
        countryBudget = new HashSet<>();
    }

    public TripDetails(final int numberOfVisits, final List<String> neighbors, final float leftover, final Set<CountryBudget> countryBudget) {
        this.numberOfVisits = numberOfVisits;
        this.neighbors = neighbors;
        this.leftover = leftover;
        this.countryBudget = countryBudget;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public List<String> getNeighbors() {
        return neighbors;
    }

    public float getLeftover() {
        return leftover;
    }

    public Set<CountryBudget> getCountryBudget() {
        return countryBudget;
    }

    public void setNumberOfVisits(final int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public void setNeighbors(final List<String> neighbors) {
        this.neighbors = neighbors;
    }

    public void setLeftover(final float leftover) {
        this.leftover = leftover;
    }

    public void setCountryBudget(final Set<CountryBudget> countryBudget) {
        this.countryBudget = countryBudget;
    }

    @Override
    public String toString() {
        return "TripDetails{" +
                "numberOfVisits=" + numberOfVisits +
                ", neighbors=" + neighbors +
                ", leftover=" + leftover +
                ", countryBudget=" + countryBudget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripDetails)) return false;
        TripDetails that = (TripDetails) o;
        return numberOfVisits == that.numberOfVisits &&
                Float.compare(that.leftover, leftover) == 0 &&
                neighbors.equals(that.neighbors) &&
                countryBudget.equals(that.countryBudget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfVisits, neighbors, leftover, countryBudget);
    }
}