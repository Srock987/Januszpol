package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.Arrays;
import java.util.Observable;
import java.util.ResourceBundle;

public class ViewAllController implements Initializable {
    private static final String VIEW_ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY = "labels.view.all.title";
    private static final String VIEW_ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY = "buttons.view.all.back";

    @FXML
    Label titleLabel;

    @FXML
    Button backButton;

    ObservableList<Company> companies;

    @FXML
    ListView<Company> companiesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(VIEW_ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY));
        backButton.setText(resources.getString(VIEW_ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY));
        companies = FXCollections.observableList(Arrays.asList(new Company("Januszpol", "Adresik")));
        companiesList.setItems(companies);
    }

    public void onBackButtonClick() {
        ScreenManager.getInstance().setScreen(ScreenManager.MAIN_VIEW_ID);
    }
}
