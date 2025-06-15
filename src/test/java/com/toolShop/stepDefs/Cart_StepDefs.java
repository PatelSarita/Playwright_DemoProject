package com.toolShop.stepDefs;

import com.toolShop.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart_StepDefs extends BasePage_stepDefs{

    @When("the user increases or decrease the quantity of {string} to {string}")
    public void theUserIncreasesOrDecreaseTheQuantityOfTo(String product, String newQuantity) {
        checkoutPage.updateProductQuantityInTheCart(product,newQuantity);
    }

    @Then("the success message {string} should be displayed with {string} and {string} and {string} and correct {string}")
    public void theSuccessMessageShouldBeDisplayedWithAndAndAndCorrect(String message, String product, String newQty, String newPrice, String newTotal) {
        BrowserUtils.assertMessage(message);
        page.waitForTimeout(1000);
        checkoutPage.assertProductsDetailsInCart(product,newQty,newPrice,newTotal);
    }
    @When("the user removes the product from the cart")
    public void theUserRemovesTheProductFromTheCart() {
        checkoutPage.removeProductFromTheCart();
    }

    @Then("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        BrowserUtils.assertMessage(message);
    }


    @When("the user removes all the product from the cart")
    public void theUserRemovesAllTheProductFromTheCart() {
        checkoutPage.removeAllProductsFromCart();

    }

    @Then("the {string} message should be displayed after deleting each item")
    public void theMessageShouldBeDisplayedAfterDeletingEachItem(String message) {
        checkoutPage.assertAllMessages(message);
    }

    @And("the Proceed to checkout button should be not visible")
    public void theProceedToCheckoutButtonShouldBeNotVisible() {
        checkoutPage.assertCheckoutButtonIsNotVisible();
    }



}
