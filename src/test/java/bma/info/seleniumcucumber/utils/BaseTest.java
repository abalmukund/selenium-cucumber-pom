package bma.info.seleniumcucumber.utils;

public interface BaseTest {
    MiscMethods miscMethodObj = new MiscMethods();
    NavigateMethods navigationObj = new NavigateMethods();
    AssertionMethods assertionObj = new AssertionMethods();
    ClickElementsMethods clickObj = new ClickElementsMethods();
    ConfigurationMethods configObj = new ConfigurationMethods();
    InputMethods inputObj = new InputMethods();
    JavascriptHandlingMethods javascriptObj = new JavascriptHandlingMethods();
    ScreenShotMethods screenshotObj = new ScreenShotMethods();
}
