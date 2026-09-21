package com.sathyakunda.qa.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final String CONFIG_FILE = "config.properties";

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private ConfigManager() {
    }

    private static void loadProperties() {
        try (InputStream inputStream =
                     ConfigManager.class.getClassLoader()
                             .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Configuration file not found: " + CONFIG_FILE);
            }

            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load configuration: " + CONFIG_FILE, e);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);

        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }

        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }

        if (value == null) {
            throw new IllegalArgumentException(
                    "Configuration key not found: " + key);
        }

        return value.trim();
    }

    public static String getEnvironment() {
        return EnvironmentManager.getEnvironment();
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
