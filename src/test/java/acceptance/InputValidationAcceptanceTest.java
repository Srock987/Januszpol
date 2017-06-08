package acceptance;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;
import pl.edu.agh.eaiib.io.xp.StartupClass;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Pawel on 2017-06-07.
 */
public class InputValidationAcceptanceTest extends ApplicationTest {

    private String companyName = "customCompany";
    private String companyAddress = "companyAddress";
    private String date = "07.10.2017";
    private Scene scene;
    private String longName = "TenTextMaPonadSiedemdziesiatZnakow!!!TenTextMaPonadSiedemdziesiatZnakow!!!";
    private String shortName = "Ni";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
        scene = stage.getScene();
    }

    @Test
    public void verifyLongCompanyNameValidation(){
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj firmę").clickOn("Dodaj firmę");
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").write(longName);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").write(longName);
        moveTo("Zapisz").clickOn("Zapisz");
        verifyThat("Błąd", Node::isVisible);
        moveTo("OK").clickOn("OK");
    }

    @Test
    public void verifyShortCompanyNameValidation(){
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj firmę").clickOn("Dodaj firmę");
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").write(shortName);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").write(shortName);
        moveTo("Zapisz").clickOn("Zapisz");
        verifyThat("Błąd", Node::isVisible);
        moveTo("OK").clickOn("OK");
    }



    private void addCompany(){
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj firmę").clickOn("Dodaj firmę");
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").write(companyName);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").write(companyAddress);
        moveTo("Zapisz").clickOn("Zapisz");
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Lista firm").clickOn("Lista firm");
        verifyThat("#companiesList", TableViewMatchers.hasTableCell(companyName));
    }

    @Test
    public void verifyWorkRecordValidation(){
        addCompany();
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj czas pracy").clickOn("Dodaj czas pracy");
        moveTo(scene.lookup("#companyComboBox")).clickOn(scene.lookup("#companyComboBox"));
        moveTo(companyName).clickOn(companyName);
        moveTo(scene.lookup("#datePicker")).clickOn("#datePicker").write(date);
        moveTo("Zapisz").clickOn("Zapisz");
        verifyThat("Błąd", Node::isVisible);
        moveTo("OK").clickOn("OK");
    }
}
