Feature: add to favorite feature on amazon.com

  Scenario: creating list and adding items to list
    Given I land on item detail page
    When I click on add to list button
    Then I should see create list window is opened
    When I click on wish list button
    And I click on create list button
    Then I should see item added message on screen
    When I click on view list on list create popup