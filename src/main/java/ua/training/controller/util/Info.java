package ua.training.controller.util;

import java.util.ResourceBundle;

public class Info {
    private static Info message;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "info";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    public static final String DONE = "DONE";
    public static final String ERROR = "ERROR";

    public static Info getInstance() {
        if (message == null) {
            message = new Info();
            message.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return message;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
