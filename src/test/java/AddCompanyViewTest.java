import com.google.common.base.Predicate;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.StartupClass;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Created by frben on 04.05.2017.
 */
public class AddCompanyViewTest extends ApplicationTest {

    private static final String TITLE_LABEL = "#newCompanyTitleLabel";
    private static final String COMPANY_NAME_LABEL = "#newCompanyNameLabel";
    private static final String COMPANY_ADDRESS_LABEL = "#newCompanyAddressLabel";
    private static final String COMPANY_NAME_TEXTFIELD = "#newCompanyNameTextField";
    private static final String COMPANY_ADDRESS_TEXTFIELD = "#newCompanyAddressTextField";
    private static final String ADD_NEW_COMPANY_BUTTON = "#newCompanyAddButton";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
    }

    @Before
    public void openAddCompanyView(){
        clickOn("#addCompanyButton");
    }

    @Test
    public void verifyLabelsText(){
        verifyThat(TITLE_LABEL, hasText("Dodaj nową firmę"));
        verifyThat(COMPANY_NAME_LABEL, hasText("Nazwa firmy"));
        verifyThat(COMPANY_ADDRESS_LABEL, hasText("Adres firmy"));
        verifyThat(ADD_NEW_COMPANY_BUTTON, hasText("Dodaj firmę"));
    }

    @Test
    public void verifyTextFields(){
        clickOn(COMPANY_NAME_TEXTFIELD).write("FirmaA");
        clickOn(COMPANY_ADDRESS_TEXTFIELD).write("AdresA");

        verifyThat(COMPANY_NAME_TEXTFIELD, (TextField tf) ->
                tf.getText().contains("FirmaA"));
        verifyThat(COMPANY_ADDRESS_TEXTFIELD, (TextField tf) ->
                tf.getText().contains("AdresA"));
    }

    public <T extends Node> T find(String query){
        // usage: TextField textField = find("#newCompanyNameTextField");
        return (T) lookup(query).queryAll().iterator().next();
    }
}
