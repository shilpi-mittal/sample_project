Feature: verify basic search functionality of google
  just go to home page and enter search parameter, hit enter and check search results displayed.

  Background:
    Given I access home page

  @cucumber
  Scenario: verify search functionality
    Then I verify logo is displayed
    When I enter search parameter "hello"
    And I hit enter
    Then I verify I am taken to new page
    And I verify search results are displayed