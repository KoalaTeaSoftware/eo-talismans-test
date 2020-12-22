@functional
Feature: Shortcutting the purchase process when I already own some stones
  So that I can retrieve what I own when I come to a fresh installation of the app,
  As a premium user,
  I want to be able shortcut the purchase process and get to the see the premium stones
  # Rule: Guests, can become 'owners' of premium stones, by buying ownership
  # Rule: During shortcut, if the user can't provide credentials for a know premium user, they don't get to see the premium stones
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
    And I log in with username "a@b.com" and password "qwertyuiop"
    And the index stone becomes visible

  # Rule: A premium owner can shortcut the purchase process and get to seeing a premium stone
  # Note: Illustration of the correct obedience to this rule is broken into parts. This may be less efficient, but it is
  # the sub-parts of the rule are made more understandable
  Scenario Outline: Operation of the shortcut button
    When I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    Then the call to purchase becomes hidden
    And the login form is visible
    Examples:
      | stone name |
      | a_haunting |

  # Rule: A premium owner can shortcut the purchase process and get to seeing a premium stone
  Scenario Outline: Operation of the shortcut's logging-in, as a premium user
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    And the call to purchase becomes hidden
    And the login form is visible
    When I enter username "premium@b.com" and password "qwertyuiop" and log in
    Then the index stone becomes visible
    Examples:
      | stone name |
      | a_haunting |

  # Rule: A premium owner can shortcut the purchase process and get to seeing a premium stone
  Scenario Outline: Having logged-in, during the shortcut, as a premium user, one can get to see the stone that you originally wanted to see
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    And the call to purchase becomes hidden
    And the login form is visible
    When I enter username "premium@b.com" and password "qwertyuiop" and log in
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    Examples:
      | stone name |
      | a_haunting |

  # Rule: During shortcut, if user provides credentials of a non-premium user, they don't get to see the premium stones
  Scenario Outline: Sign is as a non-premium user during the shortcut
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    And I click the already a premium user button
    And the call to purchase becomes hidden
    And the login form is visible
    When I enter username "a@b.com" and password "qwertyuiop" and log in
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    Then the call to purchase the "<stone name>" becomes visible
    And the index stone is visible
    And the single stone is hidden
    And the login form is hidden
    Examples:
      | stone name |
      | a_haunting |


