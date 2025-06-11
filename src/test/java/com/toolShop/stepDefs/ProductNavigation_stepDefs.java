package com.toolShop.stepDefs;

import com.toolShop.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductNavigation_stepDefs extends BasePage_stepDefs {

    @When("the user is on Home page and clicks on the Category dropdown")
    public void theUserIsOnHomePageAndClicksOnTheCategoryDropdown() {
        homePage.clickCategoryDropdown();
    }

    @And("selects the {string}")
    public void selectsThe(String productCategory) {
        homePage.selectProductCategory(productCategory);
    }

    @Then("the user should be navigated to the relevant {string} page")
    public void theUserShouldBeNavigatedToTheRelevant(String expectedPage) {
        assert productPage.isProductPageVisible(expectedPage);
    }

    @Given("the user is on {string}")
    public void theUserIsOn(String category) {
        productPage.navigateToCategoryPage(category);
    }

    @When("the user hovers over the image of a {string}")
    public void theUserHoversOverTheImageOfA(String product) {
        productPage.hoverOverProductImage(product);
    }

    @Then("the corresponding {string} video should automatically play")
    public void theCorrespondingVideoShouldAutomaticallyPlay(String productVideo) {
        productPage.isVideoPlaying(productVideo);
    }

    @When("the user clicks on {string}")
    public void theUserClicksOn(String productName) {
        productPage.clickProductByNameAcrossPages(productName);

    }

    @Then("the user should see the product detail page for {string}")
    public void theUserShouldSeeTheProductDetailPageFor(String productName) {
        assert productPage.isProductDetailPageVisible(productName);
    }


    @And("there is no product available")
    public void thereIsNoProductAvailable() {
        assert productPage.areNoProductsVisible();
    }

    @Then("the user should get the {string} message")
    public void theUserShouldGetTheMessage(String expectedMessage) {
        String actualMessage = productPage.getNoProductMessage();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
}
