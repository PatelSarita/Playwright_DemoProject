package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage{
    private final Locator registerAccountBtn;
    private final Locator loginText;

    public LoginPage(Page page){
        super(page);
        this.registerAccountBtn = page.locator("//a[text()='Register your account']");
        this.loginText = page.locator("//h3[text()='Login']");
    }
    public void clickRegisterYourAccountBtn(){
        registerAccountBtn.click();
        page.waitForTimeout(2000);
    }
    public boolean isLoginPageVisible(){
        return loginText.isVisible();
    }

}
