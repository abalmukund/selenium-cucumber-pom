package bma.info.seleniumcucumber.pages;

import bma.info.seleniumcucumber.utils.BaseTest;
import bma.info.seleniumcucumber.utils.DriverManager;
import bma.info.seleniumcucumber.utils.DriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage implements BaseTest {

    private final DriverManager driverManager = new DriverManager();
    private final DriverWait driverWait = new DriverWait(driverManager);

    protected AbstractPage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public DriverWait getDriverWait() {
        return driverWait;
    }
}
