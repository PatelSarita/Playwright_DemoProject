package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MyAccountPage extends BasePage{

    private final Locator myAccountTitle;

   public MyAccountPage(Page page){
        super(page);
        this.myAccountTitle = page.locator("[data-test='page-title']");
    }

    public boolean isMyAccountPageVisible(){
       return myAccountTitle.isVisible();
    }
}
