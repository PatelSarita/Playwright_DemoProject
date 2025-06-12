package com.toolShop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegisterPage extends BasePage {

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


    public RegisterPage(Page page) {
        super(page);
        this.firstNameInput = page.locator("#first_name");
        this.lastNameInput = page.locator("#last_name");
        this.dobInput = page.locator("input[placeholder='Your Date of birth *']");
        this.streetInput = page.locator("#street");
        this.postalCodeInput = page.locator("#postal_code");
        this.cityInput = page.locator("#city");
        this.stateInput = page.locator("[data-test='state']");
        this.countrySelect = page.locator("[data-test='country']");
        this.phoneInput = page.locator("#phone");
        this.emailInput = page.locator("#email");
        this.passwordInput = page.locator("#password");
        this.registerButton = page.locator("[data-test='register-submit']");
    }

    public void fillRegistrationForm(String firstName, String lastName, String dob, String street,
                                     String postalCode, String city, String state, String country, String phone,
                                     String email, String password) {

        firstNameInput.waitFor(); // waits for it to be attached & visible
        firstNameInput.fill(firstName);
        lastNameInput.waitFor();
        lastNameInput.fill(lastName);
        dobInput.waitFor();
        dobInput.fill(dob);
        streetInput.waitFor();
        streetInput.fill(street);
        postalCodeInput.waitFor();
        postalCodeInput.fill(postalCode);
        cityInput.waitFor();
        cityInput.fill(city);
        stateInput.waitFor();
        stateInput.fill(state);
        countrySelect.waitFor();
        countrySelect.selectOption(country);
        phoneInput.waitFor();
        phoneInput.fill(phone);
        emailInput.waitFor();
        emailInput.fill(email);
        passwordInput.waitFor();
        passwordInput.fill(password);
        registerButton.waitFor();


    }

    public void clickRegisterBtn() {
        registerButton.click();
        page.waitForTimeout(2000);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }


}
