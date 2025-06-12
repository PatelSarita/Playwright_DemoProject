Feature: Logout functionality
  As a logged-in user i can log out successfully
@logOut
  Scenario: Successful logout from My Account page
    Given the user is logged in with email "priya@gmail.com" and password "WVhft76@99"
    When the user is on My Account page and click on users name dropdown
    And select logout option
    Then the user should logged out successfully and navigated to login page