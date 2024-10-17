package com.hexaware.insurancemgmt.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    private static Properties properties = new Properties();

    static {
        try (FileReader reader = new FileReader("resources/DBConfig.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyString(String key) {
        return properties.getProperty(key);
    }
}