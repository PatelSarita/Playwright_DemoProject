package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;
    protected final Locator signIn;

    public BasePage(Page page){
        this.page = page;
        this.signIn = page.locator("//a[text()='Sign in']");
    }
    public void clickSignIn(){
        signIn.click();
    }

}
