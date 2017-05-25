package pl.edu.agh.eaiib.io.xp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class StartupClass extends Application {

    private static final Logger logger = LoggerFactory.getLogger(StartupClass.class);
    private static final String APP_NAME_RESOURCE_KEY = "labels.app.name";
    private static final String MAIN_VIEW_FXML_LOCATION = "/fxml/mainView.fxml";
    private static final String CSS_STYLESHEET_LOCATION = "/css/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.initialize();

        Scene scene = new Scene(screenManager.getContainer());
        screenManager.setScreen(ScreenManager.MAIN_VIEW_ID);
        ResourceBundle resources = ResourceUtils.loadLabelsForDefaultLocale();
        String appTitle = resources.getString(APP_NAME_RESOURCE_KEY);

        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.info("Main window showed");
    }


    private void setStyleSheet(Scene scene) {
        String css = this.getClass().getResource(CSS_STYLESHEET_LOCATION).toExternalForm();
        scene.getStylesheets().add(css);
    }
}
