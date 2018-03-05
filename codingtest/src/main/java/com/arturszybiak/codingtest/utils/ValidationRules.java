package com.arturszybiak.codingtest.utils;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.enums.*;

import java.util.Calendar;
import java.util.Objects;

import static com.arturszybiak.codingtest.utils.ValidationUtils.rejectMessage;
import static com.arturszybiak.codingtest.utils.ValidationUtils.setValidationStatusApproved;

/**
 * Class contains validation rules
 * */
public class ValidationRules {

    private ValidationRules() {
    }

    /**
     * validate the value date for types SPOT and FORWARD
     * two rules are apply valueDate must be before tradeDate
     * and value date cannot be in weekend
     *
     * @param transactionDataDTO transaction object
     */
    public static void validateValueDateAgainstType(TransactionDataDTO transactionDataDTO) {
        if (checkProductType(transactionDataDTO, TransactionType.SPOT) || checkProductType(transactionDataDTO, TransactionType.FORWARD)) {
            validateValueDateAgeinstTradeDate(transactionDataDTO);
            validateValueDateAgeinstDaysOff(transactionDataDTO);
        }
    }

    /**
     * check transaction customer name
     *
     * @param transactionDataDTO transaction object
     * */
    public static void validateSupportedCustomers(TransactionDataDTO transactionDataDTO) {
        SupportedCustomers customers = SupportedCustomers.fromString(transactionDataDTO.getCustomer());
        if (customers == null) {
            rejectMessage(transactionDataDTO, ValidationMessage.CUSTOMER_NOT_SUPPORTED);
        }else {
            setValidationStatusApproved(transactionDataDTO);
        }
    }

    /**
     * validate currency name for payCcy and premiumCcy field
     *
     * @param transactionDataDTO  transaction object
     * */
    public static void validateCurrencyName(TransactionDataDTO transactionDataDTO) {
        if (transactionDataDTO.getPayCcy() != null) {
            validateCurrency(transactionDataDTO, transactionDataDTO.getPayCcy());
        }
        if (transactionDataDTO.getPremiumCcy() != null) {
            validateCurrency(transactionDataDTO, transactionDataDTO.getPremiumCcy());
        }
    }

    /**
     * validate excerciseStartDate if style is AMERICAN
     *
     * @param transactionDataDTO transaction object
     * */
    public static void validateExcerciseStartDateAmericanStyle(TransactionDataDTO transactionDataDTO) {
        if (transactionDataDTO.getStyle() != null
                && Objects.requireNonNull(TransactionStyle.fromString(transactionDataDTO.getStyle())).equals(TransactionStyle.AMERICAN)) {
            validateExcerciseStartDate(transactionDataDTO);
        }
    }

    /**
     * expiry date against deliveryDate, expiry date shall be before delivery date
     *
     * @param transactionDataDTO transaction object
     */
    public static void validateExpiryDateAgainstDeliveryDate(TransactionDataDTO transactionDataDTO) {
        if (checkTransactionStyle(transactionDataDTO)) {
            if ((transactionDataDTO.getExpiryDate() != null && transactionDataDTO.getDeliveryDate() != null)
                    && !transactionDataDTO.getExpiryDate().before(transactionDataDTO.getDeliveryDate())) {
                rejectMessage(transactionDataDTO, ValidationMessage.EXPIRY_DATA_BEFORE_DELIVERY_DATE);
            }else {
                setValidationStatusApproved(transactionDataDTO);
            }
        }
    }

    /**
     * validate premiumDate against deliveryDate, premium date shall be before delivery date
     *
     * @param transactionDataDTO transaction object
     * */
    public static void validatePremiumDateAgainstDeliveryDate(TransactionDataDTO transactionDataDTO) {
        if (checkTransactionStyle(transactionDataDTO)) {
            if ((transactionDataDTO.getPremiumDate() != null && transactionDataDTO.getDeliveryDate() != null)
                    && !transactionDataDTO.getPremiumDate().before(transactionDataDTO.getDeliveryDate())) {
                rejectMessage(transactionDataDTO, ValidationMessage.PREMIUM_DATA_BEFORE_DELIVERY_DATE);
            }else {
                setValidationStatusApproved(transactionDataDTO);
            }
        }
    }

    /**
     * check is style AMERICAN or EUROPEAN
     *
     * @param transactionDataDTO transaction object
     * @return true if style is AMERICAN or EUROPEAN
     */
    private static boolean checkTransactionStyle(TransactionDataDTO transactionDataDTO) {
        return transactionDataDTO.getStyle() != null
                && (transactionDataDTO.getStyle().equals(TransactionStyle.AMERICAN.transactionStyle())
                || transactionDataDTO.getStyle().equals(TransactionStyle.EUROPEAN.transactionStyle()));
    }

    /**
     * validate excerciseStartDate, validation pass if excerciseStartDate is after tradeDate
     * and before expiryDate
     *
     * @param transactionDataDTO transaction object
     */
    private static void validateExcerciseStartDate(TransactionDataDTO transactionDataDTO) {
        if (transactionDataDTO.getExcerciseStartDate() != null && transactionDataDTO.getTradeDate() != null) {
            if (!transactionDataDTO.getExcerciseStartDate().after(transactionDataDTO.getTradeDate())
                    || !transactionDataDTO.getExcerciseStartDate().before(transactionDataDTO.getExpiryDate())) {
                rejectMessage(transactionDataDTO, ValidationMessage.INCORRECT_EXCERCISE_START_DATE);
            }else {
                setValidationStatusApproved(transactionDataDTO);
            }
        } else {
            rejectMessage(transactionDataDTO, ValidationMessage.MISSING_EXCERCISE_START_DATE_OR_TRADE_DATE);
        }
    }

    /**
     * validate currency name with name stored in library, set appropriate message in transactionDataDTO
     *
     * @param transactionDataDTO transaction object
     * @param currencyName       name for validation
     */
    private static void validateCurrency(TransactionDataDTO transactionDataDTO, String currencyName) {
        if (currencyName.length() == 3 && CurrencyCodes.fromCountryCode(currencyName.substring(0, 2)) != null) {
            if (!Objects.requireNonNull(CurrencyCodes.fromCountryCode(currencyName.substring(0, 2)))
                    .getCurrencyFirstLetter().equals(currencyName.substring(2))) {
                rejectMessage(transactionDataDTO, ValidationMessage.CURRENCY_WRONG_FORMAT);
            }else{
                setValidationStatusApproved(transactionDataDTO);
            }
        } else {
            rejectMessage(transactionDataDTO, ValidationMessage.CURRENCY_WRONG_FORMAT);
        }
    }

    /**
     * check transaction type with given type
     *
     * @param transactionDataDTO transaction object
     * @param transactionType    transaction type
     * @return true if transaction object type is equal given type
     */
    private static boolean checkProductType(TransactionDataDTO transactionDataDTO, TransactionType transactionType) {
        return transactionType.transactionType().equalsIgnoreCase(transactionDataDTO.getType());
    }

    /**
     * validate valueDate against tradeDate, valueDate must be before tradeDate
     * or reject message will be added to response object
     *
     * @param transactionDataDTO transaction object
     */
    private static void validateValueDateAgeinstTradeDate(TransactionDataDTO transactionDataDTO) {
        if (!transactionDataDTO.getValueDate().before(transactionDataDTO.getTradeDate())) {
            rejectMessage(transactionDataDTO, ValidationMessage.VALUE_DATE_BEFORE_TRADE_DATE);
        } else {
            setValidationStatusApproved(transactionDataDTO);
        }
    }

    /**
     * method check is valueDate in weekend if jest set appropriate message
     *
     * @param transactionDataDTO transaction object
     */
    private static void validateValueDateAgeinstDaysOff(TransactionDataDTO transactionDataDTO) {
        Calendar valueCalendar = Calendar.getInstance();
        valueCalendar.setTime(transactionDataDTO.getValueDate());
        int day = valueCalendar.get(Calendar.DAY_OF_WEEK);
        if (day == 6 || day == 7) {
            rejectMessage(transactionDataDTO, ValidationMessage.VALUE_DATE_CANNOT_BE_IN_FREE_DAY);
        } else {
            setValidationStatusApproved(transactionDataDTO);
        }
    }
}
