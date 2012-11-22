package net.masterthought;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class StepDefs {

    @Given("^extemely (.+) important step$")
    public void test(){

    }

    @Given("^another extemely (.+) important step$")
    public void anotherOne(){

    }

    @When("^yet another one!$")
    public void yetAnotherOne(){

    }
}
