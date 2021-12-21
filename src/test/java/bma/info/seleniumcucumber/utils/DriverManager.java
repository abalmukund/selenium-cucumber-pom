package bma.info.seleniumcucumber.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driver;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    /**
     * By default to web driver will be firefox
     * <p>
     * Override it by passing -Dbrowser=Chrome to the command line arguments
     *
     * @return webdriver
     */
    private static WebDriver chooseDriver() {
        String preferredDriver = System.getProperty("browser");
        System.out.println(preferredDriver);
        boolean headless = System.getProperty("headless", "false").equals("true");
        if (preferredDriver == null) {
            preferredDriver = "chrome";
        }
        switch (preferredDriver.toLowerCase()) {
            case "opera":
                WebDriverManager.operadriver().setup();
                return new SafariDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                final FirefoxOptions ffOptions = new FirefoxOptions();

                if (headless) {
                    ffOptions.setHeadless(true);
                }
                return new FirefoxDriver(ffOptions);
            default:
                final ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("-incognito");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                return new ChromeDriver(chromeOptions);
        }
    }

    public static DesiredCapabilities getCapability(InputStream input) {
        final Properties prop = new Properties();
        DesiredCapabilities capability = new DesiredCapabilities();
        try {
            prop.load(input);
            if (prop.containsKey("app")) {
                String appName = prop.getProperty("app");
                if (!appName.contains("sauce-storage")) {
                    String appPath = Constants.APK_PATH + appName;
                    prop.setProperty("app", appPath);
                }
            }

            // set capabilities
            Enumeration<Object> enuKeys = prop.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = prop.getProperty(key);
                capability.setCapability(key, value);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return capability;
    }

    /*
     * Returns saucelab remote driver instance by reading saucelab configuration
     * from platformConfigs/saucelab.properties
     *
     * @param DesiredCapabilities create capabilities by reading browser config.
     *
     * @return RemoteWebDriver
     */
    private static WebDriver saucelabDriver() {
        final DesiredCapabilities capability = null;
        final URL remoteDriverURL;
        RemoteWebDriver remoteDriver = null;
        final Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream(
                    Constants.BROWSER_CONFIG + System.getProperty("config", "") + ".properties");
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        // set app path for app testing
        if (prop.containsKey("app")) {
            String appName = prop.getProperty("app").split(":")[1];
            String appPath = Constants.APK_PATH + appName;

            File appFile = new File(appPath);
            if (appFile.exists()) {
                // prop.setProperty("app", appPath);
                SauceLabsFileManager.uploadAppToSauceStorage(appName, appPath, prop);
            } else {
                System.out.println("Exception : No app with name '" + appName + "' found in appUnderTest directory");
                System.exit(0);
            }
        }

        try {
            InputStream input = new FileInputStream(Constants.SAUCELAB_PROP);
            prop.load(input);
            String url = prop.getProperty("protocol") + "://" + prop.getProperty("username") + ":"
                    + prop.getProperty("access_key") + prop.getProperty("url");
            input.close();
            prop.clear();
            remoteDriverURL = new URL(url);
            remoteDriver = new RemoteWebDriver(remoteDriverURL, capability);
            System.out.println(remoteDriver.getCapabilities().getBrowserName());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return remoteDriver;
    }

    /*
     * Returns browserStack remote driver instance by reading browserStack
     * configuration from platformConfigs/browserstack.properties
     *
     * @param DesiredCapabilities create capabilities by reading browser config.
     *
     * @return RemoteWebDriver
     */
    private static WebDriver browserStackDriver() {
        DesiredCapabilities capability = null;
        final Properties prop = new Properties();
        URL remoteDriverURL = null;
        try {
            InputStream input = new FileInputStream(Constants.BROWSERSTACK_PROP);
            prop.load(input);
            String url = prop.getProperty("protocol") + "://" + prop.getProperty("username") + ":"
                    + prop.getProperty("access_key") + prop.getProperty("url");
            input.close();
            prop.clear();
            remoteDriverURL = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(remoteDriverURL, capability);
    }

    private static WebDriver androidDriver(DesiredCapabilities capabilities) {
        String port = "4723";
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static WebDriver iosDriver(DesiredCapabilities capabilities) {
        String port = "4723";
        try {
            driver = new IOSDriver<>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        } else {
            return getDefaultDriver();
        }
    }

    public WebDriver getDefaultDriver() {
        DesiredCapabilities capability = null;
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        }
        String enviroment = "desktop";
        String platform = "";
        String config = System.getProperty("config", "");
        if (!config.isEmpty()) {
            try {
                enviroment = config.split("_")[0].toLowerCase();
                platform = config.split("_")[1].toLowerCase();
                InputStream input = new FileInputStream(
                        Constants.BROWSER_CONFIG + config + ".properties");
                capability = getCapability(input);
            } catch (Exception e) {
                System.out.println(
                        "\nException : File not present or Invalid config file name " + config + ".properties");
                System.out.println("Config file format should be : enviroment_platform_device.properties");
                System.out.println("\nE.g : local_android_nexus5.properties");
                System.out.println("E.g : local_ios_iphone6.properties");
                System.out.println("E.g : browserstack_android_nexus5.properties");
                System.out.println("E.g : saucelab_windows7_chrome.properties");
                System.exit(0);
            }
        }

        switch (enviroment) {
            case "mobile":
                if (platform.equals("android"))
                    driver = androidDriver(capability);
                else if (platform.equals("ios"))
                    driver = iosDriver(capability);
                else {
                    System.out.println("unsupported platform");
                    System.exit(0);
                }
                break;
            case "browserstack":
                driver = browserStackDriver();
                break;
            case "saucelab":
                driver = saucelabDriver();
                break;
            case "desktop":
                driver = chooseDriver();
                break;
            default:
                System.out.println("\nException : Invalid platform " + enviroment);
                System.exit(0);
        }
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        return getDriver();
    }
}
