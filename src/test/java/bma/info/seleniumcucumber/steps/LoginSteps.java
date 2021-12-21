package bma.info.seleniumcucumber.steps;

import bma.info.seleniumcucumber.pages.AbstractPage;
import bma.info.seleniumcucumber.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps extends AbstractPage {
    LoginPage loginPage;

    @Given("^I should get logged-in$")
    public void shouldLoggedIn() throws NoSuchFieldException {
        loginPage = new LoginPage();
        Assert.assertEquals("You logged into a secure area!", loginPage.getMessage().getText().split("\n")[0].trim());
    }

    @Given("I navigate to the web page")
    public void iNavigateToTheWebPage() {
        loginPage = new LoginPage();
        loginPage.navigateToTheWebPage();
    }

    @And("I enter {string} and {string}")
    public void iEnterUserNameAndPassword(String userName, String password) throws NoSuchFieldException {
        loginPage.enterUserNamePassword(userName, password);
    }

    @When("I click on login button")
    public void iClickOnLoginButton() throws NoSuchFieldException {
        loginPage.clickLoginButton();
    }
}
