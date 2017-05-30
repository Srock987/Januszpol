package pl.edu.agh.eaiib.io.xp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import pl.edu.agh.eaiib.io.xp.utils.ResourceUtils;

import java.util.*;

public class ScreenManager {
    public static final String MAIN_VIEW_ID = "mainView";
    public static final String MAIN_VIEW_FXML = "/fxml/mainView.fxml";
    public static final String ADD_COMPANY_VIEW_ID = "addCompanyView";
    public static final String ADD_COMPANY_VIEW_FXML = "/fxml/addCompanyView.fxml";
    public static final String ALL_COMPANIES_VIEW_ID = "allCompaniesView";
    public static final String ALL_COMPANIES_VIEW_FXML = "/fxml/viewAllCompanies.fxml";

    private static final ScreenManager INSTANCE = new ScreenManager();

    private final ScreensController screensController;

    public static ScreenManager getInstance() {
        return INSTANCE;
    }

    private ScreenManager() {
        screensController = new ScreensController();
    }

    public void initialize() {
        loadScreen(MAIN_VIEW_ID, MAIN_VIEW_FXML);
        loadScreen(ADD_COMPANY_VIEW_ID, ADD_COMPANY_VIEW_FXML);
        loadScreen(ALL_COMPANIES_VIEW_ID, ALL_COMPANIES_VIEW_FXML);
    }

    private boolean loadScreen(String name, String resource) {
        ResourceBundle bundle = ResourceUtils.loadLabelsForDefaultLocale();
        return screensController.loadScreen(name, resource, bundle);
    }

    public boolean setScreen(String name) {
        return screensController.setScreen(name);
    }

    public String getActiveViewId() {
        return screensController.getActiveView();
    }

    public Parent getContainer() {
        return screensController;
    }

    public Node getMenuBar() {
        List<MenuItem> menuItemsList = new ArrayList<>();
        screensController.screens.keySet().forEach(screenId -> menuItemsList.add(createMenuItem(screenId)));
        Menu menu = new Menu("Widok");
        menu.getItems().addAll(menuItemsList);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private MenuItem createMenuItem(String screenId) {
        ResourceBundle resources = ResourceUtils.loadLabelsForDefaultLocale();
        String menuText = resources.getString(screenId);
        MenuItem menuItem = new MenuItem(menuText);
        menuItem.setOnAction(event -> setScreen(screenId));
        return menuItem;
    }

    private static final class ScreensController extends StackPane {
        private Map<String, Node> screens = new LinkedHashMap<>();

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
