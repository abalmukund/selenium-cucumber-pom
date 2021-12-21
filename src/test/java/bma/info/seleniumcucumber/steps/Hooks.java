package bma.info.seleniumcucumber.steps;

import bma.info.seleniumcucumber.utils.ScreenShotMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Hooks {
    public static Scenario scenarioInfo;
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioInfo = scenario;
        clearScreenshots();
    }

    private void clearScreenshots() {
        try {
            FileUtils.cleanDirectory(new File("screenShots/"));
        } catch (IOException e) {
            log.error("Exception in cleaning screenShots directory: ", e);
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        endOfTest(scenario);
    }

    private void endOfTest(Scenario scenario) {
        if (scenario.getStatus() != null && scenario.isFailed()) {
            new ScreenShotMethods().takeScreenShot();
        }

        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
