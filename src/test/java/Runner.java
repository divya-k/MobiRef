
import com.simple.ams.util.CustomRunner;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CustomRunner.class)
@CucumberOptions(
        format = {
                "json:target/cucumber/cuke.json",
                "html:target/cucumber/cuke.html",
                "pretty"
        }
)
public class Runner {

}



