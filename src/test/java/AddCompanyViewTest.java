import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;

public class AddCompanyViewTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
    }

    @Test
    public void should_contain_company_form() {
        //TODO define company form elements
    }


}

