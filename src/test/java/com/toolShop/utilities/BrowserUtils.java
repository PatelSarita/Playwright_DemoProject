package com.toolShop.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.toolShop.stepDefs.Hooks.page;
import static org.junit.Assert.assertEquals;

public class BrowserUtils {
    public static void assertErrorMessage(String errorMsg) {
        Locator errorLocator = page.locator("//*[contains(text(), '" + errorMsg + "')]");
        errorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage = errorLocator.textContent().trim();
        assertEquals(errorMsg, actualMessage);
    }

    public static void assertProductAddedToCart(String message) {
        Locator successMessage = page.locator("//*[contains(text(), '" + message + "')]");
        successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage = successMessage.textContent().trim();
        assertEquals(message, actualMessage);
    }

    public static void assertProductsDetailsInCart(String expectedProduct, String expectedQuantity, String expectedPrice, String expectedTotalPrice) {
        String actualProductName = page.locator("[data-test='product-title']").innerText().trim().replace("\u00A0", "").strip();
        String actualQuantity = page.locator("[data-test='product-quantity']").inputValue().trim();
        String actualPrice = page.locator("[data-test='product-price']").innerText().trim().replace("\u00A0", "").strip();
        String actualTotal = page.locator("[data-test='line-price']").innerText().trim().replace("\u00A0", "").strip();
        assertEquals(expectedProduct, actualProductName);
        assertEquals(expectedQuantity, actualQuantity);
        assertEquals(expectedPrice, actualPrice);
        assertEquals(expectedTotalPrice, actualTotal);

    }
}
