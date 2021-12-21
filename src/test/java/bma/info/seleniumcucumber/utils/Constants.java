package bma.info.seleniumcucumber.utils;

public class Constants {
    public static final long timeoutLong = 30;
    public static final long pollingLong = 200;
    public static final long timeoutShort = 10;
    public static final long pollingShort = 100;
    public static final String APK_PATH = System.getProperty("user.dir") + "/src/main/java/appUnderTest/";
    public static final String BROWSER_CONFIG = System.getProperty("user.dir") + "/src/test/resources/configs/";
    public static final String SAUCELAB_PROP = System.getProperty("user.dir") + "/src/test/resources/configs/saucelab.properties";
    public static final String BROWSERSTACK_PROP = System.getProperty("user.dir") + "/src/test/resources/configs/browserstack.properties";
    public static final String APPLICATION_CONFIG = System.getProperty("user.dir") + "/src/test/resources/configs/configuration.properties";
}
