package com.toolShop.stepDefs;

import com.toolShop.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDefs extends BasePage_stepDefs{

    @Given("the user is on the Login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.clickSignIn();
    }

    @When("The user enters valid {string} and {string}")
    public void theUserEntersValidAnd(String email, String password) {
        loginPage.login(email, password);
    }
    @And("clicks on Login button")
    public void clicksOnLoginButton() {
        loginPage.clickLoginBtn();
    }


    @Then("The user should be able to login and redirected to My account page")
    public void theUserShouldBeAbleToLoginAndRedirectedToMyAccountPage() {
        assert myAccountPage.isMyAccountPageVisible();
    }

    @Then("The user should only see  masked password")
    public void theUserShouldOnlySeeMaskedPassword() {
        assert loginPage.isPasswordMasked();
    }

    @When("The user enters invalid {string} or {string}")
    public void theUserEntersInvalidOr(String email, String password) {
        loginPage.login(email,password);

    }


    @Then("The user should get the appropriate {string}")
    public void theUserShouldGetTheAppropriate(String expectedErrorMsg) {
        BrowserUtils.assertMessage(expectedErrorMsg);
    }



}
