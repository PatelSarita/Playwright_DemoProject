package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.Assert;

public class ProductPage extends BasePage {
    private final Locator quantityInput;
    private final Locator addToCartBtn;

    public ProductPage(Page page) {
        super(page);
        this.quantityInput = page.locator("#quantity-input");
        this.addToCartBtn = page.locator("#btn-add-to-cart");
    }

    public boolean isProductPageVisible(String productPage) {
        String productPageHeading = String.format("//h2[text()='Category: %s']", productPage);
        Locator heading = page.locator(productPageHeading);
        return heading.isVisible();
    }

    public void hoverOverProductImage(String productName) {
        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
        Locator image = page.locator(xpath);
        image.hover();
        page.waitForTimeout(4000);

    }

    public void isVideoPlaying(String productName) {
        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
        Locator image = page.locator(xpath);
        // Use Playwright to check if the style changed (e.g., transform: scale(...))
        String transform = (String) image.evaluate("img => window.getComputedStyle(img).transform");
        System.out.println("Transform style for: " + productName + " is: " + transform);
        // Assert that the style has changed (indicating transition/video activation)
        Assert.assertNotEquals("none", transform, "Hover transition did not trigger for: " + productName);
    }

    public void addToCart(String productName,String quantity){
        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
        Locator product = page.locator(xpath);
        product.click();
        quantityInput.fill(String.valueOf(quantity));
        addToCartBtn.click();
        page.waitForTimeout(3000);
    }
}