@regression
Feature: Cart Page Functionality
  As a user I want to manage my shopping cart
  So that I can review, modify or remove items before purchase

  @cart @smoke
  Scenario Outline: Verify that the user can add a product to the cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<product category>"
    Then the user should be navigated to the relevant "<product page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    Then The Product added to your shopping cart message "<productAddedMessage>" should be displayed
    Examples:
      | product category | product page | product      | quantity | productAddedMessage             |
      | Hand Tools       | Hand Tools   | Bolt Cutters | 2        | Product added to shopping cart. |

  @cart @validation
  Scenario Outline: Verify correct product and quantity in cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<product category>"
    Then the user should be navigated to the relevant "<product page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    And the user clicks on cart icon
    Then the cart should display the "<product>" and "<quantity>" and "<product price>" and correct "<total price>"
    Examples:
      | product category | product page | product      | quantity | product price | total price |
      | Hand Tools       | Hand Tools   | Bolt Cutters | 2        | $48.41        | $96.82      |



  @cart @increaseQuantity
  Scenario Outline: Update quantity of a product in the cart

    When the user is on My account page and clicks on the Category dropdown
    And selects the "<product category>"
    Then the user should be navigated to the relevant "<product page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    And the user clicks on cart icon
    Then the cart should display the "<product>" and "<quantity>" and "<product price>" and correct "<total price>"
    When the user increases or decrease the quantity of "<product>" to "<new quantity>"
    Then the success message "<message>" should be displayed with "<product>" and "<new quantity>" and "<new price>" and correct "<new total>"
    Examples:
      | product category | product page | product        | quantity | product price | total price | new quantity | message                   | new price | new total |
      | Other            | Other        | Ear Protection | 1        | $18.58        | $18.58      | 3            | Product quantity updated. | $18.58    | $55.74    |
      | Hand Tools       | Hand Tools   | Bolt Cutters   | 4        | $48.41        | $193.64     | 2            | Product quantity updated. | $48.41    | $96.82    |

  @cart @removeQuantity
  Scenario Outline: Remove an item from the cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<product category>"
    Then the user should be navigated to the relevant "<product page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    And the user clicks on cart icon
    Then the cart should display the "<product>" and "<quantity>" and "<product price>" and correct "<total price>"
    When the user removes the product from the cart
    Then the message "<message>" should be displayed
    Examples:
      | product category | product page | product        | quantity | product price | total price | message          |
      | Other            | Other        | Ear Protection | 1        | $18.58        | $18.58      | Product deleted. |

