package com.toolShop.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",                                      // cleaner console output
                "html:target/cucumber-reports.html",          // HTML report
                "json:target/cucumber.json",                  // JSON report (optional for integration)
                "rerun:target/failed_scenarios.txt"           // rerun file for failed scenarios
        },
        features = "src/test/resources/features",         // path to feature files
        glue = "com/toolShop/stepDefs",                   // path to step definitions
        dryRun = false,                                   // true = check steps only
        monochrome = true,                                // better console formatting
        tags = "@cart and @validation"                              // filter scenarios by tag (optional)
)
public class CukesRunner {
}
