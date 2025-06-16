# 🧪 Playwright Demo Project

This project contains automated UI tests developed using **Java**, **Playwright**, **Cucumber**, **JUnit**, and **Page Object Model (POM)**. It verifies the functionality of an e-commerce tool shop across various user journeys.

<p align="left">
  <img src="https://img.shields.io/badge/Java-17-blue.svg" />
  <img src="https://img.shields.io/badge/Playwright-1.44.0-green.svg" />
  <img src="https://img.shields.io/badge/Cucumber-BDD-brightgreen.svg" />
  <img src="https://img.shields.io/badge/JUnit-4.13.2-lightgrey.svg" />
  <img src="https://img.shields.io/badge/POM-Design_Pattern-yellow.svg" />
  <img src="https://img.shields.io/badge/Report-Cucumber_HTML-informational.svg" />
</p>

---

## 📚 Test Scenarios

### ✅ Positive Tests

- **Login (Valid):** Login with valid credentials.
- **Password Masking:** Check password input is displayed as bullet signs.
- **Registration Success:** Register a new user with valid details.
- **Category Navigation:** Navigate to a product category from the dropdown.
- **Product Video Hover:** Play product video on hover over image.
- **View Product Details:** Open and view detailed product information.
- **Add to Cart:** Add a product to the cart successfully.
- **Cart Verification:** Verify product and quantity in the cart.
- **Update Quantity:** Change quantity of product in the cart.
- **Remove Item:** Remove a specific item from the cart.
- **Purchase Flow:** Complete purchase from product selection to order confirmation.
- **Guest Checkout:** Guest user purchases multiple products from different categories.
- **Purchase with Payment Methods:** Complete purchase using different types of payment methods.
- **Logout:** Successfully logout from the My Account page.

---

### ❌ Negative Tests

- **Login (Invalid):** Attempt login with invalid credentials.
- **Registration Missing Fields:** Fail registration due to missing or invalid fields.
- **Duplicate Email Registration:** Handle existing email registration gracefully.
- **Invalid Password:** Show error on invalid password during registration.
- **Out-of-Stock Handling:** Detect and report when a product is unavailable.
- **Empty Cart Checkout:** Prevent checkout after removing all products from the cart.
- **Bank Transfer Errors:** Bank Transfer fails with missing or invalid account details.
- **Credit Card Validation:** Credit Card payment fails with invalid or expired card details.
- **Gift Card Rejection:** Gift Card payment fails with invalid details.

---

## 🛠️ Tech Stack

| Tool / Framework | Version     | Description                                 |
|------------------|-------------|---------------------------------------------|
| Java             | 17          | Base programming language                   |
| Playwright       | 1.44.0      | Browser automation tool                     |
| Cucumber         | 7.15.0      | BDD framework for feature files             |
| JUnit            | 4.13.2      | Test runner                                 |
| POM              | -           | Design pattern for code structure           |
| Commons Config   | 1.10        | Manage configuration files                  |

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 17 or higher
- Maven installed
- Node.js (for Playwright CLI)

### 🔧 Install Playwright Browsers

```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

---

## ▶️ Running the Tests

### 🥪 Run All Tests via Maven

```bash
mvn test
```

### 🍿 Run Specific Tagged Tests

You can tag feature files or scenarios (e.g., `@register`, `@regression`) and run them like so:

```bash
mvn test -Dcucumber.filter.tags="@register"
```

To **run `@register` first**, and then others (like `@regression`), structure your Cucumber runner or test suites accordingly using Maven profiles or test ordering plugins.

---

## 📊 Reports

After execution, an HTML report is generated:

* **Location:** `/target/cucumber-reports/Cucumber.html`
* **Includes:** Passed/failed steps, scenarios, tags, execution time, and screenshots (if configured).

---
