package com.toolShop.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Payment_StepDefs extends BasePage_stepDefs {


    @Given("the user is on the payment page")
    public void theUserIsOnThePaymentPage() {
        loginPage.clickSignIn();
        loginPage.login("priya@gmail.com", "WVhft76@99");
        loginPage.clickLoginBtn();
        homePage.clickCategoryDropdown();
        homePage.selectProductCategory("Hand Tools");
        productPage.addToCart("Bolt Cutters", "1");
        homePage.clickCartIcon();
        checkoutPage.clickProceedToCheckoutBtn1();
        checkoutPage.clickProceedToCheckoutBtn2();
        checkoutPage.clickProceedToCheckoutBtn3();
    }

    @When("the user selects {string} as the payment method")
    public void theUserSelectsAsThePaymentMethod(String paymentType) {
        checkoutPage.selectBankTransferPaymentType(paymentType);
    }

    @And("the user do not enters bank name {string}, account name {string}, and account number {string}")
    public void theUserDoNotEntersBankNameAccountNameAndAccountNumber(String bankName, String accountName, String accountNumber) {
        checkoutPage.invalidBankDetailsInput(bankName, accountName, accountNumber);
    }

    @Then("the Confirm button should be disabled")
    public void theConfirmButtonShouldBeDisabled() {
        checkoutPage.isConfirmButtonDisabled();
    }

    @And("enters {string} as card number, {string} as expiry, {string} as CVV, and {string} as cardholder")
    public void entersAsCardNumberAsExpiryAsCVVAndAsCardholder(String cardNumber, String expiry, String cvv, String cardholderName) {
        checkoutPage.invalidCreditCardInput(cardNumber,expiry,cvv,cardholderName);
    }

    @And("enters {string} as the gift card number and {string} as the validation code")
    public void entersAsTheGiftCardNumberAndAsTheValidationCode(String giftCardNo, String validationCode) {
        checkoutPage.invalidGiftCardInput(giftCardNo,validationCode);
    }


}
