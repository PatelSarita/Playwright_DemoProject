package com.toolShop.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed_scenarios.txt",        // pick only failed scenarios
        glue = "com/toolShop/stepDefs",
        plugin = {
                "pretty",
                "html:target/failed-cucumber-reports.html"
        },
        monochrome = true
)
public class FailedTestRunner {
}
