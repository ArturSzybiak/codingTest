package com.arturszybiak.codingtest.controllers;

import com.arturszybiak.codingtest.dto.TransactionDataDTO;
import com.arturszybiak.codingtest.facade.ValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is an endpoint for validation methods
 **/
@RestController
public class ValidationController {

    private final ValidationFacade validationFacade;

    @Autowired
    public ValidationController(ValidationFacade validationFacade) {
        this.validationFacade = validationFacade;
    }

    /**
     * method consume json send by post and pass data to validation
     *
     * @param transactionDataDTOList list of transaction
     * @return list of validated transaction with appropriate message and status
     * */
    @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<TransactionDataDTO> validate(@RequestBody List<TransactionDataDTO> transactionDataDTOList) {
        return validationFacade.validate(transactionDataDTOList);
    }

}
