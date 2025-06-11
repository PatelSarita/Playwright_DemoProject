Feature: Product Navigation
  As a user i want to navigate through product categories


  @navigation @smoke
  Scenario Outline: Navigate to product category from dropdown
    When the user is on Home page and clicks on the Category dropdown
    And selects the "<Category>"
    Then the user should be navigated to the relevant "<Category Page>" page
    Examples:
      | Category    | Category Page |
      | Power Tools | Power Tools   |
      | Hand Tools  | Hand Tools    |
      | Other       | Other         |

  @hover
  Scenario Outline: Product video plays on image hover over
    Given the user is on "<Category Page>"
    When the user hovers over the image of a "<Product>"
    Then the corresponding "<Product>" video should automatically play
    Examples:
      | Category Page | Product        |
      | Power Tools   | Sheet Sander   |
      | Hand Tools    | Bolt Cutters   |
      | Other         | Ear Protection |

  @productDetails @regression
  Scenario Outline: View product details
    Given the user is on "<Category Page>"
    When the user clicks on "<Product>"
    Then the user should see the product detail page for "<Product>"

    Examples:
      | Category Page | Product            |
      | Power Tools   | Cordless Drill 12V |
      | Hand Tools    | Square Ruler       |
      | Other         | Tool Cabinet       |


  @negative @emptyProduct
  Scenario Outline: unavailability of product
    When the user is on "<Category Page>"
    And there is no product available
    Then the user should get the "There are no products found." message
    Examples:
      | Category Page |
      | Special Tools |



