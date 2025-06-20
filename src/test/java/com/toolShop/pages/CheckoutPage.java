package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutPage extends BasePage {

    private final Locator proceedToCheckoutBtn1;
    private final Locator proceedToCheckoutBtn2;
    private final Locator proceedToCheckoutBtn3;
    private final Locator loggedInMsg;
    private final Locator streetInput;
    private final Locator cityInput;
    private final Locator stateInput;
    private final Locator countryInput;
    private final Locator postalCodeInput;
    private final Locator deleteButton;
    private final Locator paymentDropdown;
    private final Locator confirmBtn;
    private final Locator inputBankName;
    private final Locator inputAccountName;
    private final Locator inputAccountNumber;
    private final Locator inputCreditCardNo;
    private final Locator inputExpiryDate;
    private final Locator inputCvv;
    private final Locator inputCardHolderName;
    private final Locator inputGiftCardNumber;
    private final Locator inputValidationCode;
    private final Locator installmentDropdown;


    public CheckoutPage(Page page) {
        super(page);
        this.proceedToCheckoutBtn1 = page.locator("//button[text()='Proceed to checkout']");
        this.proceedToCheckoutBtn2 = page.locator("[data-test='proceed-2']");
        this.proceedToCheckoutBtn3 = page.locator("[data-test='proceed-3']");
        this.loggedInMsg = page.locator("//p[@class='ng-star-inserted']");
        this.streetInput = page.locator("[data-test='street']");
        this.cityInput = page.locator("[data-test='city']");
        this.stateInput = page.locator("[data-test='state']");
        this.countryInput = page.locator("[data-test='country']");
        this.postalCodeInput = page.locator("[data-test='postal_code']");
        this.deleteButton = page.locator("[data-icon='xmark']");
        this.paymentDropdown = page.locator("#payment-method");
        this.confirmBtn = page.locator("[data-test='finish']");
        this.inputBankName = page.locator("input[placeholder='Bank Name']");
        this.inputAccountName = page.locator("input[placeholder='Account Name']");
        this.inputAccountNumber = page.locator("input[placeholder='Account Number']");
        this.inputCreditCardNo = page.locator("input[placeholder='Credit Card Number']");
        this.inputExpiryDate = page.locator("input[placeholder='Expiration Date']");
        this.inputCvv = page.locator("input[placeholder='CVV']");
        this.inputCardHolderName = page.locator("input[placeholder='Card Holder Name']");
        this.inputGiftCardNumber = page.locator("input[placeholder='Gift Card Number']");
        this.inputValidationCode = page.locator("input[placeholder='Validation Code']");
        this.installmentDropdown = page.locator("#monthly_installments");
    }

    public void assertProductsDetailsInCart(String expectedProduct, String expectedQuantity, String expectedPrice, String expectedTotalPrice) {
        String actualProductName = page.locator("[data-test='product-title']").innerText().trim().replace("\u00A0", "").strip();
        String actualQuantity = page.locator("[data-test='product-quantity']").inputValue().trim();
        String actualPrice = page.locator("[data-test='product-price']").innerText().trim().replace("\u00A0", "").strip();
        String actualTotal = page.locator("[data-test='line-price']").innerText().trim().replace("\u00A0", "").strip();
        assertEquals(expectedProduct, actualProductName);
        assertEquals(expectedQuantity, actualQuantity);
        assertEquals(expectedPrice, actualPrice);
        assertEquals(expectedTotalPrice, actualTotal);

    }

    public void assertCartProductDataTable(DataTable table) {
        List<Map<String, String>> expectedProducts = table.asMaps(String.class, String.class);

        for (Map<String, String> expected : expectedProducts) {
            String productName = expected.get("Product Name");
            String quantity = expected.get("Product Quantity");
            String price = expected.get("Product Price");
            String total = expected.get("Total Price");

            Locator productRow = page.locator("tr:has(span[data-test='product-title']:has-text('" + productName + "'))");
            productRow.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            PlaywrightAssertions.assertThat(productRow.locator("span[data-test='product-title']")).hasText(productName);

            String quantityValue = productRow.locator("input[data-test='product-quantity']").inputValue();
            assertEquals(quantity, quantityValue);

            PlaywrightAssertions.assertThat(productRow.locator("span[data-test='product-price']")).hasText(price);

            PlaywrightAssertions.assertThat(productRow.locator("span[data-test='line-price']")).hasText(total);
        }
    }

    public void assertCartTotal(String expectedPrice) {
        String actualCartTotal = page.locator("[data-test='cart-total']").innerText().trim().replace("\u00A0", "").strip();
        assertThat(actualCartTotal, containsString(expectedPrice));

    }

    public void clickProceedToCheckoutBtn1() {
        proceedToCheckoutBtn1.click();
    }

    public void clickProceedToCheckoutBtn2() {
        proceedToCheckoutBtn2.click();
    }

    public void clickProceedToCheckoutBtn3() {
        proceedToCheckoutBtn3.click();
    }

    public void asserCartLoggedInMsg(String expectedMessage) {
        String actualMessage = loggedInMsg.textContent().trim();
        assertThat(actualMessage, containsString(expectedMessage));
    }

    public void assertBillingAddressIsVisible() {
        page.waitForTimeout(2000);

        assertThat("Street should be filled", streetInput.inputValue(), equalTo("Albert Strasse"));
        assertThat("City should be filled", cityInput.inputValue(), equalTo("Berlin"));
        assertThat("State should be filled", stateInput.inputValue(), equalTo("Berlin"));
        assertThat("Country should be filled", countryInput.inputValue(), equalTo("DE"));
        assertThat("Postal Code should be filled", postalCodeInput.inputValue(), equalTo("60043"));
    }


    public void selectAndFillPaymentDetails(String paymentType, String installmentOption) {
        paymentDropdown.selectOption(paymentType);
        page.waitForTimeout(1000);
        switch (paymentType) {
            case "Bank Transfer":
                inputBankName.fill("Deutsch Bank");
                inputAccountName.fill("Priya Test");
                inputAccountNumber.fill("123456789101");
                break;
            case "Cash on Delivery":
                System.out.println("COD selected, no extra input needed.");
                break;

            case "Credit Card":
                inputCreditCardNo.fill("4111-1111-2222-3333");
                inputExpiryDate.fill("12/2027");
                inputCvv.fill("123");
                inputCardHolderName.fill("Priya Test");
                break;
            case "Buy Now Pay Later":
                if (installmentDropdown.isVisible()) {
                    installmentDropdown.selectOption(new SelectOption().setLabel(installmentOption));
                    System.out.println("BNPL with " + installmentOption + " selected.");
                } else {
                    throw new RuntimeException("Installment dropdown not found for BNPL.");
                }
                break;

            case "Gift Card":
                inputGiftCardNumber.fill("GIFTCARD123");
                inputValidationCode.fill("1212");
                break;


            default:
                throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
    }

    public void clickConfirmBtn() {
        confirmBtn.click();
    }

    public void updateProductQuantityInTheCart(String product, String qtyInput) {
        Locator row = page.locator("tr:has(span[data-test='product-title']:has-text('" + product + "'))");
        Locator cartQuatity = row.locator("input[data-test='product-quantity']");
        cartQuatity.fill(qtyInput);

        Locator field = page.locator("//div[@class='wizard-steps horizontal']");
        field.click();
    }

    public void removeProductFromTheCart() {
        deleteButton.waitFor();
        deleteButton.click();
    }
    public void removeAllProductsFromCart(){
        while(deleteButton.count() > 0){
            deleteButton.nth(0).click();
            page.waitForTimeout(1000);
        }
    }

    public void assertAllMessages(String message){
        List<String> messages = page.locator("//*[contains(text(), '" + message + "')]").allTextContents();
        for(String msg : messages){
            assertEquals(message,msg.trim());
            page.waitForTimeout(1000);
        }
    }
    public void assertCheckoutButtonIsNotVisible(){
        Locator proceedToCheckout = page.locator("text=Proceed to checkout");
        assertThat(proceedToCheckout).isHidden();
    }

    public void selectBankTransferPaymentType(String paymentType) {
        paymentDropdown.selectOption(paymentType);
    }

    public void invalidBankDetailsInput(String bankName, String accountName, String accountNumber) {
        if (bankName != null) { //prevent any potential issue if any field is null
            inputBankName.fill("temp");
            inputBankName.clear();
            inputBankName.fill(bankName);
        }

        if (accountName != null) {
            inputAccountName.fill("temp");
            inputAccountName.clear();
            inputAccountName.fill(accountName);
        }

        if (accountNumber != null) {
            inputAccountNumber.fill("temp");
            inputAccountNumber.clear();
            inputAccountNumber.fill(accountNumber);
        }
    }

    public void isConfirmButtonDisabled() {
        boolean isDisabled = confirmBtn.isDisabled();
        assertTrue(isDisabled);
    }

    public void invalidCreditCardInput(String cardNo, String expiryDate, String cvv, String cardHolderName) {
        if (cardNo != null) {
            inputCreditCardNo.fill(cardNo);
        }
        if (expiryDate != null) {
            inputExpiryDate.fill(expiryDate);
        }
        if (cvv != null) {
            inputCvv.fill(cvv);
        }
        if (cardHolderName != null) {
            inputCardHolderName.fill(cardHolderName);
        }
    }

    public void invalidGiftCardInput(String giftCardNo, String validationCode) {
        if (giftCardNo != null) {
            inputGiftCardNumber.fill("temp");
            inputGiftCardNumber.clear();
            inputGiftCardNumber.fill(giftCardNo);
        }
        if (validationCode != null) {
            inputValidationCode.fill("temp");
            inputValidationCode.clear();
            inputValidationCode.fill(validationCode);
        }
    }


}
