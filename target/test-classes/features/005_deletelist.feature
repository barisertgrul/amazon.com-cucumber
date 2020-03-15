Feature: remove a wishlist feature on amazon.com

  Scenario: remove a wishlist
    Given I land on  wish list page
    When I click on manage list button
    Then I should see manage list window is opened
    When I click on delete list button
    And I click on yes button

