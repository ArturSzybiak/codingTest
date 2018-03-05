package com.arturszybiak.codingtest.enums;

/**
 * supported customers
 * */
public enum SupportedCustomers {
    PlUTO1("PLUTO1"),
    PLUTO2("PLUTO2");

    private String custommerName;

    SupportedCustomers(String custommerName) {
        this.custommerName = custommerName;
    }

    public String custommerName() {
        return custommerName;
    }

    public static SupportedCustomers fromString(String text) {
        for (SupportedCustomers customers : SupportedCustomers.values()) {
            if (customers.custommerName.equalsIgnoreCase(text)) {
                return customers;
            }
        }
        return null;
    }
}
