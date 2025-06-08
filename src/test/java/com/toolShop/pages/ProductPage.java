package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.Assert;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

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
        // Use Playwright to check if the style changed (e.g., transform: scale(...))
        String transform = (String) image.evaluate("img => window.getComputedStyle(img).transform");
        System.out.println("Transform style for: " + productName + " is: " + transform);
        // Assert that the style has changed (indicating transition/video activation)
        Assert.assertNotEquals("none", transform, "Hover transition did not trigger for: " + productName);
    }

    public void clickOnProduct(String productName) {
        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
        Locator image = page.locator(xpath);
        image.click();
    }
//    public void clickProductByNameAcrossPages(String productName) {
//        int totalPages = page.locator("//ul[contains(@class,'pagination')]//a").count();
//
//        for (int i = 1; i <= totalPages; i++) {
//            goToPage(i);
//            String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
//Locator image = page.locator(xpath);
//            if (product.isVisible()) {
//                product.click();
//                page.waitForLoadState();
//                return;
//            }
//        }
//        throw new RuntimeException("Product '" + productName + "' not found on any page.");
//    }


    public void clickProductByNameAcrossPages(String productName) {
        while (true) {
            Locator product = page.locator("//h5[normalize-space(text())='" + productName + "']");

            if (product.count() > 0) {
                product.first().scrollIntoViewIfNeeded();
                product.first().click();
                page.waitForLoadState();
                return;
            }

            Locator nextButton = page.locator("//a[@aria-label='Next']");
            Locator parentLi = nextButton.locator(".."); // li with class='disabled'
            String parentClass = parentLi.getAttribute("class");

            if (parentClass != null && parentClass.contains("disabled")) {
                break;
            }

            nextButton.click();
            page.waitForSelector("div[data-test='product-card']"); // wait for products to reload
        }

        throw new RuntimeException("Product '" + productName + "' not found on any page.");
    }

//    public void clickProductByNameAcrossPages(String productName) {
//        int pageIndex = 1;
//        while (true) {
//            System.out.println("Searching page: " + pageIndex++);
//
//            // Locate product card anchor by matching inner product name
//           // Locator productCard = page.locator("//a[contains(@class, 'card')][.//h5[@data-test='product-name' and normalize-space(text())='" + productName + "']]");
//              String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
//        Locator image = page.locator(xpath);
//
//
//            if (image.count() > 0 && image.first().isVisible()) {
//                image.first().scrollIntoViewIfNeeded();
//               image.first().click();
//                page.waitForLoadState();
//                System.out.println("Clicked on product: " + productName);
//                return;
//            }
//
//            // Locate the 'Next' button
//            Locator nextButton = page.locator("//a[@aria-label='Next']");
//            Locator parentLi = nextButton.locator("..");
//            String parentClass = parentLi.getAttribute("class");
//
//            // Check if the next button is disabled
//            if (parentClass != null && parentClass.contains("disabled")) {
//                break;
//            }
//
//            nextButton.click();
//            // Wait for new products to load on next page
//            page.waitForSelector("a.card");
//        }
//        System.out.println("Final page reached. Could not find product: " + productName);
//        List<String> allProductNames = page.locator("h5[data-test='product-name']")
//                .allInnerTexts();
//        System.out.println("Products on last page: " + allProductNames);
//
//        throw new RuntimeException("Product '" + productName + "' not found on any page.");
//    }


    public boolean isProductDetailPageVisible(String productName) {
        Locator productTitle = page.locator("//h1[text()='" + productName + "']");
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
        String xpath = String.format("//h5[normalize-space(text())='%s']/ancestor::a//img", productName);
        Locator product = page.locator(xpath);
        product.click();
        quantityInput.fill(String.valueOf(quantity));
        addToCartBtn.click();
        page.waitForTimeout(3000);
    }
}