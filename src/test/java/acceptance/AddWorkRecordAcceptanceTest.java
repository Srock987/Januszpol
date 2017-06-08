package acceptance;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;
import pl.edu.agh.eaiib.io.xp.StartupClass;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Pawel on 2017-06-07.
 */
public class AddWorkRecordAcceptanceTest extends ApplicationTest {

    private Scene scene;
    private String companyName = "customCompany";
    private String companyAddress = "companyAddress";
    private String date = "07.06.2017";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
        scene = stage.getScene();
    }

    @Test
    public void verifyAddCompanyTime(){
        addCompany();
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj czas pracy").clickOn("Dodaj czas pracy");
        moveTo(scene.lookup("#companyComboBox")).clickOn(scene.lookup("#companyComboBox"));
        moveTo(companyName).clickOn(companyName);
        moveTo(scene.lookup("#datePicker")).clickOn("#datePicker").write(date);
        moveTo("Zapisz").clickOn("Zapisz");
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Przegladaj czas pracy").clickOn("Przegladaj czas pracy");
        verifyThat("#workRecordsTableView",TableViewMatchers.hasTableCell(companyName));
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
}
