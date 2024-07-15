@local
Feature: Shopping cart

  Scenario: Verify adding multiple products to the shopping cart
    Given User is on Homepage
    When User adds 5 products to the shopping cart
    Then The added products are present in the shopping cart

  Scenario: Verify removing a product from shopping cart successfully
    Given User is on Homepage
    When User adds 5 products to the shopping cart
    And User removes a product from shopping cart
    Then The removed product is no longer present in the Shopping Cart

  Scenario: Verify subtotal and total calculation in shopping cart
    Given User is on Homepage
    When User adds 5 products to the shopping cart
    Then The subtotal displayed in the shopping cart matches the sum of individual product prices

  Scenario Outline: Verify applying a coupon code successfully
    Given User is on Homepage
    When User adds 5 products to the shopping cart
    And User enters a valid coupon code <discount_type>
    Then The subtotal displayed in the shopping cart matches the sum of individual product prices
    Then The total price displayed in the shopping cart reflects the discounted amount after applying the coupon <discount_type>
    Examples:
      |               discount_type               |
      |      fixed_discount_to_entire_order       |
      |    percentage_discount_to_entire_order    |