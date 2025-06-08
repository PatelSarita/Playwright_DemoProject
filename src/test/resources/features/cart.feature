Feature: Cart Page Functionality
  As a user I want to manage my shopping cart
  So that I can review, modify or remove items before purchase

  @cart @smoke
  Scenario Outline: Verify that the user can add a product to the cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<Product Category>"
    Then the user should be navigated to the relevant "<Product Page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    Then The Product added to your shopping cart message "<productAddedMessage>" should be displayed
    Examples:
      | Product Category | Product Page | product      | quantity | productAddedMessage             |
      | Hand Tools       | Hand Tools   | Bolt Cutters | 2        | Product added to shopping cart. |

  @cart @validation
  Scenario Outline: Verify correct product and quantity in cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<Product Category>"
    Then the user should be navigated to the relevant "<Product Page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    And the user clicks on cart icon
    Then the cart should display the "<Product Name>" and "<Product Quantity>" and "<Product Price>" and correct "<Total Price>"
    Examples:
      | Product Category | Product Page | product      | quantity | Product Name | Product Quantity | Product Price | Total Price |
      | Hand Tools       | Hand Tools   | Bolt Cutters | 2        | Bolt Cutters | 2                | $48.41        | $96.82      |



  Scenario Outline: Verify correct product and quantity in cart
    Given the cart contains "<product>" with quantity "<quantity>"
    Then the cart should display "<product>" with quantity "<quantity>" and correct total

    Examples:
      | product            | quantity |
      | Combination Pliers | 2        |
      | Bolt Cutters       | 1        |


  Scenario Outline: Increase quantity of a product in the cart
    Given the cart contains "<product>" with quantity "<initialQuantity>"
    When the user increases the quantity of "<product>" to "<newQuantity>"
    Then the quantity should be updated to "<newQuantity>" and total recalculated

    Examples:
      | product            | initialQuantity | newQuantity |
      | Combination Pliers | 1               | 3           |
      | Bolt Cutters       | 2               | 4           |

  Scenario Outline: Remove an item from the cart
    Given the cart contains "<product>"
    When the user removes "<product>" from the cart
    Then "<product>" should no longer be displayed in the cart

    Examples:
      | product            |
      | Combination Pliers |
      | Bolt Cutters       |
