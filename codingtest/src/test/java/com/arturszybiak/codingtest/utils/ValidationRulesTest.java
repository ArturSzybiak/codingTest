package com.arturszybiak.codingtest.utils;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.enums.TransactionStyle;
import com.arturszybiak.codingtest.enums.ValidationStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.GregorianCalendar;

import static com.arturszybiak.codingtest.testutils.TestUtils.invalidTransactionDTO;
import static com.arturszybiak.codingtest.testutils.TestUtils.validTransactionDTO;
import static com.arturszybiak.codingtest.utils.ValidationRules.*;

@RunWith(SpringRunner.class)
public class ValidationRulesTest {

    @Test
    public void validateValueDateAgeinstTypeTest() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        ValidationRules.validateValueDateAgainstType(transactionDataDTO1);
        Assert.assertEquals(transactionDataDTO1.getValidationResponseDTO().getValidationStatus(), ValidationStatus.APPROVED.validationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        ValidationRules.validateValueDateAgainstType(transactionDataDTO2);
        Assert.assertEquals(transactionDataDTO2.getValidationResponseDTO().getValidationStatus(), ValidationStatus.REJECT.validationStatus());

    }

    @Test
    public void validateSupportedCustomers() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        ValidationRules.validateSupportedCustomers(transactionDataDTO1);
        Assert.assertEquals(transactionDataDTO1.getValidationResponseDTO().getValidationStatus(), ValidationStatus.APPROVED.validationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        ValidationRules.validateSupportedCustomers(transactionDataDTO2);
        Assert.assertEquals(transactionDataDTO2.getValidationResponseDTO().getValidationStatus(), ValidationStatus.REJECT.validationStatus());
        Assert.assertTrue(transactionDataDTO2.getValidationResponseDTO().getValidationMessages().size() > 0);
    }

    @Test
    public void validateCurrencyNameTest() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        ValidationRules.validateCurrencyName(transactionDataDTO1);
        Assert.assertEquals(transactionDataDTO1.getValidationResponseDTO().getValidationStatus(), ValidationStatus.APPROVED.validationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        ValidationRules.validateCurrencyName(transactionDataDTO2);
        Assert.assertEquals(transactionDataDTO2.getValidationResponseDTO().getValidationStatus(), ValidationStatus.REJECT.validationStatus());
        Assert.assertTrue(transactionDataDTO2.getValidationResponseDTO().getValidationMessages().size() > 0);
    }

    @Test
    public void validateExcerciseStartDateAmericanStyleTest() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        validateExcerciseStartDateAmericanStyle(transactionDataDTO1);
        Assert.assertEquals(transactionDataDTO1.getValidationResponseDTO().getValidationStatus(), ValidationStatus.APPROVED.validationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        validateExcerciseStartDateAmericanStyle(transactionDataDTO2);
        Assert.assertEquals(ValidationStatus.REJECT.validationStatus(), transactionDataDTO2.getValidationResponseDTO().getValidationStatus());
        Assert.assertTrue(transactionDataDTO2.getValidationResponseDTO().getValidationMessages().size() > 0);

        TransactionDataDTO transactionDataDTO3 = new TransactionDataDTO();
        transactionDataDTO3.setStyle(TransactionStyle.AMERICAN.transactionStyle());
        transactionDataDTO3.setExcerciseStartDate(new GregorianCalendar(2016, 8, 9).getTime());
        transactionDataDTO3.setTradeDate(new GregorianCalendar(2016, 8, 11).getTime());
        transactionDataDTO3.setExpiryDate(new GregorianCalendar(2016, 8, 19).getTime());

        validateExcerciseStartDateAmericanStyle(transactionDataDTO3);
        Assert.assertEquals(transactionDataDTO3.getValidationResponseDTO().getValidationStatus(), ValidationStatus.REJECT.validationStatus());
        Assert.assertTrue(transactionDataDTO3.getValidationResponseDTO().getValidationMessages().size() > 0);
    }

    @Test
    public void validateExpiryDateAgainstDeliveryDateTest() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        validateExpiryDateAgainstDeliveryDate(transactionDataDTO1);
        Assert.assertEquals(transactionDataDTO1.getValidationResponseDTO().getValidationStatus(), ValidationStatus.APPROVED.validationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        validateExpiryDateAgainstDeliveryDate(transactionDataDTO2);
        Assert.assertEquals(transactionDataDTO2.getValidationResponseDTO().getValidationStatus(), ValidationStatus.REJECT.validationStatus());
        Assert.assertTrue(transactionDataDTO2.getValidationResponseDTO().getValidationMessages().size() > 0);
    }

    @Test
    public void validatePremiumDateAgainstDeliveryDateTest() {
        TransactionDataDTO transactionDataDTO1 = validTransactionDTO();

        validatePremiumDateAgainstDeliveryDate(transactionDataDTO1);
        Assert.assertEquals(ValidationStatus.APPROVED.validationStatus(), transactionDataDTO1.getValidationResponseDTO().getValidationStatus());

        TransactionDataDTO transactionDataDTO2 = invalidTransactionDTO();

        validatePremiumDateAgainstDeliveryDate(transactionDataDTO2);
        Assert.assertEquals(ValidationStatus.REJECT.validationStatus(), transactionDataDTO2.getValidationResponseDTO().getValidationStatus());
    }


}