package com.toolShop.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "rerun:target/failed_scenarios.txt"
        },
        features = "src/test/resources/features",
        glue = "com/toolShop/stepDefs",
        dryRun = false,
        monochrome = true,
        tags = "@e2e and @purchase and @regression"
)
public class CukesRunner {
}
