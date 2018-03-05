package com.arturszybiak.codingtest.enums;

/**
 * Validation messages
 * */
public enum ValidationMessage {
    TRANSACTION_TYPE_NOT_FOUND("There is no transaction type"),
    VALUE_DATE_BEFORE_TRADE_DATE("Value date cannot be before trade date"),
    VALUE_DATE_CANNOT_BE_IN_FREE_DAY("Value date cannot be in weekend"),
    CUSTOMER_NOT_SUPPORTED("Customer not supported"),
    CURRENCY_WRONG_FORMAT("Currency is in wrong format"),
    INCORRECT_EXCERCISE_START_DATE("Excercise Start Date has to be after the trade date and before the expiry date"),
    MISSING_EXCERCISE_START_DATE_OR_TRADE_DATE("Missing excercise start date or trade date"),
    EXPIRY_DATA_BEFORE_DELIVERY_DATE("Expiry date shall be before delivery date"),
    PREMIUM_DATA_BEFORE_DELIVERY_DATE("Premium date shall be before delivery date");

    private String validationMessage;

    ValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String validationMessage() {
        return validationMessage;
    }
}
