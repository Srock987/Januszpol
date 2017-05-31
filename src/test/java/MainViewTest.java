import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class MainViewTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
    }

    @Test
    public void should_contain_title_label() {
        verifyThat("#titleLabel", hasText("Rejestrator czasu pracy".toUpperCase()));
    }

    @Test
    public void should_contain_buttons() {
        verifyThat("#addCompanyButton", hasText("Dodaj firmę"));
        verifyThat("#addWorkRecordButton", hasText("Dodaj wpis"));
        verifyThat("#viewAllButton", hasText("Zobacz wpisy"));
        verifyThat("#generateReportButton", hasText("Generuj raport"));
    }
}
