package pl.edu.agh.eaiib.io.xp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.util.ResourceBundle;

public class StartupClass extends Application {

    private static final Logger logger = LoggerFactory.getLogger(StartupClass.class);
    private static final String APP_NAME_RESOURCE_KEY = "labels.app.name";
    private static final String CSS_STYLESHEET_LOCATION = "/css/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        ScreenManager screenManager = ScreenManager.getInstance(primaryStage);
        screenManager.initialize();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(screenManager.getMenuBar());
        borderPane.setCenter(screenManager.getContainer());

        Scene scene = new Scene(borderPane);
        screenManager.setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
        ResourceBundle resources = ResourceUtils.loadLabelsForDefaultLocale();
        String appTitle = resources.getString(APP_NAME_RESOURCE_KEY);

        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        logger.info("Main window showed");
    }

    @Override
    public void stop() throws Exception {
        try {
            Database.saveData();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        super.stop();
    }
}
