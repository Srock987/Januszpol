package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuBar menuBar = new MenuBar();
        Menu view = new Menu("Widok");
        MenuItem menuItem = new MenuItem("Ziemniak");

        view.getItems().add(menuItem);
        menuBar.getMenus().add(view);
        AnchorPane node = (AnchorPane) ScreenManager.getInstance().getContainer().getChildrenUnmodifiable().get(0);
        node.getChildren().add(menuBar);
    }
}
