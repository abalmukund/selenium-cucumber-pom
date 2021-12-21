package bma.info.seleniumcucumber.utils;

import bma.info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InputMethods extends AbstractPage implements BaseTest {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement dropdown = null;
    private Select selectList = null;

    /**
     * Method to enter text into text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param text       : String : Text value to enter in field
     * @param accessName : String : Locator value
     */
    public void enterText(String accessType, String text, String accessName) {
        getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        getDriver().findElement(selectElementByType.getelementbytype(accessType, accessName)).sendKeys(text);
    }

    /**
     * Method to enter text into text field
     *
     * @param element : WebElement : Object element
     * @param text    : String : Text value to enter in field
     */
    public void enterText(WebElement element, String text) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Method to clear text of text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clearText(String accessType, String accessName) {
        getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        getDriver().findElement(selectElementByType.getelementbytype(accessType, accessName)).clear();
    }

    /**
     * Method to select element from Dropdown by type
     *
     * @param select_list : Select : Select variable
     * @param bytype      : String : Name of by type
     * @param option      : String : Option to select
     */
    public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
        switch (bytype) {
            case "selectByIndex":
                int index = Integer.parseInt(option);
                select_list.selectByIndex(index - 1);
                break;
            case "value":
                select_list.selectByValue(option);
                break;
            case "text":
                select_list.selectByVisibleText(option);
                break;
        }
    }

    /**
     * Method to select option from dropdown list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
        selectelementfromdropdownbytype(selectList, optionBy, option);
    }

    /**
     * Method to select all option from dropdwon list
     *
     * @param access_type : String : Locator type (id, name, class, xpath, css)
     * @param access_name : String : Locator value
     * @throws TestCaseFailed : Fails the test case
     */
    public void selectAllOptionFromMultiselectDropdown(String access_type, String access_name) throws TestCaseFailed {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(access_type, access_name)));
        selectList = new Select(dropdown);
        if (selectList.isMultiple()) {
            for (WebElement options : selectList.getOptions()) {
                selectList.selectByVisibleText(options.getText());
            }
        } else {
            throw new TestCaseFailed("Select list is not compatible with multiple select");
        }
    }

    /**
     * Method to unselect all option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /**
     * Method to unselect option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);

        switch (optionBy) {
            case "selectByIndex":
                selectList.deselectByIndex(Integer.parseInt(option) - 1);
                break;
            case "value":
                selectList.deselectByValue(option);
                break;
            case "text":
                selectList.deselectByVisibleText(option);
                break;
        }
    }

    /**
     * Method to check check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void checkCheckbox(String accessType, String accessName) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (!checkbox.isSelected())
            checkbox.click();
    }

    /**
     * Method to uncheck check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void uncheckCheckbox(String accessType, String accessName) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (checkbox.isSelected())
            checkbox.click();
    }

    /**
     * Method to toggle check-box status
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void toggleCheckbox(String accessType, String accessName) {
        getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName))).click();
    }

    /**
     * Method to select radio button
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void selectRadioButton(String accessType, String accessName) {
        WebElement radioButton = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (!radioButton.isSelected())
            radioButton.click();
    }

    /**
     * Method to select option from radio button group
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param by         : String : Name of by type
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
        List<WebElement> radioButtonGroup = getDriver().findElements(selectElementByType.getelementbytype(accessType, accessName));
        for (WebElement rb : radioButtonGroup) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option) && !rb.isSelected())
                    rb.click();
            } else if (by.equals("text")) {
                if (rb.getText().equals(option) && !rb.isSelected())
                    rb.click();
            }
        }
    }
}
