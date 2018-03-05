package com.arturszybiak.codingtest.facade;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arturszybiak.codingtest.utils.ValidationRules.*;

/**
 * Facade with method for validation incoming request
 */
@Component
public class ValidationFacade {

    /**
     * Method iterate over transaction list and run validation
     *
     * @param transactionDataDTOList list of transaction to validation
     * @return list of validated transaction with status and messages
     */
    public List<TransactionDataDTO> validate(List<TransactionDataDTO> transactionDataDTOList) {
        transactionDataDTOList.forEach(this::validateTransaction);
        return transactionDataDTOList;
    }

    /**
     * Method run validation rules for given transaction
     *
     * @param transactionDataDTO transaction object
     */
    private void validateTransaction(TransactionDataDTO transactionDataDTO) {
        validateValueDateAgainstType(transactionDataDTO);
        validateSupportedCustomers(transactionDataDTO);
        validateCurrencyName(transactionDataDTO);
        validateExcerciseStartDateAmericanStyle(transactionDataDTO);
        validateExpiryDateAgainstDeliveryDate(transactionDataDTO);
        validatePremiumDateAgainstDeliveryDate(transactionDataDTO);
    }
}
