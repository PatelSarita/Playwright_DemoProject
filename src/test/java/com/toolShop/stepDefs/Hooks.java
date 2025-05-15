package com.toolShop.stepDefs;

import com.microsoft.playwright.Page;
import com.toolShop.utilities.ConfigurationReader;
import com.toolShop.utilities.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static Page page;

    @Before
    public void setUp() {
        page = PlaywrightFactory.getPage();
        page.navigate(ConfigurationReader.get("url"));
        page.waitForTimeout(2000);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (page != null && scenario.isFailed()) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        PlaywrightFactory.close();
    }
}
