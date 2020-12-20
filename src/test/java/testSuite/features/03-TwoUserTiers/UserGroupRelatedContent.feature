@wip
Feature: User Group Related Content
  There are two tiers of user: guest, premium. The guest users see fewer stones that the premium users, and can
  become premium users.

  Background: Get the app
    # for the time being, all users have to log in, so all anonymous users (as they will be when scenarios start)
    # see the welcome page until they log in
    Given I navigate to the page ""
    And the please log in CTA is visible
    And the index stone is hidden
    And the single stone is hidden
    And the login form is hidden

  Scenario Outline: As either type of user, look at free stones
    And I log in with username "<username>" and password "<password>"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    Examples:
      | username      | password   | stone name |
      | a@b.com       | qwertyuiop | lightning  |
      | premium@b.com | qwertyuiop | lightning  |

  Scenario Outline: As a guest, look at premium stones
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

  Scenario Outline: As a premium member, look at premium stones
    And I log in with username "premium@b.com " and password "qwertyuiop"
    And the index stone becomes visible
    When I ask to view the stone "<stone name>"
    Then the single stone becomes visible
    And the single stone is the "<stone name>" stone
    Examples:
      | stone name |
      | a_haunting |

  Scenario Outline: As a guest, I purchase a stone (and so become a premium user)
    # As it is designed at the moment, there are just two tiers, and once a user has
    # become a premium user they are just that
    #
    # To become a useful test, through will have to be put into making sure that the
    # test user is a non-premium user, because once that user has made the purchase he becomes a premium user
    And I log in with username "a@b.com" and password "qwertyuiop"
    And the index stone becomes visible
    And I ask to view the stone "<stone name>"
    And the call to purchase the "<stone name>" becomes visible
    When I purchase premium membership
    Then the call to purchase becomes hidden
    And the single stone becomes visible
    And the single stone is the "<stone name>" stone
    And the index stone is hidden
    And the login form is hidden
    And the logout button is visible
    Examples:
      | stone name |
      | a_haunting |
