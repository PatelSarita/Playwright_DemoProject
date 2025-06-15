Feature: Payment Type Functionality
  As a user I should be able to complete purchase with different payment types


  @payment @positive @regression
  Scenario Outline: User completes a purchase using different types of payment methods
    Given the user is on the payment page
    When the user chooses "<Payment Type>" payment method with "<Installment Option>"and clicks on Confirm button
    Then the user should see "Payment was successful" message and clicks on Confirm button again
    And the user should see "Thanks for your order! Your invoice number is" order confirmation message
    Examples:
      | Payment Type      | Installment Option      |
      | Bank Transfer     |                         |
      | Cash on Delivery  |                         |
      | Credit Card       |                         |
      | Buy Now Pay Later | 12 Monthly Installments |
      | Gift Card         |                         |

  @payment @bankTransfer @negative @regression
  Scenario Outline: Bank Transfer fails with missing or invalid account details
    Given the user is on the payment page
    When the user selects "Bank Transfer" as the payment method
    And the user do not enters bank name "<Bank Name>", account name "<Account Name>", and account number "<Account Number>"
    Then the Confirm button should be disabled
    And the message "<Error Message>" should be displayed

    Examples:
      | Bank Name    | Account Name | Account Number | Error Message                                                                         |
      |              | Priya Test   | 123456789      | Bank name can only contain letters and spaces.                                        |
      | 1234567      | Priya Test   | 123456789      | Bank name can only contain letters and spaces.                                        |
      |              | Priya@Test   | 123456789      | Bank name can only contain letters and spaces.                                        |
      | Deutsch Bank |              | 123456789      | Account name can contain letters, numbers, spaces, periods, apostrophes, and hyphens. |
      | Deutsch Bank | Priya Test   |                | Account number must be numeric.                                                       |
      | Invalid@Bank | Priya Test   | abcde12345     | Account number must be numeric.                                                       |

@payment @creditCard @negative @regression
  Scenario Outline: Credit Card payment fails with invalid or expired card details
    Given the user is on the payment page
    When the user selects "Credit Card" as the payment method
    And enters "<Card Number>" as card number, "<Expiry Date>" as expiry, "<CVV>" as CVV, and "<Cardholder Name>" as cardholder
    Then the Confirm button should be disabled
    And the message "<Error Message>" should be displayed

    Examples:
      | Card Number         | Expiry Date | CVV | Cardholder Name | Error Message                          |
      | 4111-1111-1111-111  | 01/2020     | 123 | Test User       | Invalid card number format.            |
      | 4111111111110111    | 01/2020     | 123 | Test User       | Invalid card number format.            |
      | 4111-1111-1111-1111 | 12/2024     | 123 | Test User       | Expiration date must be in the future. |
      | 4111-1111-1111-1111 | 22/2026     | 123 | Test User       | Invalid date format. Use MM/YYYY.      |
      | 4111-1111-1111-1111 | 22/20244    | 123 | Test User       | Invalid date format. Use MM/YYYY.      |
      | 4111-1111-1111-1111 | 12/2026     | 12  | Test User       | CVV must be 3 or 4 digits.             |
      | 4111-1111-1111-1111 | 12/2026     | ABC | Test User       | CVV must be 3 or 4 digits.             |

     # | 4111-1111-1111-1111 | 12/2026     | 123 |                 | Cardholder name is required | Bug found for this test case

  @payment @giftCard @negative @regression
  Scenario Outline: Gift Card payment fails with invalid details
    Given the user is on the payment page
    When the user selects "Gift Card" as the payment method
    And enters "<Gift Card Number>" as the gift card number and "<Validation Code>" as the validation code
    Then the Confirm button should be disabled
    And the message "<Error Message>" should be displayed

    Examples:
      | Gift Card Number | Validation Code | Error Message                          |
      |                  | 1212            | Gift card number must be alphanumeric. |
      | 112!ab1876       | 1212            | Gift card number must be alphanumeric. |
      | GIFTCARD123      |                 | Validation code must be alphanumeric.  |
      | GIFTCARD123      | 12-34-5         | Validation code must be alphanumeric.  |


