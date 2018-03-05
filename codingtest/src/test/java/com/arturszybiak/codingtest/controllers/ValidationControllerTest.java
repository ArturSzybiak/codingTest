package com.arturszybiak.codingtest.controllers;

import com.arturszybiak.codingtest.facade.ValidationFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ValidationController.class)
public class ValidationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ValidationFacade validationFacade;

    @Test
    public void validateTest() throws Exception {

        mvc.perform(post("/validate")
                .content("[{\n" +
                        "\t\t\"customer\": \"PLUTO1\",\n" +
                        "\t\t\"ccyPair\": \"EURUSD\",\n" +
                        "\t\t\"type\": \"Spot\",\n" +
                        "\t\t\"direction\": \"BUY\",\n" +
                        "\t\t\"tradeDate\": \"2016-08-11\",\n" +
                        "\t\t\"amount1\": 1000000.00,\n" +
                        "\t\t\"amount2\": 1120000.00,\n" +
                        "\t\t\"rate\": 1.12,\n" +
                        "\t\t\"valueDate\": \"2016-08-15\",\n" +
                        "\t\t\"legalEntity\": \"CS Zurich\",\n" +
                        "\t\t\"trader\": \"Johann Baumfiddler\"\n" +
                        "\t}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}