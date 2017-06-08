package acceptance;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Pawel on 2017-06-07.
 */
public class EditCompanyAcceptanceTest extends ApplicationTest {

    private Scene scene;
    private String companyFirstName = "customCompany1";
    private String companyFirstAddress = "companyAddress1";
    private String companySecName = "customCompany2";
    private String companySecAddress = "companyAddress2";
    private String companyThirdName = "customCompany3";
    private String companyThirdAddress = "companyAddress3";
    private String companyFourthName = "customCompany4";
    private String companyFourthAddress = "companyAddress4";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
        scene = stage.getScene();
    }

    @Test
    public void VerifyEditSaveCompany(){
        addCompany(companyFirstName,companyFirstAddress);
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Lista firm").clickOn("Lista firm");
        moveTo(scene.lookup("#companyEdit-"+companyFirstName)).clickOn(scene.lookup("#companyEdit-"+companyFirstName));
        changeCompany(companySecName,companySecAddress);
        moveTo("Zapisz").clickOn("Zapisz");
        verifyThat("#companiesList", TableViewMatchers.hasTableCell(companySecName));
    }

    @Test
    public void VerifyEditCancelCompany(){
        addCompany(companyThirdName,companyThirdAddress);
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Lista firm").clickOn("Lista firm");
        moveTo(scene.lookup("#companyEdit-"+companyThirdName)).clickOn(scene.lookup("#companyEdit-"+companyThirdName));
        changeCompany(companyFourthName,companyFourthAddress);
        moveTo("Anuluj").clickOn("Anuluj");
        verifyThat("#companiesList", TableViewMatchers.hasTableCell(companyThirdName));
    }

    private void addCompany(String company, String address){
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj firmę").clickOn("Dodaj firmę");
        fillAndSavecompany(company,address);
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Lista firm").clickOn("Lista firm");
        verifyThat("#companiesList", TableViewMatchers.hasTableCell(company));
    }

    private void fillAndSavecompany(String company, String address){
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").write(company);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").write(address);
        moveTo("Zapisz").clickOn("Zapisz");
    }

    private void changeCompany(String company, String address){
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").clickOn("#companyNameTextField").write(company);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").clickOn("#companyAddressTextField").write(address);
    }
}
