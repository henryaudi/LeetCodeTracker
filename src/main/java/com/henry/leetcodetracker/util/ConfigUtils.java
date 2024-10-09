package com.henry.leetcodetracker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static Properties properties = new Properties();

    /**
     * Load from config.properties file.
     */
    static {
        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            // Load properties file from the classpath
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getReadmeFilePath() { return properties.getProperty("readme.file.path"); }
}
