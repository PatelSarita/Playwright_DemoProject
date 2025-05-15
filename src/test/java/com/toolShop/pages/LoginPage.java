package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage{
    protected final Locator registerAccountBtn;

    public LoginPage(Page page){
        super(page);
        this.registerAccountBtn = page.locator("//a[text()='Register your account']");
    }
    public void clickRegisterYourAccountBtn(){
        registerAccountBtn.click();
        page.waitForTimeout(2000);
    }

}
