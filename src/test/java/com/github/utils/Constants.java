package com.github.utils;

import java.io.File;

public class Constants {
    public final static String PROJECT_HOME = System.getProperty("user.dir");
    public final static String MAIN_RESOURCES = PROJECT_HOME + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    public final static String TEST_RESOURCES = PROJECT_HOME + File.separator + "src" + File.separator + "test" + File.separator + "resources";
    public final static String ENVIRONMENT_DETAILS_PROPERTIES_FILE = TEST_RESOURCES + File.separator + "EnvironmentDetails" + File.separator + "Github.properties";
}
