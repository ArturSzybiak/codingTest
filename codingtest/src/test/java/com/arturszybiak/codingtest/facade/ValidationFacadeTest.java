package com.arturszybiak.codingtest.facade;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.enums.ValidationStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.arturszybiak.codingtest.testutils.TestUtils.invalidTransactionDTO;
import static com.arturszybiak.codingtest.testutils.TestUtils.validTransactionDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationFacadeTest {

    @Autowired
    private ValidationFacade validationFacade;

    @Test
    public void validateTest(){
        List<TransactionDataDTO> listToValidate = new ArrayList<>();
        listToValidate.add(validTransactionDTO());
        listToValidate.add(invalidTransactionDTO());
        List<TransactionDataDTO> validatedList = validationFacade.validate(listToValidate);

        Assert.assertTrue(validatedList.size() > 0);
        Assert.assertEquals(ValidationStatus.APPROVED.validationStatus(), validatedList.get(0).getValidationResponseDTO().getValidationStatus());
        Assert.assertEquals(ValidationStatus.REJECT.validationStatus(), validatedList.get(1).getValidationResponseDTO().getValidationStatus());
        Assert.assertEquals(7, validatedList.get(1).getValidationResponseDTO().getValidationMessages().size());
    }
}
