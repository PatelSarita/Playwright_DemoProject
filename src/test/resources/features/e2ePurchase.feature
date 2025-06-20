Feature: End-to-End Product purchase Flow
  As a user I should be able to complete the purchase with selected products


  @e2e @purchase @regression
  Scenario Outline: User completes a purchase from product selection to order confirmation
    Given the user is logged in with email "priya@gmail.com" and password "WVhft76@99"
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<Product Category>" category
    Then the user should be navigated to the related "<Product Page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    And the user clicks on cart icon
    Then the cart should display the "<Product Name>" and "<Product Quantity>" and "<Product Price>" and correct "<Total Price>"
    And user clicks on first Proceed to checkout button
    Then the user sees the message "you are already logged in. You can proceed to checkout."
    And user clicks on second Proceed to checkout button
    Then the Billing Address should be displayed
    And user clicks on third Proceed to checkout button
    And the user chooses "<Payment Type>" payment method with "<Installment Option>"and clicks on Confirm button
    Then the user should see "Payment was successful" message and clicks on Confirm button again
    And the user should see "Thanks for your order! Your invoice number is" order confirmation message
    Examples:
      | Product Category | Product Page | product           | quantity | Product Name      | Product Quantity | Product Price | Total Price | Payment Type      | Installment Option     |
      | Other            | Other        | Protective Gloves | 4        | Protective Gloves | 4                | $21.42        | $85.68      | Credit Card       |                        |
     # | Hand Tools       | Hand Tools   | Bolt Cutters      | 2        | Bolt Cutters      | 2                | $48.41        | $96.82      | Buy Now Pay Later | 6 Monthly Installments |


  @guest @regression
  Scenario Outline: Guest user selects multiple products from different categories and completes a purchase
    When the user is on Home page and clicks on the Category dropdown

  # -- Product 1 --
    And selects the "Hand Tools" category
    Then the user should be navigated to the related "Hand Tools" page
    And select the "Adjustable Wrench" and "2" and clicks on Add to cart

  # -- Product 2 --
    And the user clicks on Category dropdown
    And selects the "Power Tools" category
    Then the user should be navigated to the related "Power Tools" page
    And select the "Cordless Drill 12V" and "1" and clicks on Add to cart

  # -- Product 3 --
    And the user clicks on Category dropdown
    And selects the "Other" category
    Then the user should be navigated to the related "Other" page
    And select the "Washers" and "4" and clicks on Add to cart

  # -- Cart and Checkout --
    And the user clicks on cart icon
    Then the cart should display:
      | Product Name       | Product Quantity | Product Price | Total Price |
      | Adjustable Wrench  | 2                | $20.33        | $40.66      |
      | Cordless Drill 12V | 1                | $46.50        | $46.50      |
      | Washers            | 4                | $3.55         | $14.20      |
    And the cart also displays the "$101.36" cart total

    And user clicks on first Proceed to checkout button
    Then the log in page should be displayed and the user enters valid "priya@gmail.com" and "WVhft76@99" and clicks on login button
    Then the user sees the message "you are already logged in. You can proceed to checkout."

    And user clicks on second Proceed to checkout button
    Then the Billing Address should be displayed

    And user clicks on third Proceed to checkout button
    And the user chooses "<Payment Type>" payment method with "<Installment Option>"and clicks on Confirm button

    Then the user should see "Payment was successful" message and clicks on Confirm button again
    And the user should see "Thanks for your order! Your invoice number is" order confirmation message
    Examples:
      | Payment Type | Installment Option |
      | Gift Card    |                    |

