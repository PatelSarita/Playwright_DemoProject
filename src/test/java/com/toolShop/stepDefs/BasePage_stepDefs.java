package com.toolShop.stepDefs;

import com.microsoft.playwright.Page;
import com.toolShop.pages.LoginPage;
import com.toolShop.pages.RegisterPage;

public abstract class BasePage_stepDefs {
    Page page = Hooks.page;
    LoginPage loginPage = new LoginPage(page);
    RegisterPage registerPage = new RegisterPage(page);
}
