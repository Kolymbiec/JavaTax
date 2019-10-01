package ua.training.controller.util;

import java.util.ResourceBundle;

public class Config {

    private static Config config;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";
    public static final String HOME = "HOME";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String INFO = "INFO";


    public static Config getInstance() {
        if (config == null) {
            config = new Config();
            config.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return config;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
