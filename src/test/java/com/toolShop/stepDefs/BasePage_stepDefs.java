package com.toolShop.stepDefs;

import com.microsoft.playwright.Page;
import com.toolShop.pages.*;

public abstract class BasePage_stepDefs {
    Page page = Hooks.page;
    LoginPage loginPage = new LoginPage(page);
    RegisterPage registerPage = new RegisterPage(page);
    MyAccountPage myAccountPage = new MyAccountPage(page);
    HomePage homePage = new HomePage(page);
    ProductPage productPage = new ProductPage(page);
    CartPage cartPage = new CartPage(page);
}
