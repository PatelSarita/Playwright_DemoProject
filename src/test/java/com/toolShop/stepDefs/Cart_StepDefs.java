package com.toolShop.stepDefs;

import com.toolShop.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart_StepDefs extends BasePage_stepDefs{

    @When("the user increases or decrease the quantity of {string} to {string}")
    public void theUserIncreasesOrDecreaseTheQuantityOfTo(String product, String newQuantity) {
        cartPage.updateProductQuantityInTheCart(product,newQuantity);
    }

    @Then("the success message {string} should be displayed with {string} and {string} and {string} and correct {string}")
    public void theSuccessMessageShouldBeDisplayedWithAndAndAndCorrect(String message, String product, String newQty, String newPrice, String newTotal) {
        BrowserUtils.assertMessage(message);
        page.waitForTimeout(1000);
        cartPage.assertProductsDetailsInCart(product,newQty,newPrice,newTotal);
    }
    @When("the user removes the product from the cart")
    public void theUserRemovesTheProductFromTheCart() {
        cartPage.removeProductFromTheCart();
    }

    @Then("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        BrowserUtils.assertMessage(message);
    }



}
