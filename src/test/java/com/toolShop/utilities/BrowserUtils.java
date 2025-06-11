package com.toolShop.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.toolShop.stepDefs.Hooks.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

public class BrowserUtils {
    public static void assertMessage(String expectedMessage) {
        Locator errorLocator = page.locator("//*[contains(text(), '" + expectedMessage + "')]");
        errorLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String actualMessage = errorLocator.textContent().trim();
        assertThat(actualMessage, containsString(expectedMessage));
    }


}
