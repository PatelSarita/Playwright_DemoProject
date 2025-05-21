package com.toolShop.stepDefs;

import com.microsoft.playwright.Locator;
import com.toolShop.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Register_StepDefs extends BasePage_stepDefs {


    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        loginPage.clickSignIn();
        loginPage.clickRegisterYourAccountBtn();
    }


    @When("The user enters valid input {string},{string},{string},{string},{string},{string},{string}, {string},{string},{string} and {string}")
    public void theUserEntersValidInputAnd(String firstName, String lastName, String dob, String street, String postalCode, String city, String state, String country, String phone, String email, String password) {
        registerPage.fillRegistrationForm(firstName, lastName, dob, street, postalCode, city, state, country, phone, email, password);
    }

    @And("clicks on Register button")
    public void clicksOnRegisterButton() {
        registerPage.clickRegisterBtn();
    }

    @Then("The user should be navigated to Login page")
    public void theUserShouldBeNavigatedToLoginPage() {
        assert loginPage.isLoginPageVisible();
    }

    @When("The user enters invalid input {string},{string},{string},{string},{string},{string},{string}, {string},{string},{string} and {string}")
    public void theUserEntersInvalidInputAnd(String firstName, String lastName, String dob, String street, String postalCode, String city, String state, String country, String phone, String email, String password) {
        registerPage.fillRegistrationForm(firstName, lastName, dob, street, postalCode, city, state, country, phone, email, password);
    }


    @Then("The registration should fail with appropriate validation {string}")
    public void theRegistrationShouldFailWithAppropriateValidation(String expectedErrorMsg) {
        BrowserUtils.assertErrorMessage(expectedErrorMsg);
    }


    @When("The user re-enters the existing information as {string},{string},{string},{string},{string},{string},{string}, {string},{string},{string} and {string}")
    public void theUserReEntersTheExistingInformationAsAnd(String firstName, String lastName, String dob, String street, String postalCode, String city, String state, String country, String phone, String email, String password) {
        registerPage.fillRegistrationForm(firstName, lastName, dob, street, postalCode, city, state, country, phone, email, password);

    }

    @When("The user enters invalid password {string}")
    public void theUserEntersInvalidPassword(String password) {
        registerPage.enterPassword(password);
    }
}
