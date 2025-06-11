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
        page.waitForTimeout(1000);
        return heading.isVisible();
    }

    public void navigateToCategoryPage(String category) {
        String categorySlug = category.toLowerCase().replace(" ", "-");
        page.navigate("https://practicesoftwaretesting.com/category/" + categorySlug);
        page.waitForTimeout(2000);
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
        String transform = (String) image.evaluate("img => window.getComputedStyle(img).transform");
        System.out.println("Transform style for: " + productName + " is: " + transform);

        Assert.assertNotEquals("none", transform, "Hover transition did not trigger for: " + productName);
    }

    public void clickProductByNameAcrossPages(String productName) {
        while (true) {
            Locator product = page.locator("//h5[normalize-space(text())='" + productName + "']");

            if (product.count() > 0) {
                product.first().scrollIntoViewIfNeeded();
                product.first().click();
                return;
            }
            Locator nextButton = page.locator("a[aria-label='Next']");
            if (!nextButton.isVisible() || nextButton.getAttribute("class").contains("disabled")) {
                break;
            }
            nextButton.click();
            page.waitForTimeout(1000);
        }
        throw new RuntimeException("Product '" + productName + "' not found on any page.");
    }


    public boolean isProductDetailPageVisible(String productName) {

        Locator productTitle = page.locator("//h1[text()='" + productName + "']");
        productTitle.waitFor();
        String titleText = productTitle.innerText().trim();
        System.out.println("titleText = " + titleText);
        return productTitle.isVisible();
    }
    public boolean areNoProductsVisible() {
        Locator productItems = page.locator("[data-test='product-name']");
        int count = productItems.count();
        System.out.println("Product items found: " + count);
        return count == 0;
    }

    public String getNoProductMessage(){
        Locator message = page.locator("//div[text()=' There are no products found. ']");
        return message.innerText().trim();
    }


    public void addToCart(String productName, String quantity) {
//        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
//        Locator product = page.locator(xpath);
//        product.click();
        clickProductByNameAcrossPages(productName);
        quantityInput.fill(String.valueOf(quantity));
        addToCartBtn.click();
        page.waitForTimeout(3000);
    }
}