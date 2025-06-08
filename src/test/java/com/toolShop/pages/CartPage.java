package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {

    private final Locator proceedToCheckoutBtn1;
    private final Locator proceedToCheckoutBtn2;
    private final Locator proceedToCheckoutBtn3;

    private final Locator loggedInMsg;
    private final Locator streetInput;
    private final Locator cityInput;
    private final Locator stateInput;
    private final Locator countryInput;
    private final Locator postalCodeInput;
    private final Locator paymentDropdown;
    private final Locator confirmBtn;

    public CartPage(Page page) {
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
        this.paymentDropdown = page.locator("#payment-method");
        this.confirmBtn = page.locator("[data-test='finish']");
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
    public void assertCartProductDataTable(DataTable table){
        List<Map<String, String>> expectedProducts = table.asMaps(String.class, String.class);

        for (Map<String, String> expected : expectedProducts) {
            String productName = expected.get("Product Name");
            String quantity = expected.get("Product Quantity");
            String price = expected.get("Product Price");
            String total = expected.get("Total Price");

            // Locate the row by product name
            Locator productRow = page.locator("tr:has(span[data-test='product-title']:has-text('" + productName + "'))");
            productRow.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
           // String actualProduct = productRow.textContent().trim();
            // Assert Product Name
            PlaywrightAssertions.assertThat(productRow.locator("span[data-test='product-title']")).hasText(productName);

            // Assert Quantity (read from the input field's value attribute)
            String quantityValue = productRow.locator("input[data-test='product-quantity']").inputValue();
            assertEquals(quantity, quantityValue);

            // Assert Price
            PlaywrightAssertions.assertThat(productRow.locator("span[data-test='product-price']")).hasText(price);

            // Assert Total
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


    public void selectAndFillPaymentDetails(String paymentType) {
        paymentDropdown.selectOption(paymentType);
        page.waitForTimeout(1000);
        switch (paymentType) {
            case "Bank Name":
                page.fill("input[placeholder='Bank Name']", "Deutsch Bank");
                page.fill("input[placeholder='Account Name']", "Priya Test");
                page.fill("input[placeholder='Account Number']", "123456789101");
                break;
            case "Cash on Delivery":
                System.out.println("COD selected, no extra input needed.");
                break;

            case "Credit Card":
                page.fill("input[placeholder='Credit Card Number']", "4111-1111-2222-3333");
                page.fill("input[placeholder='Expiration Date']", "12/2026");
                page.fill("input[placeholder='CVV']", "123");
                page.fill("input[placeholder='Card Holder Name']", "Priya Test");
                break;
            case "Buy Now Pay Later":
                Locator installmentDropdown = page.locator("#monthly_installments");
                if (installmentDropdown.isVisible()) {
                    installmentDropdown.selectOption("6 Monthly Installments");
                    System.out.println("BNPL with 6-month installment selected.");
                } else {
                    throw new RuntimeException("Installment dropdown not found for BNPL.");
                }
                break;

            case "Gift Card":
                page.fill("input[placeholder='Gift Card Number']", "GIFTCARD123");
                page.fill("input[placeholder='Validation Code']", "1212");
                break;


            default:
                throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
    }

    public void clickConfirmBtn() {
        confirmBtn.click();
    }
}
