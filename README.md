### selenium-cucumber-pom

This repository contains a collection of sample `selenium-cucumber-pom` projects and libraries that demonstrate how to
use the tool and develop automation script using the Cucumber (v 3.0.0) BDD framework with Java as programming language.
It generates Allure, HTML and JSON reporters as well. It also generates `screen shots` for your tests if you enable it
and also generate `error shots` for your failed test cases as well.

### Installation (pre-requisites)

1. JDK 1.8+ (make sure Java class path is set)
2. Maven (make sure .m2 class path is set)
3. Eclipse/InteliJ
4. Eclipse/InteliJ Plugins for
    - Maven
    - Cucumber

### Framework set up

Fork / Clone repository or download zip and set it up in your local workspace.
`https://github.com/abalmukund/selenium-cucumber-pom.git`

### Run Some Sample Tests

Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory
type `mvn clean test` command to run features. With this command it will invoke the default Firefox browser and will
execute the tests.

- To run features on specific browser use, `mvn test "-Dbrowser=browser_name"`
  browser_name can be one of following. firefox, chrome, edge, opera etc.

- To run specific feature file, if you have multiple feature files use:
  `mvn test -Dcucumber.options="classpath:features/my_first.feature"`

### Reporters

Once you ran your tests you can generate the various types of reports. This framework `selenium-cucumber-pom` uses
several types of test reporters to communicate pass/failure.

##### Allure Report:

You can generate a report using one of the following command.

- `mvn allure:serve`

Report will be generated into temp folder. Web server with results will start appearing in your default browser. This is
very handy if you are running test in CI/CD environment and wants to access the execution report. Note: If your default
browser is IE, Allure reports does not appear due to the style sheet compatibility issue. To get rid of this problem you
can use any other browser as a default browser or copy the Allure report url from the IE browser and use it in any other
browser.

- `mvn allure:report`

Report will be generated tо directory: `target/site/allure-maven/index.html` and you can view it locally.

##### HTML Report:

To generate HTML report use  `mvn test -Dcucumber.options="–plugin html:target/result.html"`

##### JSON Report:

To generate a JSON report Use `mvn test -Dcucumber.options="–plugin json:target/result.json"`

##### Extent Spark Reports

The Framework uses [Spark Reports Framework](http://www.extentreports.com/docs/versions/4/java/spark-reporter.html) to
generate the HTML Test Reports

### Develop automation scripts using BDD approach - Cucumber-Java

There are already many predefined StepDefinitions which is packaged under `/steps/Commonsteps.java` will help you speed
up your automation development that support both your favorite workaday helpers methods.

Tests are written in the Cucumber framework using the Gherkin Syntax. More about Gherkin & Cucumber can be found
at https://cucumber.io/docs/reference A typical test will look similar to this:

```
Feature: Performing a Google Search

    As a user on the Google search page
    I want to search for Selenium-Webdriver
    Because I want to learn more about it

    Background:

        Given I am on the search page

    Scenario: Performing a search operation
        When I enter "Selenium Webdriver" into the search box
        And  I click the search button
        Then I should see a list of search results

    Scenario Outline: Performing a search operation with passing test data as data table
        When I enter <searchItem> into the search box
        And  I click the search button
        Then I should see a list of search results

        Examples:
        |searchItem|
        |"Selenium Webdriver"|
```

### The Page Object Design Pattern

Within your web app's UI there are areas that your tests interact with. A Page Object simply models these as objects
within the test code. This reduces the amount of duplicated code and means that if the UI changes, the fix need only be
applied in one place. In other words one of the challenges of writing test automation is keeping your [selectors] (
classes, id's, or xpath' etc.) up to date with the latest version of your code. The next challenge is to keep the code
you write nice and `DRY (Don't Repeat Yourself)`. The page object pattern helps us accomplish this in one solution.
Instead of including our selectors in our step definitions(in cucumber) we instead place them in a <pagename>.java file
where we can manage all these selectors and methods together. Your test file should only call the test methods.

You can also place reusable methods or logic inside these pages and call them from your step java files. The page object
serves as a layer of abstraction between tests and code. When A test fails, it fails on an individual step. That step
may call a selector that is no longer valid, but that selector may be used by many other steps. By having a single
source of truth of what the selector is supposed to be, fixing one selector on the page object could repair a number of
failing tests that were affected by the same selector.
