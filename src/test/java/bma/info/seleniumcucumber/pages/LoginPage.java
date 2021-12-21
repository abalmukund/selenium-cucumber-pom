package bma.info.seleniumcucumber.pages;

import bma.info.seleniumcucumber.annotations.PageObject;
import bma.info.seleniumcucumber.utils.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@PageObject
public class LoginPage extends AbstractPage {

    @FindBy(how = How.ID, using = "flash")
    private WebElement MESSAGE;

    @FindBy(id = "username")
    private WebElement txtUserName;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(className = "radius")
    private WebElement btnLogin;

    public WebElement getMessage() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(MESSAGE);
        return MESSAGE;
    }

    public void navigateToTheWebPage() {
        navigationObj.navigateTo(ConfigurationReader.getProperty("url"));
        screenshotObj.takeScreenShot();
    }

    public void enterUserNamePassword(String userName, String password) throws NoSuchFieldException {
        getDriverWait().waitForElementVisible(txtUserName);
        inputObj.enterText(txtUserName, userName);
        inputObj.enterText(txtPassword, password);
    }

    public void clickLoginButton() throws NoSuchFieldException {
        clickObj.click(btnLogin);
    }
}

