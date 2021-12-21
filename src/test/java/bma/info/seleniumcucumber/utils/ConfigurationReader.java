package bma.info.seleniumcucumber.utils;

import java.io.FileInputStream;
import java.util.Properties;

public final class ConfigurationReader {

    private static Properties configFile;

    static {
        try {
            String path = Constants.APPLICATION_CONFIG;
            FileInputStream input = new FileInputStream(path);
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConfigurationReader() {
    }

    public static String getProperty(final String keyName) {
        return configFile.getProperty(keyName);
    }
}
