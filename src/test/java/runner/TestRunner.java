package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {
                "pretty",

                // ✅ Cucumber HTML Report
                "html:target/cucumber-report.html",

                // ✅ Allure Report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,     // ✅ Cleaner console output
        publish = false        // ✅ Avoid publish warning
)
public class TestRunner {
}
