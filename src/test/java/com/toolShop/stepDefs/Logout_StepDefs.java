package com.toolShop.stepDefs;

import com.toolShop.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout_StepDefs extends BasePage_stepDefs {


    @When("the user is on My Account page and click on users name dropdown")
    public void theUserIsOnMyAccountPageAndClickOnUsersNameDropdown() {
        myAccountPage.isMyAccountPageVisible();
        myAccountPage.clickUserNameDropdown();

    }

    @And("select logout option")
    public void selectLogoutOption() {
        myAccountPage.selectSignOutOption();

    }

    @Then("the user should logged out successfully and navigated to login page")
    public void theUserShouldLoggedOutSuccessfullyAndNavigatedToLoginPage() {
        loginPage.isLoginPageVisible();
    }
}
