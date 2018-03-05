package com.arturszybiak.codingtest.enums;

/**
 * currency code library
 * */
public enum  CurrencyCodes {

    USD("US", "D");

    private String countryCode;
    private String currencyFirstLetter;

    CurrencyCodes(String countryCode, String currencyFirstLetter) {
        this.countryCode = countryCode;
        this.currencyFirstLetter = currencyFirstLetter;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrencyFirstLetter() {
        return currencyFirstLetter;
    }

    public static CurrencyCodes fromCountryCode(String countryCode) {
        for (CurrencyCodes codes : CurrencyCodes.values()) {
            if (codes.getCountryCode().equalsIgnoreCase(countryCode)) {
                return codes;
            }
        }
        return null;
    }
}
