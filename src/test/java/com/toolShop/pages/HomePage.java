package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.toolShop.stepDefs.Hooks.page;

public class HomePage extends BasePage {

    private final Locator categoryDropdown;
    private final Locator cartIcon;

    public HomePage(Page page) {
        super(page);
        this.categoryDropdown = page.locator("[data-test='nav-categories']");
        this.cartIcon = page.locator("[data-test='nav-cart']");
    }

    public void clickCategoryDropdown() {
        categoryDropdown.click();
        page.waitForTimeout(2000);
    }


    public void selectProductCategory(String categoryName) {
        String formattedName = categoryName.toLowerCase().replace(" ", "-");
        String selector = String.format("a.dropdown-item[data-test='nav-%s']", formattedName);
        page.locator(selector).click();
        page.waitForTimeout(2000);
    }

    public void clickCartIcon() {
        cartIcon.waitFor();
        cartIcon.click();
        page.waitForTimeout(2000);
    }

}
