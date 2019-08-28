package utils;

import com.example.travel.model.Country;
import com.example.travel.model.CountryBudget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MockData {
    public static final String START_COUNTRY_NAME = "Bulgaria";
    public static final float BUDGET_PER_COUNTRY = 100;
    public static final float TOTAL_BUDGET = 1200;
    public static final String INITIAL_CURRENCY_CODE = "EUR";

    public static final String INVALID_COUNTRY_NAME = "B";
    public static final String INVALID_CURRENCY = "BG";
    public static final float ZERO_BUDGET = 0;
    public static final float NEGATIVE_BUDGET = -100;

    public static final Map<String, Float> RATES = initRatesMap();
    public static final List<String> CURRENCIES = Collections.unmodifiableList(Arrays.asList("BGN"));

    public static final int NUMBER_OF_VISITS = 2;
    public static final List<String> BOARDERS = Collections.unmodifiableList(Arrays.asList("GR", "MK", "RO", "SR", "TR"));
    public static final float LEFTOVER = 200;
    public static final Set<CountryBudget> COUNTRY_BUDGET = initCurrenciesBudgetMap();

    public static final String CODE_GR = "GR";
    public static final String CODE_RO = "RO";
    public static final String CODE_TR = "TR";
    public static final String CODE_MK = "MK";
    public static final String CODE_SR = "SR";

    public static final Country COUNTRY_GREECE = new Country("Greece", null, new ArrayList<>(Arrays.asList("EUR")));
    public static final Country COUNTRY_ROMANIA = new Country("Romania", null, new ArrayList<>(Arrays.asList("RON")));
    public static final Country COUNTRY_TURKEY = new Country("Turkey", null, new ArrayList<>(Arrays.asList("TRY")));
    public static final Country COUNTRY_MACEDONIA = new Country("Republic of Macedonia", null, new ArrayList<>());
    public static final Country COUNTRY_SERBIA = new Country("Serbia", null, new ArrayList<>());

    private static Set<CountryBudget> initCurrenciesBudgetMap() {
        Set<CountryBudget> countryBudgetHashSet = new HashSet<>();

        countryBudgetHashSet.add(new CountryBudget("Romania", "RON", 945.0f));
        countryBudgetHashSet.add(new CountryBudget("Turkey", "TRY", 1229.0399f));
        countryBudgetHashSet.add(new CountryBudget("Greece", "EUR", 200.0f));
        countryBudgetHashSet.add(new CountryBudget("Republic of Macedonia", "EUR", 200.0f));
        countryBudgetHashSet.add(new CountryBudget("Serbia", "EUR", 200.0f));
        return Collections.unmodifiableSet(countryBudgetHashSet);
    }

    private static Map<String, Float> initRatesMap() {
        Map<String, Float> map = new HashMap<>();
        map.put("RON", 4.725f);
        map.put("TRY", 6.1452f);
        return Collections.unmodifiableMap(map);
    }
}