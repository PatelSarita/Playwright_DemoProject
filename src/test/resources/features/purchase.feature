Feature: Purchase Function
  As a user I should be able to complete the purchase with selected products

  Background:
    Given the user is logged in with email "proya@gmail.com" and password "WVhft76@99"

  @hover @regression
  Scenario Outline: Product video plays on image hover over
    When the user is on My account page and clicks on the Category dropdown
    And selects the "<Product Category>"
    Then the user should be navigated to the relevant "<Product Page>" page
    When the user hovers over the image of a "<Product>"
    Then the corresponding "<Product Video>" video should automatically play
    Examples:
      | Product Category | Product Page | Product        | Product Video  |
      | Power Tools      | Power Tools  | Sheet Sander   | Sheet Sander   |
      | Other            | Other        | Ear Protection | Ear Protection |
      | Hand Tools       | Hand Tools   | Bolt Cutters   | Bolt Cutters   |

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

