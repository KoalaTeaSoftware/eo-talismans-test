@wip
Feature: Stone Ownership
  Stone ownership means that if a person who has purchased ownership of a set of stones, is logged in, then they get to see the premium stones.
  # Rule: if the user can't provide credentials for a know premium user, they don't get to see the premium stones
  # Rule: Users that already own a stone set will not be allowed to purchase that set twice
  # Rule: Users must provide acceptable credentials as they enter the purchase process
  # Rule: If the payment provider does not indicate a successful purchase, then they don't see the premium stones

  Background: Visit the home page
    Given I navigate to the page ""
    # Rule: During development, everyone has to be logged in
    # therefore, an anonymous user will be asked to log in
    And the please log in CTA is visible
    And the index stone is hidden
    And the single stone is hidden
    And the login form is hidden

  # Rule: A premium owner can shortcut the purchase process and get to seeing a premium stone
  Scenario Outline: Shortcut the purchase process (1)
    # Rule: During development, everyone has to be logged in
    And I log in with username "a@b.con" and password "quertyuiop"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    Then the call to purchase becomes hidden
    And the login form is visible
    Examples:
      | stone name |
      | a_haunting |

  # Rule: A premium owner can shortcut the purchase process and get to seeing a premium stone
  Scenario Outline: Shortcut the purchase process (2)
    # Rule: During development, everyone has to be logged in
    And I log in with username "a@b.con" and password "quertyuiop"
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    And the call to purchase becomes hidden
    And the login form is visible
    When I log in with username "premium@b.com" and password "qwertyuiop"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    And the logout button is visible
    Examples:
      | stone name |
      | a_haunting |

  # Rule: Guests, can become 'owners' of premium stones
  Scenario Outline: As a guest, I purchase a premium stone
    # Rule: During development, everyone has to be logged in
    And I log in with username "a@b.com" and password "qwertyuiop"
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    When I purchase premium membership
      | username |            |
      | password | quertyuiop |
    Then the call to purchase becomes hidden
    And the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    And the logout button is visible
    Examples:
      | stone name |
      | a_haunting |

  Scenario Outline: As a guest, when purchase a premium stone I become a premium user
    # As it is designed at the moment, there are just two tiers, and once a user has
    # become a premium user they are just that
    #
    # To become a useful test, through will have to be put into making sure that the
    # test user is a non-premium user, because once that user has made the purchase he becomes a premium user
    And I log in with username "a@b.com" and password "qwertyuiop"
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I purchase premium membership
    And the single stone becomes visible
    When I go back to the index stone
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    And the logout button is visible
    Examples:
      | stone name |
      | a_haunting |



