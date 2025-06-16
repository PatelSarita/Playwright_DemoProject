Feature: Cart Page Functionality
  As a user I want to manage my shopping cart
  So that I can review, modify or remove items before purchase

  @cart @smoke @regression
  Scenario Outline: Verify that the user can add a product to the cart
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<product category>"
    Then the user should be navigated to the relevant "<product page>" page
    And select the "<product>" and "<quantity>" and clicks on Add to cart
    Then The Product added to your shopping cart message "<productAddedMessage>" should be displayed
    Examples:
      | product category | product page | product      | quantity | productAddedMessage             |
      | Hand Tools       | Hand Tools   | Bolt Cutters | 2        | Product added to shopping cart. |

  @cart @validation @regression
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



  @cart @increaseQuantity @regression
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

  @cart @removeQuantity @regression
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

    @cart @deleteAll @regression
  Scenario: User cannot proceed to checkout after removing all "<Product>"
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
    When the user removes all the product from the cart
    Then the "Product deleted." message should be displayed after deleting each item
    And the Proceed to checkout button should be not visible


