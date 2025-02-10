package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDefinitions",
    //tags = "@Post or @Get or @GetById or @GetByFirstName or @DeleteByFirstName",
    
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)

public class TestRunner {
	
}
