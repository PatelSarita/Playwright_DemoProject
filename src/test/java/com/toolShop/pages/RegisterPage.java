package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.toolShop.stepDefs.Hooks.page;
import static org.junit.Assert.assertEquals;

public class RegisterPage extends BasePage{

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator dobInput;
    private final Locator streetInput;
    private final Locator postalCodeInput;
    private final Locator cityInput;
    private final Locator stateInput;
    private final Locator countrySelect;
    private final Locator phoneInput;
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator registerButton;


    public RegisterPage(Page page){
        super(page);
        this.firstNameInput   = page.locator("#first_name");
        this.lastNameInput    = page.locator("#last_name");
        this.dobInput         = page.locator("input[placeholder='Your Date of birth *']");
        this.streetInput      = page.locator("#street");
        this.postalCodeInput  = page.locator("#postal_code");
        this.cityInput        = page.locator("#city");
        this.stateInput       = page.locator("[data-test='state']");
        this.countrySelect    = page.locator("[data-test='country']");
        this.phoneInput       = page.locator("#phone");
        this.emailInput       = page.locator("#email");
        this.passwordInput    = page.locator("#password");
        this.registerButton   = page.locator("[data-test='register-submit']");
    }

    public void fillRegistrationForm(String firstName, String lastName, String dob, String street,
                                     String postalCode, String city, String state,String country, String phone,
                                     String email, String password) {

     //   firstNameInput.click();
        firstNameInput.fill(firstName);
      //  lastNameInput.click();
        lastNameInput.fill(lastName);
        page.waitForTimeout(1000);

        dobInput.fill(dob);
        page.waitForTimeout(2000);
        streetInput.fill(street);
        page.waitForTimeout(2000);
        postalCodeInput.fill(postalCode);
        page.waitForTimeout(2000);
        cityInput.fill(city);
        page.waitForTimeout(2000);
        stateInput.fill(state);
        page.waitForTimeout(1000);
        countrySelect.selectOption(country);
        page.waitForTimeout(1000);// Assumes <select> element
        phoneInput.fill(phone);
        page.waitForTimeout(1000);
        emailInput.fill(email);
        page.waitForTimeout(1000);
        passwordInput.fill(password);
        page.waitForTimeout(2000);


    }
    public void clickRegisterBtn(){
        registerButton.click();
        page.waitForTimeout(2000);
    }
//    public Locator errorMsgLocator(String text) {
//        return page.locator("xpath=//*[text()='" + text + "']");
//      //  return page.locator("//*[text()=' + text + ']");
//    }





}
