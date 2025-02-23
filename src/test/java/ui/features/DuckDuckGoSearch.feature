Feature: DuckDuckGo Search Functionality
  As a user, I want to be able to search for information on DuckDuckGo
  so that I get relevant results.

  Scenario: Search for "Selenium WebDriver" on DuckDuckGo
    Given I am on the DuckDuckGo homepage
    When I search for "Selenium WebDriver"
    Then the search results page title should contain "Selenium WebDriver"