package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"steps"},
        publish = true,
        tags = "@Test"
)
public class InicializationTest {

}
