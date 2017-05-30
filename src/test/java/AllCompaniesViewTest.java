import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AllCompaniesViewTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);

        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
    }

    @Test
    public void should_have_label_listView_button() {
        should_contain_title_label();
        should_contain_button();
        should_have_list_view();
    }

    private void should_contain_title_label() {
        verifyThat("#titleLabel", hasText("Lista firm"));
    }

    private void should_contain_button() {
        verifyThat("#backButton", hasText("Powrót"));
    }

    private void should_have_list_view() {
        verifyThat("#companiesList", Node::isVisible);
    }
}
