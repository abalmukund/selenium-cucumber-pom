package bma.info.seleniumcucumber.utils;

import bma.info.seleniumcucumber.pages.AbstractPage;
import bma.info.seleniumcucumber.steps.Hooks;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScreenShotMethods extends AbstractPage {
    /**
     * Method to take screen shot and save in ./screenShots folder
     */
    public void takeScreenShot() {
        final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        Hooks.scenarioInfo.attach(screenshot, "image/png", "Screenshot");
    }

    public File getScreenShot() {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy-z-HH-mm-ss", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        File destFile = new File("screenShots/" + dateFormat.format(cal.getTime()) + ".png");
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }
}
