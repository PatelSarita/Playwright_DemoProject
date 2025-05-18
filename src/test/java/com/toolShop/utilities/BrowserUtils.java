package com.toolShop.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.toolShop.stepDefs.Hooks.page;
import static org.junit.Assert.assertEquals;

public class BrowserUtils {
    public static void assertErrorMessage(String errorMsg){
        Locator errorLocator = page.locator("//*[contains(text(), '" + errorMsg + "')]");
        errorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage= errorLocator.textContent().trim();
        assertEquals(errorMsg,actualMessage);
    }
}
