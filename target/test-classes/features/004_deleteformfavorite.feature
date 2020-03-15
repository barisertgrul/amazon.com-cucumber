Feature: remove an item from wishlist feature on amazon.com

  Scenario: remove an item from wishlist
    Given I land on  wish list page
    When I click on delete item button on first item on the list
    Then I should see deleted text on screen
