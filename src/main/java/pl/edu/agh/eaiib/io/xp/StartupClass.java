package pl.edu.agh.eaiib.io.xp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.controllers.MainViewController;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class StartupClass extends Application {
    private static final Logger logger = LoggerFactory.getLogger(StartupClass.class);
    private static final String APP_NAME_RESOURCE_KEY = "labels.app.name";
    private static final String CSS_STYLESHEET_LOCATION = "/css/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.loadScreen(ScreenManager.MAIN_VIEW_ID, ScreenManager.MAIN_VIEW_FXML);
        screenManager.loadScreen(ScreenManager.ADD_COMPANY_VIEW_ID, ScreenManager.ADD_COMPANY_VIEW_FXML);
        screenManager.setScreen(ScreenManager.MAIN_VIEW_ID);

        Scene scene = new Scene(screenManager.getContainer());
        scene.getStylesheets().add(CSS_STYLESHEET_LOCATION);

        ResourceBundle resources = ResourceUtils.loadLabelsForDefaultLocale();
        String appTitle = resources.getString(APP_NAME_RESOURCE_KEY);

        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.show();

        logger.info("Main window showed");
    }


}
