package bma.info.seleniumcucumber.steps;

import bma.info.seleniumcucumber.pages.AbstractPage;
import bma.info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.Then;

public class PredefinedStepDefinitions extends AbstractPage {
    // Navigation Steps

    // Step to navigate to specified URL
    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigateTo(String link) {
        navigationObj.navigateTo(link);
    }

    // Step to navigate forward
    @Then("^I navigate forward")
    public void navigateForward() {
        navigationObj.navigate("forward");
    }

    // Step to navigate backward
    @Then("^I navigate back")
    public void navigateBack() {
        navigationObj.navigate("back");
    }

    // steps to refresh page
    @Then("^I refresh page$")
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    // Switch between windows

    // Switch to new window
    @Then("^I switch to new window$")
    public void switchToNewWindow() {
        navigationObj.switchToNewWindow();
    }

    // Switch to old window
    @Then("^I switch to previous window$")
    public void switchToOldWindow() {
        navigationObj.switchToOldWindow();
    }

    // Switch to new window by window title
    @Then("^I switch to window having title \"(.*?)\"$")
    public void switchToWindowByTitle(String windowTitle) {
        navigationObj.switchToWindowByTitle(windowTitle);
    }

    // Close new window
    @Then("^I close new window$")
    public void closeNewWindow() {
        navigationObj.closeNewWindow();
    }

    // Switch between frame

    // Step to switch to frame by web element
    @Then("^I switch to frame having (.+) \"(.*?)\"$")
    public void switchFrameByElement(String method, String value) {
        navigationObj.switchFrame(method, value);
    }

    // step to switch to main content
    @Then("^I switch to main content$")
    public void switchToDefaultContent() {
        navigationObj.switchToDefaultContent();
    }

    // To interact with browser

    // step to resize browser
    @Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
    public void resizeBrowser(int width, int heigth) {
        navigationObj.resizeBrowser(width, heigth);
    }

    // step to maximize browser
    @Then("^I maximize browser window$")
    public void maximizeBrowser() {
        navigationObj.maximizeBrowser();
    }

    // zoom in/out page

    // steps to zoom in page
    @Then("^I zoom in page$")
    public void zoomIn() {
        navigationObj.zoomInOut("ADD");
    }

    // steps to zoom out page
    @Then("^I zoom out page$")
    public void zoomOut() {
        navigationObj.zoomInOut("SUBTRACT");
    }

    // zoom out webpage till necessary element displays

    // steps to zoom out till element displays
    @Then("^I zoom out page till I see element having (.+) \"(.*?)\"$")
    public void zoomTillElementDisplay(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        navigationObj.zoomInOutTillElementDisplay(type, "substract", accessName);
    }

    // reset webpage view use
    @Then("^I reset page view$")
    public void resetPageZoom() {
        navigationObj.zoomInOut("reset");
    }

    // scroll webpage
    @Then("^I scroll to (top|end) of page$")
    public void scrollPage(String to) throws Exception {
        navigationObj.scrollPage(to);
    }

    // scroll webpage to specific element
    @Then("^I scroll to element having (.+) \"(.*?)\"$")
    public void scrollToElement(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        navigationObj.scrollToElement(type, accessName);
    }

    // hover over element
    // Note: Doesn't work on Windows firefox
    @Then("^I hover over element having (.+) \"(.*?)\"$")
    public void hoverOverElement(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        navigationObj.hoverOverElement(type, accessName);
    }

    // Assertion steps

    /**
     * page title checking
     *
     * @param present :
     * @param title   :
     */
    @Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
    public void checkTitle(String present, String title) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkTitle(title, present.isEmpty());
    }

    // step to check element partial text
    @Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
    public void checkPartialText(String present, String partialTextTitle) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkPartialTitle(partialTextTitle, present.isEmpty());
    }

    // step to check element text
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
    public void checkElementText(String type, String accessName, String present, String value) throws Exception {
        miscMethodObj.validateLocator(type);
        assertionObj.checkElementText(type, value, accessName, present.isEmpty());
    }

    // step to check element partial text
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have partial text as \"(.*?)\"$")
    public void checkElementPartialText(String type, String accessName, String present, String value)
            throws Exception {
        miscMethodObj.validateLocator(type);
        assertionObj.checkElementPartialText(type, value, accessName, present.isEmpty());
    }

    // step to check attribute value
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have attribute \"(.*?)\" with value \"(.*?)\"$")
    public void checkElementAttribute(String type, String accessName, String present, String attrb, String value)
            throws Exception {
        miscMethodObj.validateLocator(type);
        assertionObj.checkElementAttribute(type, attrb, value, accessName, present.isEmpty());
    }

    // step to check element enabled or not
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
    public void checkElementEnable(String type, String accessName, String present, String state) throws Exception {
        miscMethodObj.validateLocator(type);
        boolean flag = state.equals("enabled");
        if (!present.isEmpty()) {
            flag = !flag;
        }
        assertionObj.checkElementEnable(type, accessName, flag);
    }

    // step to check element present or not
    @Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void checkElementPresence(String type, String accessName, String present) throws Exception {
        miscMethodObj.validateLocator(type);
        assertionObj.checkElementPresence(type, accessName, present.isEmpty());
    }

    // step to assert checkbox is checked or unchecked
    @Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
    public void isCheckboxChecked(String type, String accessName, String state) throws Exception {
        miscMethodObj.validateLocator(type);
        boolean flag = state.equals("checked");
        assertionObj.isCheckboxChecked(type, accessName, flag);
    }

    // steps to assert radio button checked or unchecked
    @Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void isRadioButtonSelected(String type, String accessName, String state) throws Exception {
        miscMethodObj.validateLocator(type);
        boolean flag = state.equals("selected");
        assertionObj.isRadioButtonSelected(type, accessName, flag);
    }

    // steps to assert option by text from radio button group
    // selected/unselected
    @Then("^option \"(.*?)\" by (.+) from radio button group having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void isOptionFromRadioButtonGroupSelected(String option, String attrb, String type, String accessName,
                                                     String state) throws Exception {
        miscMethodObj.validateLocator(type);
        boolean flag = state.equals("selected");
        assertionObj.isOptionFromRadioButtonGroupSelected(type, attrb, option, accessName, flag);
    }

    // steps to check link presence
    @Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void checkElementPresence(String accessName, String present) throws TestCaseFailed, Exception {
        assertionObj.checkElementPresence("linkText", accessName, present.isEmpty());
    }

    // steps to check partail link presence
    @Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void checkPartialElementPresence(String accessName, String present) throws TestCaseFailed, Exception {
        assertionObj.checkElementPresence("partialLinkText", accessName, present.isEmpty());
    }

    // step to assert javascript pop-up alert text
    @Then("^I should see alert text as \"(.*?)\"$")
    public void checkAlertText(String expectedValue) throws TestCaseFailed {
        assertionObj.checkAlertText(expectedValue);
    }

    // step to select dropdown list
    @Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void isOptionFromDropdownSelected(String option, String by, String type, String accessName, String state)
            throws Exception {
        miscMethodObj.validateLocator(type);
        boolean flag = state.equals("selected");
        assertionObj.isOptionFromDropdownSelected(type, by, option, accessName, flag);
    }

    // Input steps
    // enter text into input field steps
    @Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
    public void enterText(String text, String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.enterText(type, text, accessName);
    }

    // clear input field steps
    @Then("^I clear input field having (.+) \"([^\"]*)\"$")
    public void clearText(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.clearText(type, accessName);
    }

    // select option by text/value from dropdown
    @Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
    public void selectOptionFromDropdown(String option, String optionBy, String type, String accessName)
            throws Exception {
        miscMethodObj.validateLocator(type);
        miscMethodObj.validateOptionBy(optionBy);
        inputObj.selectOptionFromDropdown(type, optionBy, option, accessName);
    }

    // select option by index from dropdown
    @Then("^I select (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
    public void selectOptionFromDropdownByIndex(String option, String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.selectOptionFromDropdown(type, "selectByIndex", option, accessName);
    }

    // select option by text/value from multiselect
    @Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
    public void selectOptionFromMultiselectDropdown(String option, String optionBy, String type, String accessName)
            throws Exception {
        miscMethodObj.validateLocator(type);
        miscMethodObj.validateOptionBy(optionBy);
        inputObj.selectOptionFromDropdown(type, optionBy, option, accessName);
    }

    // select option by index from multiselect
    @Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
    public void selectOptionFromMultiselectDropdownByIndex(String option, String type, String accessName)
            throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.selectOptionFromDropdown(type, "selectByIndex", option, accessName);
    }

    // deselect option by text/value from multiselect
    @Then("^I deselect \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
    public void deselectOptionFromMultiselectDropdown(String option, String optionBy, String type,
                                                      String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        miscMethodObj.validateOptionBy(optionBy);
        inputObj.deselectOptionFromDropdown(type, optionBy, option, accessName);
    }

    // deselect option by index from multiselect
    @Then("^I deselect (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
    public void deselectOptionFromMultiselectDropdownByIndex(String option, String type, String accessName)
            throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.deselectOptionFromDropdown(type, "selectByIndex", option, accessName);
    }

    // step to select option from mutliselect dropdown list
    /*
     * @Then("^I select all options from multiselect dropdown having (.+) \"(.*?)\"$"
     * ) public void select_all_option_from_multiselect_dropdown(String
     * type,String accessName) throws Exception {
     * miscmethod.validateLocator(type); //inputObj.
     * //select_all_option_from_multiselect_dropdown(type, access_name) }
     */

    // step to unselect option from mutliselect dropdown list
    @Then("^I deselect all options from multiselect dropdown having (.+) \"(.*?)\"$")
    public void unselectAllOptionFromMultiselectDropdown(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.unselectAllOptionFromMultiselectDropdown(type, accessName);
    }

    // check checkbox steps
    @Then("^I check the checkbox having (.+) \"(.*?)\"$")
    public void checkCheckbox(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.checkCheckbox(type, accessName);
    }

    // uncheck checkbox steps
    @Then("^I uncheck the checkbox having (.+) \"(.*?)\"$")
    public void uncheckCheckbox(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.uncheckCheckbox(type, accessName);
    }

    // steps to toggle checkbox
    @Then("^I toggle checkbox having (.+) \"(.*?)\"$")
    public void toggleCheckbox(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.toggleCheckbox(type, accessName);
    }

    // step to select radio button
    @Then("^I select radio button having (.+) \"(.*?)\"$")
    public void selectRadioButton(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        inputObj.selectRadioButton(type, accessName);
    }

    // steps to select option by text from radio button group
    @Then("^I select \"(.*?)\" option by (.+) from radio button group having (.+) \"(.*?)\"$")
    public void selectOptionFromRadioBtnGroup(String option, String by, String type, String accessName)
            throws Exception {
        miscMethodObj.validateLocator(type);
        // miscMethodObj.validateOptionBy(optionBy);
        inputObj.selectOptionFromRadioButtonGroup(type, option, by, accessName);
    }

    // Click element Steps
    // click on web element
    @Then("^I click on element having (.+) \"(.*?)\"$")
    public void click(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        clickObj.click(type, accessName);
    }

    // Forcefully click on element
    @Then("^I forcefully click on element having (.+) \"(.*?)\"$")
    public void clickForcefully(String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        clickObj.clickForcefully(type, accessName);
    }

    // double click on web element
    @Then("^I double click on element having (.+) \"(.*?)\"$")
    public void doubleClick(String type, String accessValue) throws Exception {
        miscMethodObj.validateLocator(type);
        clickObj.doubleClick(type, accessValue);
    }

    // steps to click on link
    @Then("^I click on link having text \"(.*?)\"$")
    public void clickLink(String accessName) {
        clickObj.click("linkText", accessName);
    }

    // Step to click on partial link
    @Then("^I click on link having partial text \"(.*?)\"$")
    public void clickPartialLink(String accessName) {
        clickObj.click("partialLinkText", accessName);
    }

    // Progress methods

    // wait for specific period of time
    @Then("^I wait for (\\d+) sec$")
    public void wait(String time) throws NumberFormatException {
        getDriverWait().sleepTight(Integer.parseInt(time));
    }

    // wait for specific element to display for specific period of time
    @Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
    public void waitForEleToDisplay(String duration, String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        getDriverWait().waitForElementToDisplay(type, accessName, duration);
    }

    // wait for specific element to enable for specific period of time
    @Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
    public void waitForEleToClick(String duration, String type, String accessName) throws Exception {
        miscMethodObj.validateLocator(type);
        getDriverWait().waitForElementToClick(type, accessName, duration);
    }

    // JavaScript handling steps
    // Step to handle java script
    @Then("^I accept alert$")
    public void handleAlert() {
        javascriptObj.handleAlert("accept");
    }

    // Steps to dismiss java script
    @Then("^I dismiss alert$")
    public void dismissAlert() {
        javascriptObj.handleAlert("dismiss");
    }

    // Screen shot methods
    @Then("^I take screenshot$")
    public void takeScreenshot() {
        screenshotObj.takeScreenShot();
    }

    // Configuration steps
    // step to print configuration
    @Then("^I print configuration$")
    public void printConfig() {
        configObj.printDesktopConfiguration();
    }
}