package com.toolShop.utilities;
import com.microsoft.playwright.*;
import java.util.Locale;
import static com.toolShop.utilities.ConfigurationReader.get;


public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page getPage() {
        if (playwright == null) {
            String browserName = get("browser").toLowerCase(Locale.ROOT);
            boolean isHeadless = Boolean.parseBoolean(get("headless"));

            playwright = Playwright.create();

            switch (browserName) {
                case "firefox":
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                    break;
                case "webkit":
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                    break;
                case "chromium":
                default:
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                    break;
            }

            context = browser.newContext();
            page = context.newPage();
        }
        return page;
    }

    public static void close() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
            browser = null;
            context = null;
            page = null;
        }
    }
}
