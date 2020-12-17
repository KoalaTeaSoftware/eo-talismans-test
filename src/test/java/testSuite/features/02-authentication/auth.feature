Feature: Authentication
  During development, I want all access to this app to be available to only authenticated users
  There will be 2 user classes: Guest (anonymous will be guests, when the app is released), and Premium
  During development, everyone has to be logged in
  This set of tests verifies the operation of my code, and the user experience. The hard stuff is handled by Big Brother

  Background: Get the app
    Given I navigate to the page ""
    And the please log in CTA is visible
    And the index stone is hidden
    And the single stone is hidden
    And the login form is hidden

  Scenario: I log in as a guest
    When  I log in with username "a@b.com" and password "qwertyuiop"
    Then The index stone becomes visible
    And the login form is hidden
    And the please log in CTA is hidden
    And the single stone is hidden

  Scenario: Authenticate as a premium user
    When I log in with username "premium@b.com" and password "premium1234"
    Then The index stone becomes visible
    And the login form is hidden
    And the please log in CTA is hidden
    And the single stone is hidden

  Scenario Outline: : Fail to authenticate with syntactically valid credentials
    # for the time being, assume that tidy handling of thing like missing ampersands, or empty fields will be handled
    # by the browser, and the server-side. This it to prove that the user experience is good
    When I log in with username "<username>" and password "<password>"
    Then the login form is visible
    # given that there is a round-trip to the server, be patient
    And the login failure message eventually mentions "credentials"
    And the index stone is hidden
    And the single stone is hidden
    Examples:
      | username | password   |
      | b@b.com  | qwertyuiop |
      | a@b.com  | wqertyuiop |
