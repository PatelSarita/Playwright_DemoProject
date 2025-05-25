package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final Locator registerAccountBtn;
    private final Locator loginText;
    private final Locator loginEmail;
    private final Locator loginPassword;
    private final Locator loginBtn;


    public LoginPage(Page page) {
        super(page);
        this.registerAccountBtn = page.locator("//a[text()='Register your account']");
        this.loginText = page.locator("//h3[text()='Login']");
        this.loginEmail = page.getByPlaceholder("Your email");
        this.loginPassword = page.locator("#password");
        this.loginBtn = page.locator("[data-test='login-submit']");
    }

    public void clickRegisterYourAccountBtn() {
        registerAccountBtn.click();
        page.waitForTimeout(2000);
    }

    public boolean isLoginPageVisible() {
        return loginText.isVisible();
    }

    public void login(String email, String password) {
        loginEmail.waitFor();
        loginEmail.fill(email);
        loginPassword.waitFor();
        loginPassword.fill(password);
        page.waitForTimeout(2000);
    }
    public void clickLoginBtn(){
        loginBtn.click();
        page.waitForTimeout(2000);
    }
    public boolean isPasswordMasked() {
        String typeAttribute = loginPassword.getAttribute("type");
        return "password".equals(typeAttribute);
    }

}
