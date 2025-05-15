package com.toolShop.stepDefs;

import io.cucumber.java.en.Given;

public class Register_StepDefs extends BasePage_stepDefs {


    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
       loginPage.clickSignIn();
       loginPage.clickRegisterYourAccountBtn();
    }
}
