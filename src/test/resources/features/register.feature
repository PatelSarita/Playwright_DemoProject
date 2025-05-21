Feature: User Registration
  As a user i should be able to register on the web site

  Background:
    Given the user is on the registration page

  @regression
  Scenario Outline: Successful registration with valid details

    When The user enters valid input "<First name>","<Last name>","<dob>","<Street>","<Postal code>","<City>","<State>", "<Country>","<Phone>","<Email address>" and "<Password>"
    And clicks on Register button
    Then The user should be navigated to Login page
    Examples:

      | First name | Last name | dob        | Street         | Postal code | City   | State  | Country | Phone     | Email address    | Password   |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 | prriya@gmail.com | WVhft76@99 |

  @register
  Scenario Outline: Registration fails when mandatory fields are missing or invalid
    When The user enters invalid input "<First name>","<Last name>","<dob>","<Street>","<Postal code>","<City>","<State>", "<Country>","<Phone>","<Email address>" and "<Password>"
    And clicks on Register button
    Then The registration should fail with appropriate validation "<ErrorMsg>"
    Examples:
      | First name | Last name | dob        | Street         | Postal code | City   | State  | Country | Phone     | Email address   | Password   | ErrorMsg                  |
      |            | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | First name is required    |
      | Priya      |           | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | fields.last-name.required |
      | Priya      | Survey    |            | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | Date of Birth is required |
      | Priya      | Survey    | 2001-10-22 |                | 60043       | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | Street is required        |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse |             | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | Postcode is required      |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       |        | Berlin | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | City is required          |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin |        | Germany | 196433333 | piiya@gmail.com | WVhft76@99 | State is required         |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin |         | 196433333 | piiya@gmail.com | WVhft76@99 | Country is required       |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany |           | piiya@gmail.com | WVhft76@99 | Phone is required.        |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 |                 | WVhft76@99 | Email is required         |
      | Priya      | Survey    | 2001-10-22 | Albert Strasse | 60043       | Berlin | Berlin | Germany | 196433333 | piiya@gmail.com |            | Password is required      |

