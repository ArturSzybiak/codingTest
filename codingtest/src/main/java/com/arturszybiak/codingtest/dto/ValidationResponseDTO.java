package com.arturszybiak.codingtest.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponseDTO {

    private String validationStatus;
    private List<String> validationMessages;

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public List<String> getValidationMessages() {
        if (validationMessages == null)
            this.setValidationMessages(new ArrayList<>());
        return validationMessages;
    }

    public void setValidationMessages(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }
}
