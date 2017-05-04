package pl.edu.agh.eaiib.io.xp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ScreenManager {
    public static final String MAIN_VIEW_ID = "mainView";
    public static final String MAIN_VIEW_FXML = "/fxml/mainView.fxml";
    public static final String ADD_COMPANY_VIEW_ID = "addCompanyView";
    public static final String ADD_COMPANY_VIEW_FXML = "/fxml/addCompanyView.fxml";

    private static final ScreenManager INSTANCE = new ScreenManager();

    private final ScreensController screensController;

    public static ScreenManager getInstance() {
        return INSTANCE;
    }

    private ScreenManager() {
        screensController = new ScreensController();
    }

    public boolean loadScreen(String name, String resource) {
        ResourceBundle bundle = ResourceUtils.loadLabelsForDefaultLocale();
        return screensController.loadScreen(name, resource, bundle);
    }

    public boolean setScreen(String name) {

        return screensController.setScreen(name);
    }

    public String getActiveViewId() {
        return screensController.getActiveView();
    }

    public Node getContainer() {
        return screensController;
    }

    private static final class ScreensController extends StackPane {
        private static final Logger logger = LoggerFactory.getLogger(ScreensController.class);

        private Map<String, Node> screens = new HashMap<>();

        private void addScreen(String name, Node screen) {
            screens.put(name, screen);
        }

        private boolean loadScreen(String name, String resource, ResourceBundle resourceBundle) {
            try {
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource), resourceBundle);
                Parent loadScreen = myLoader.load();
                addScreen(name, loadScreen);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        private boolean setScreen(final String name) {
            if (getChildren().isEmpty()) {
                getChildren().add(screens.get(name));
            } else {
                getChildren().remove(0);
                getChildren().add(0, screens.get(name));
            }
            return true;
        }

        private String getActiveView() {
            Node view = getChildren().get(0);
            for (Map.Entry<String, Node> entry : screens.entrySet()) {
                if (view.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            return MAIN_VIEW_ID;
        }
    }
}
