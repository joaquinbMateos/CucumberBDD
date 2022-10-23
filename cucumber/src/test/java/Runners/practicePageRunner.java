package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/PracticePage/practicePage.feature"},
        glue = {"stepDefinition/PracticePage"},
        plugin = {"pretty"}
        //,tags = "@"
)

public class practicePageRunner {

}