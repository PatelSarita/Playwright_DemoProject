package com.toolShop.stepDefs;

import com.toolShop.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Purchase_StepDefs extends BasePage_stepDefs {

    @Given("the user is logged in with email {string} and password {string}")
    public void theUserIsLoggedInWithEmailAndPassword(String email, String password) {
        loginPage.clickSignIn();
        loginPage.login(email, password);
        loginPage.clickLoginBtn();
    }

    @When("the user is on My account page and clicks on the Category dropdown")
    public void theUserIsOnMyAccountPageAndClicksOnTheCategoryDropdown() {
        homePage.clickCategoryDropdown();
    }

    @And("select the {string} and {string} and clicks on Add to cart")
    public void selectTheAndAndClicksOnAddToCart(String product, String quantity) {
        productPage.addToCart(product, quantity);
    }

    @Then("The Product added to your shopping cart message {string} should be displayed")
    public void theProductAddedToYourShoppingCartMessageShouldBeDisplayed(String message) {
        BrowserUtils.assertProductAddedToCart(message);
    }

    @And("the user clicks on cart icon")
    public void theUserClicksOnCartIcon() {
        homePage.clickCartIcon();
    }

    @Then("the cart should display the {string} and {string} and {string} and correct {string}")
    public void theCartShouldDisplayTheAndAndAndCorrect(String expectedProduct, String expectedQuantity, String expectedPrice, String expectedTotalPrice) {
        BrowserUtils.assertProductsDetailsInCart(expectedProduct, expectedQuantity, expectedPrice, expectedTotalPrice);
    }


}
