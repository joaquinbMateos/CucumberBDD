package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/DemoQa/demoQa.feature"},
        glue = {"stepDefinition/DemoQa"},
        plugin = {"pretty"}
        //,tags = "@BirthDate"
)
public class demoQaRunner {
}
