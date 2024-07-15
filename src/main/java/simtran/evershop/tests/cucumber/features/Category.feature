@local @prod
Feature: Category page

  Scenario Outline: Verify opening categories from Homepage
    Given User is on Homepage
    When User clicks on category "<category>" button
    Then User is on category "<category>" page

    Examples:
      |     category    |
      |   Shop kids     |
      |   Shop women    |
      |   Shop men      |

  Scenario Outline: Verify product count displayed on category page matches product list size
    Given User is on Homepage
    When User clicks on category "<category>" button
    Then Product count displays correctly

    Examples:
      |     category    |
      |   Shop kids     |
      |   Shop women    |
      |   Shop men      |


  Scenario: Verify sorting products by name (ascending and descending)
    Given User is on Homepage
    When User clicks on category "Shop women" button
    And User sorts products by name in asc order
    Then Products are sorted by name in asc order
    When User sorts products by name in desc order
    Then Products are sorted by name in desc order


  Scenario: Verify sorting products by price (ascending and descending)
    Given User is on Homepage
    When User clicks on category "Shop women" button
    And User sorts products by price in asc order
    Then Products are sorted by price in asc order
    When User sorts products by price in desc order
    Then Products are sorted by price in desc order