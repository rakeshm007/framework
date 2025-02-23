package ui.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = "ui/stepdefinitions",
        plugin = {

                "pretty",
                "json:target/cucumber-reports/cucumber.json",
//                "extent:target/cucumber-reports/extent-report.html"

        }
)
public class TestRunner {
}