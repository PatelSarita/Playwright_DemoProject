package com.toolShop.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties props;

    static {
        try (InputStream input = new FileInputStream("configuration.properties")) {
            props = new Properties();
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
