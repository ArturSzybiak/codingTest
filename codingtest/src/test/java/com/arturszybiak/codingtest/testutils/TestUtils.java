package com.arturszybiak.codingtest.testutils;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.enums.SupportedCustomers;
import com.arturszybiak.codingtest.enums.TransactionStyle;

import java.util.GregorianCalendar;

public class TestUtils {

    private TestUtils() {
    }

    public static TransactionDataDTO validTransactionDTO(){
        TransactionDataDTO transactionDataDTO = new TransactionDataDTO();

        transactionDataDTO.setType("SPOT");
        transactionDataDTO.setValueDate(new GregorianCalendar(2016, 8, 13).getTime());
        transactionDataDTO.setTradeDate(new GregorianCalendar(2016, 8, 15).getTime());
        transactionDataDTO.setCustomer(SupportedCustomers.PlUTO1.custommerName());
        transactionDataDTO.setPayCcy("USD");
        transactionDataDTO.setPremiumCcy("USD");
        transactionDataDTO.setStyle(TransactionStyle.AMERICAN.transactionStyle());
        transactionDataDTO.setExcerciseStartDate(new GregorianCalendar(2016, 8, 16).getTime());
        transactionDataDTO.setExpiryDate(new GregorianCalendar(2016, 8, 19).getTime());
        transactionDataDTO.setDeliveryDate(new GregorianCalendar(2016, 8, 20).getTime());
        transactionDataDTO.setPremiumDate(new GregorianCalendar(2016, 8, 11).getTime());

        return transactionDataDTO;
    }

    public static TransactionDataDTO invalidTransactionDTO(){
        TransactionDataDTO transactionDataDTO = new TransactionDataDTO();

        transactionDataDTO.setType("SPOT");
        transactionDataDTO.setValueDate(new GregorianCalendar(2016, 8, 20).getTime());
        transactionDataDTO.setCustomer("NOT_SUPPORTED_CUSTOMER");
        transactionDataDTO.setPayCcy("USG");
        transactionDataDTO.setPremiumCcy("USG");
        transactionDataDTO.setExcerciseStartDate(new GregorianCalendar(2016, 8, 20).getTime());
        transactionDataDTO.setTradeDate(new GregorianCalendar(2016, 8, 11).getTime());
        transactionDataDTO.setExpiryDate(new GregorianCalendar(2016, 8, 20).getTime());
        transactionDataDTO.setDeliveryDate(new GregorianCalendar(2016, 8, 19).getTime());
        transactionDataDTO.setStyle(TransactionStyle.AMERICAN.transactionStyle());
        transactionDataDTO.setPremiumDate(new GregorianCalendar(2016, 8, 20).getTime());

        return transactionDataDTO;
    }
}
