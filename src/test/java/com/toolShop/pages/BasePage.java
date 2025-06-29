package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;

    protected final Locator signIn;
    private final Locator userNameDropdown;
    private final Locator signOut;



    public BasePage(Page page){
        this.page = page;
        this.signIn = page.locator("//a[text()='Sign in']");
        this.userNameDropdown = page.locator("#menu");
        this.signOut = page.locator("a.dropdown-item[data-test='nav-sign-out']");
    }
    public void clickSignIn(){
        signIn.click();
    }

    public void clickUserNameDropdown(){
        userNameDropdown.click();
    }
    public void selectSignOutOption(){
        signOut.click();
        page.waitForTimeout(2000);
    }
}
