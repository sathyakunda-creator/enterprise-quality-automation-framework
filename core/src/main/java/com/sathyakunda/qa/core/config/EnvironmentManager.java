package com.sathyakunda.qa.core.config;

public final class EnvironmentManager {

    private static final String ENVIRONMENT_PROPERTY = "environment";

    private EnvironmentManager() {
    }

    public static String getEnvironment() {
        String environment = System.getProperty(ENVIRONMENT_PROPERTY);

        if (environment == null || environment.isBlank()) {
            environment = System.getenv(ENVIRONMENT_PROPERTY);
        }

        if (environment == null || environment.isBlank()) {
            environment = "qa";
        }

        return environment.trim().toLowerCase();
    }
}
