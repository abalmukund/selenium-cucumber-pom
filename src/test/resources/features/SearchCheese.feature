Feature: SearchCheese
  As a user I should able to search

  Scenario: I search 'cheese'
    Given I navigate to "http://duckduckgo.com"
    Then I take screenshot
    And I enter "cheese" into input field having name "q"
    When I click on element having id "search_button_homepage"
