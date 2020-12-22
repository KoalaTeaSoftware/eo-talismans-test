Feature: User Group Related Content
  There are two tiers of user: guest, owner. The guest users see fewer stones that the premium users

  Background: Visit the home page
    Given I navigate to the page ""
    # Rule: During development, everyone has to be logged in
    # therefore, an anonymous user will be asked to log in
    And the please log in CTA is visible
    And the index stone is hidden
    And the single stone is hidden
    And the login form is hidden

  # Rule: Users of all classes can see the free stones
  Scenario Outline: As different types of user, look at free stones
    And I log in with username "<username>" and password "<password>"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    # Assumption: The authentication service knows all about these users
    Examples:
      | username      | password   | stone name |
      | a@b.com       | qwertyuiop | lightning  |
      | premium@b.com | qwertyuiop | lightning  |

  # Rule: Guest users are unable to directly see the premium stones
  Scenario Outline: As a guest, look at premium stones
    # Assumption: The authentication service knows all about this user
    And I log in with username "a@b.com" and password "qwertyuiop"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    Then the call to purchase the "<stone name>" becomes visible
    And the index stone is visible
    And the single stone is hidden
    And the login form is hidden
    Examples:
      | stone name |
      | a_haunting |

  # Rule: Premium users can directly see the premium stones
  Scenario Outline: As a premium member, look at premium stones
    # Assumption: The authentication service knows all about this user
    And I log in with username "premium@b.com " and password "qwertyuiop"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    Examples:
      | stone name |
      | a_haunting |