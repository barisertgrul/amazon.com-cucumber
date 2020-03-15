Feature: login to Amazon.com

  Scenario: login with valid credentials
    Given Start Driver and Browser
    When Navigate to website
    And I land on homepage
    And I click on signin button
    And I enter email address
    And I click on Continue button
    And I enter password
    And I click on SignIn Button
    Then I should be logged in
