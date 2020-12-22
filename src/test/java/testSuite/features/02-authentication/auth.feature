@functional
Feature: Authentication
  There are three classes of user: Guest, Owner, Subscriber.
  An authenticated user will see different UI elements from those seen by an anonymous user.
  This set of tests verifies the operation of my code, and the user experience.
  The hard stuff is handled by the authentication service supplier (Google Firebase), and the browser itself

  Background: Visit the home page
    Given I navigate to the page ""
    # Rule: During development, everyone has to be logged in
    # therefore, an anonymous user will be asked to log in
    And the please log in CTA is visible
    And the index stone is hidden
    And the single stone is hidden
    And the login form is hidden
    And the login button is visible
    And the logout button is hidden

  # Rule: During development, everyone has to be logged in
  # therefore, an authenticated user will see the interesting stuff
  Scenario Outline: I log in
    When  I log in with username "<username>" and password "<password>"
    Then the index stone becomes visible
    And the login form becomes hidden
    And the please log in CTA is hidden
    And the single stone is hidden
    And the login button is hidden
    And the logout button is visible
    # Assumption: The authentication service knows all about this user
    Examples:
      | username      | password   |
      | a@b.com       | qwertyuiop |
      | premium@b.com | qwertyuiop |

  # Rule: Authentication is only possible with known credentials
  # Assume tidy handling of thing like missing ampersands, or empty fields will be handled
  # by the browser, and the server-side processing.
  # This scenario is to prove that the user experience is good
  Scenario Outline: Fail to authenticate with syntactically valid credentials
    When I log in with username "<username>" and password "<password>"
    Then the login form is visible
    # given that there is a round-trip to the server, be patient
    And the login failure message eventually mentions "credentials"
    And the index stone is hidden
    And the single stone is hidden
    And the login button is visible
    And the logout button is hidden
    # Assumption: The authentication service knows all about these users do not exist or do not have these passwords
    Examples:
      | username | password   |
      | b@b.com  | qwertyuiop |
      | a@b.com  | wqertyuiop |
