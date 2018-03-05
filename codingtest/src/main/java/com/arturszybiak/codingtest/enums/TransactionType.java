package com.arturszybiak.codingtest.enums;

/**
 * transaction types
 * */
public enum TransactionType {
    FORWARD("Forward"),
    SPOT("Spot"),
    VANILLA_OPTION("VanillaOption");

    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String transactionType() {
        return transactionType;
    }

}
