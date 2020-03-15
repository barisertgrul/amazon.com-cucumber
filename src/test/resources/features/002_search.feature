Feature: search function on amazon.com
  Scenario: Search for specific product on Amazon.com
    Given I am on homepage
    When I enter text on search box
    And I click on search button
    Then I should see search results
    When I click on page number on pagination
    Then I should see second page of search results
    When I click on third item on second page of results
    Then I should see item detail page is opened
