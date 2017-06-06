package acceptance;

import com.google.common.base.Predicate;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.awt.*;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Created by Pawel on 2017-06-06.
 */
public class AddCompanyAcceptanceTest extends ApplicationTest {

    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        StartupClass startupClass = new StartupClass();
        startupClass.start(stage);
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
        scene = stage.getScene();
    }


    @Test
    public void verifyCancelAddingCompany() {
        String companyName = "customCompany1";
        String companyAddress = "companyAddress1";
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Dodaj firmę").clickOn("Dodaj firmę");
        moveTo("#companyNameTextField").clickOn("#companyNameTextField").write(companyName);
        moveTo("#companyAddressTextField").clickOn("#companyAddressTextField").write(companyAddress);
        moveTo("Anuluj").clickOn("Anuluj");
        moveTo(scene.lookup("#main-menu")).clickOn(scene.lookup("#main-menu"));
        moveTo("Lista firm").clickOn("Lista firm");
        verifyThat("#companiesList", new Predicate<Node>() {
            @Override
            public boolean apply(Node node) {
                TableViewMatchers.hasTableCell(companyName);
                return true;
            }
        });
    }

    @Test
    public void verifySaveAddingCompany() {
        String companyName = "customCompany";
        String companyAddress = "companyAddress";
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
