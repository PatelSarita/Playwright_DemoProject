Feature: Login Functionality
  As a user  I should be able to login with valid credentials

  Background:
    Given the user is on the Login page

  @login @positive @regression
  Scenario Outline: Positive login test
    When The user enters valid "<email>" and "<password>"
    And clicks on Login button
    Then The user should be able to login and redirected to My account page
    Examples:
      | email           | password   |
      | priya@gmail.com | WVhft76@99 |

  @login @positive @regression
  Scenario Outline: Positive login test with password as bullet signs
    When The user enters valid "<email>" and "<password>"
    Then The user should only see  masked password
    Examples:
      | email           | password   |
      | proya@gmail.com | WVhft76@99 |

  @login @negative @regression
  Scenario Outline: Negative login test
    When The user enters invalid "<email>" or "<password>"
    And clicks on Login button
    Then The user should get the appropriate "<errorMsg>"
    Examples:
      | email            | password   | errorMsg                  |
      | proya@gmail.com  |            | Password is required      |
      |                  | WVhft76@99 | Email is required         |
      | proya@gmail.co   | WVhft76@99 | Invalid email or password |
      | proya@gmail.com  | WVhft76@9  | Invalid email or password |



