package com.arturszybiak.codingtest.enums;

/**
 * transaction styles
 * */
public enum TransactionStyle {
    AMERICAN("AMERICAN"),
    EUROPEAN("EUROPEAN");

    private String transactionStyle;

    TransactionStyle(String transactionStyle) {
        this.transactionStyle = transactionStyle;
    }

    public String transactionStyle() {
        return transactionStyle;
    }

    public static TransactionStyle fromString(String text) {
        for (TransactionStyle transactionStyle : TransactionStyle.values()) {
            if (transactionStyle.transactionStyle.equalsIgnoreCase(text)) {
                return transactionStyle;
            }
        }
        return null;
    }
}
