Feature: LoginThroughPageClass
  As a user I should able to login into my app

  Scenario Outline: I login with valid credential
    Given I navigate to the web page
    And I enter "<userName>" and "<password>"
    When I click on login button
    Then I should get logged-in

    Examples:
      | userName | password             |
      | tomsmith | SuperSecretPassword! |