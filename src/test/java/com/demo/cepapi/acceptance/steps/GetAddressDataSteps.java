package com.demo.cepapi.acceptance.steps;

import com.demo.cepapi.service.CepService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.inject.Inject;

public class GetAddressDataSteps {

    @Inject
    public GetAddressDataSteps(CepService cepService) {
    }


    @Given("isvalid CEP is provided")
    public void isvalid_cep_is_provided() {
    }

    @When("its search a CEP")
    public void its_search_a_cep() {
    }

    @Then("returns the address data with freight of the region")
    public void returns_the_address_data_with_freight_of_the_region() {
    }
}
