@functional @wip
Feature: Friendly URLs
  ToDo: As of 2020-12-14 it offers only index.html, anything else is served in a dumb way

  The site allows (and only really responds to) the use of friendly URLs.
  'Good' urls are tested on each of the pages.
  This set sees that bad urls don't cause a problem

  Scenario: Get the right title
    When I navigate to the page ""
    Then the page is fully drawn
    And the page title is ""

  Scenario Outline: Friendly treatment of rubbish urls
    Given I navigate to the page "<url>"
    Then the page title is ""
    Examples:
      | url                                                 |
      | http://stage.swordsandclapboards.com/engine-trouble |
      | http://stage.swordsandclapboards.com/pigs/are/great |