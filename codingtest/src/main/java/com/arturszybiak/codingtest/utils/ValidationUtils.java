package com.arturszybiak.codingtest.utils;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.enums.ValidationMessage;
import com.arturszybiak.codingtest.enums.ValidationStatus;

/**
 * Utility methods for validation logic
 */
class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * method set approved status if there was no approved status before
     *
     * @param transactionDataDTO transaction object
     */
    static TransactionDataDTO setValidationStatusApproved(TransactionDataDTO transactionDataDTO) {
        String status = transactionDataDTO.getValidationResponseDTO().getValidationStatus();
        if (!(status != null && status.equals(ValidationStatus.REJECT.validationStatus()))) {
            transactionDataDTO.getValidationResponseDTO().setValidationStatus(ValidationStatus.APPROVED.validationStatus());
        }
        return transactionDataDTO;
    }

    /**
     * set reject status and add error message to transactionDataDTO
     *
     * @param transactionDataDTO transaction object
     * @param validationMessage  error message
     */
    static void rejectMessage(TransactionDataDTO transactionDataDTO, ValidationMessage validationMessage) {
        setValidationStatusReject(transactionDataDTO);
        setValidationMessage(transactionDataDTO, validationMessage.validationMessage());
    }

    /**
     * set reject status
     *
     * @param transactionDataDTO transaction object
     */
    private static TransactionDataDTO setValidationStatusReject(TransactionDataDTO transactionDataDTO) {
        transactionDataDTO.getValidationResponseDTO().setValidationStatus(ValidationStatus.REJECT.validationStatus());
        return transactionDataDTO;
    }

    /**
     * set message
     *
     * @param transactionDataDTO transaction object
     * @param message            error message
     */
    private static TransactionDataDTO setValidationMessage(TransactionDataDTO transactionDataDTO, String message) {
        transactionDataDTO.getValidationResponseDTO().getValidationMessages().add(message);
        return transactionDataDTO;
    }
}
