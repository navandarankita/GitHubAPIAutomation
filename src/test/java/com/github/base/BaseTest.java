package com.github.base;

import com.github.utils.EnvironmentDetails;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        EnvironmentDetails.loadProperties();
    }

}
