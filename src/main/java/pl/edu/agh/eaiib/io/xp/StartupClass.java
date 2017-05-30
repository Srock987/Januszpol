package pl.edu.agh.eaiib.io.xp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;

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
        ResourceBundle resources = ResourceUtils.loadLabelsForDefaultLocale();
        Scene scene = prepareScene(resources);

        String appTitle = resources.getString(APP_NAME_RESOURCE_KEY);

        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.info("Main window showed");
    }

    private Scene prepareScene(ResourceBundle resources) throws IOException {
        Scene scene = buildScene(resources);

        setStyleSheet(scene);
        return scene;
    }

    private Scene buildScene(ResourceBundle resources) throws IOException {
        Parent rootNode = loadFXMLForm(resources);
        logger.info("Loaded main fxml from file");
        return new Scene(rootNode);
    }

    private Parent loadFXMLForm(ResourceBundle resources) throws IOException {
        return FXMLLoader.load(getClass().getResource(MAIN_VIEW_FXML_LOCATION), resources);
    }

    private void setStyleSheet(Scene scene) {
        String css = this.getClass().getResource(CSS_STYLESHEET_LOCATION).toExternalForm();
        scene.getStylesheets().add(css);
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
