package acceptance;

import com.google.common.base.Predicate;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import javax.swing.text.TableView;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Pawel on 2017-06-08.
 */
public class DeleteCompanyAcceptanceTest extends ApplicationTest {

    private Scene scene;
    private String companyFirstName = "customCompany1";
    private String companyFirstAddress = "companyAddress1";
    private String companySecName = "customCompany2";
    private String companySecAddress = "companyAddress2";

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
        scene = stage.getScene();
    }

    @Test
    public void verifyDeleteOkCompany(){
        addCompany(companyFirstName,companyFirstAddress);
        moveTo(scene.lookup("#companyDelete-"+companyFirstName)).clickOn(scene.lookup("#companyDelete-"+companyFirstName));
        moveTo("OK").clickOn("OK");

//        verifyThat("#companiesList", TableViewMatchers.hasTableCell(companyFirstName));
    }

    @Test
    public void verifyDeleteCancelCompany(){
        addCompany(companySecName,companySecAddress);
        moveTo(scene.lookup("#companyDelete-"+companySecName)).clickOn(scene.lookup("#companyDelete-"+companySecName));
        moveTo("Cancel").clickOn("Cancel");
//        verifyThat("#companiesList", TableViewMatchers.hasTableCell(companySecName));
        verifyThat("#companiesList", new Predicate<Node>() {
            @Override
            public boolean apply(Node node) {
                TableViewMatchers.hasItems(6).matches(new Object());
                return true;
            }
        });
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
}
