package com.arturszybiak.codingtest.enums;

/**
 * validation statuses
 * */
public enum ValidationStatus {
    APPROVED("APPROVED"),
    REJECT("REJECT");

    private String validationStatus;

    ValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String validationStatus() {
        return validationStatus;
    }
}
