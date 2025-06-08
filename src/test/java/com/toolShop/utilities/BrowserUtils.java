package com.toolShop.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.toolShop.stepDefs.Hooks.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

public class BrowserUtils {
//    public static void assertErrorMessage(String errorMsg) {
//        Locator errorLocator = page.locator("//*[contains(text(), '" + errorMsg + "')]");
//        errorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        String actualMessage = errorLocator.textContent().trim();
//        assertEquals(errorMsg, actualMessage);
//    }
//
//    public static void assertProductAddedToCart(String message) {
//        Locator successMessage = page.locator("//*[contains(text(), '" + message + "')]");
//        successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        String actualMessage = successMessage.textContent().trim();
//        assertEquals(message, actualMessage);
//    }
//public static void assertPaymentSuccessfulMessage(String expectedMessage){
//    Locator successMessage = page.locator("//*[contains(text(), '" + expectedMessage + "')]");
//    successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//    String actualMessage = successMessage.textContent().trim();
//    assertEquals(expectedMessage, actualMessage);
//}
    public static void assertMessage(String expectedMessage) {
        Locator errorLocator = page.locator("//*[contains(text(), '" + expectedMessage + "')]");
        errorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage = errorLocator.textContent().trim();
        assertThat(actualMessage, containsString(expectedMessage));
      //  assertEquals(expectedMessage, actualMessage);
    }


}
