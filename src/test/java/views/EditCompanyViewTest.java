package views;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Created by HP on 2017-06-08.
 */
public class EditCompanyViewTest extends ApplicationTest {

    private static final String TITLE_LABEL = "#companyTitleLabel";

    private static final String COMPANY_NAME_LABEL = "#companyNameLabel";

    private static final String COMPANY_ADDRESS_LABEL = "#companyAddressLabel";

    private static final String COMPANY_NAME_TEXTFIELD = "#companyNameTextField";

    private static final String COMPANY_ADDRESS_TEXTFIELD = "#companyAddressTextField";

    private static final String SAVE_COMPANY_BUTTON = "#companyAddButton";

    private static final String CANCEL_COMPANY_BUTTON = "#companyCancelButton";

    @Override
    public void start(Stage stage)
            throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);

        ScreenManager
                .getInstance()
                .setScreen(ScreenManager.EDIT_COMPANY_VIEW_ID);
    }

    @Test
    public void verifyLabelsText() {
        verifyThat(TITLE_LABEL, hasText("Edytuj firmę"));
        verifyThat(COMPANY_NAME_LABEL, hasText("Nazwa firmy"));
        verifyThat(COMPANY_ADDRESS_LABEL, hasText("Adres firmy"));
        verifyThat(SAVE_COMPANY_BUTTON, hasText("Zapisz"));
        verifyThat(CANCEL_COMPANY_BUTTON, hasText("Anuluj"));
    }

    @Test
    public void verifyTextFields() {
        clickOn(COMPANY_NAME_TEXTFIELD).write("FirmaA");
        clickOn(COMPANY_ADDRESS_TEXTFIELD).write("AdresA");

        verifyThat(COMPANY_NAME_TEXTFIELD, (TextField tf) -> tf
                .getText()
                .contains("FirmaA"));
        verifyThat(COMPANY_ADDRESS_TEXTFIELD, (TextField tf) -> tf
                .getText()
                .contains("AdresA"));
    }
}
