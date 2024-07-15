@local @prod
Feature: Product details page

  Scenario Outline: Verify opening product details page from Homepage
    Given User is on Homepage
    When User clicks on product number <index> on Homepage
    Then Product details page number <index> is displayed
    Examples:
      |   index   |
      |      0    |
      |      1    |
      |      2    |
      |      3    |


  Scenario Outline: Verify opening product details page from Category page
    Given User is on Homepage
    When User clicks on category "Shop men" button
    When User clicks on product number <index> on the category page
    Then Product details page number <index> is displayed
    Examples:
      |   index   |
      |      0    |
      |      1    |
      |      2    |