package com.toolShop.stepDefs;

import com.toolShop.utilities.BrowserUtils;
import io.cucumber.datatable.DataTable;
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

    @And("selects the {string} category")
    public void selectsTheCategory(String category) {
        homePage.selectProductCategory(category);
    }

    @Then("the user should be navigated to the related {string} page")
    public void theUserShouldBeNavigatedToTheRelatedPage(String expectedPage) {
        assert productPage.isProductPageVisible(expectedPage);
    }

    @And("select the {string} and {string} and clicks on Add to cart")
    public void selectTheAndAndClicksOnAddToCart(String product, String quantity) {
        productPage.addToCart(product, quantity);
    }

    @Then("The Product added to your shopping cart message {string} should be displayed")
    public void theProductAddedToYourShoppingCartMessageShouldBeDisplayed(String message) {
        BrowserUtils.assertMessage(message);
    }

    @And("the user clicks on cart icon")
    public void theUserClicksOnCartIcon() {
        homePage.clickCartIcon();
    }

    @Then("the cart should display the {string} and {string} and {string} and correct {string}")
    public void theCartShouldDisplayTheAndAndAndCorrect(String expectedProduct, String expectedQuantity, String expectedPrice, String expectedTotalPrice) {
        cartPage.assertProductsDetailsInCart(expectedProduct, expectedQuantity, expectedPrice, expectedTotalPrice);
    }


    @And("user clicks on first Proceed to checkout button")
    public void userClicksOnFirstProceedToCheckoutButton() {
        cartPage.clickProceedToCheckoutBtn1();
    }


    @Then("the user sees the message {string}")
    public void theUserSeesTheMessage(String expectedMessage) {
        cartPage.asserCartLoggedInMsg(expectedMessage);
    }

    @And("user clicks on second Proceed to checkout button")
    public void userClicksOnSecondProceedToCheckoutButton() {
        cartPage.clickProceedToCheckoutBtn2();
    }

    @Then("the Billing Address should be displayed")
    public void theBillingAddressShouldBeDisplayed() {
        cartPage.assertBillingAddressIsVisible();
    }

    @And("user clicks on third Proceed to checkout button")
    public void userClicksOnThirdProceedToCheckoutButton() {
        cartPage.clickProceedToCheckoutBtn3();
    }

    @And("The user chooses {string} payment method and clicks on Confirm button")
    public void theUserChoosesPaymentMethodAndClicksOnConfirmButton(String paymentMethod) {
        cartPage.selectAndFillPaymentDetails(paymentMethod);
        cartPage.clickConfirmBtn();
    }


    @Then("The user should see {string} message and clicks on Confirm button again")
    public void theUserShouldSeeMessageAndClicksOnConfirmButtonAgain(String expectedMessage) {
        BrowserUtils.assertMessage(expectedMessage);
        cartPage.clickConfirmBtn();
    }


    @And("The user should see {string} order confirmation message")
    public void theUserShouldSeeOrderConfirmationMessage(String expectedMessage) {
        BrowserUtils.assertMessage(expectedMessage);
    }


    @And("the user clicks on Category dropdown")
    public void theUserClicksOnCategoryDropdown() {
        homePage.clickCategoryDropdown();
    }

    @Then("the cart should display:")
    public void theCartShouldDisplay(DataTable table) {
        cartPage.assertCartProductDataTable(table);

    }

    @And("the cart also displays the {string} cart total")
    public void theCartAlsoDisplaysTheCartTotal(String expectedCartTotal) {
        cartPage.assertCartTotal(expectedCartTotal);
    }


    @Then("the log in page should be displayed and the user enters valid {string} and {string} and clicks on login button")
    public void theLogInPageShouldBeDisplayedAndTheUserEntersValidAndAndClicksOnLoginButton(String email, String password) {
        loginPage.login(email, password);
        loginPage.clickLoginBtn();
    }


}