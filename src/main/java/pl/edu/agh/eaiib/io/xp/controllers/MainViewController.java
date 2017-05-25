package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private static final String APP_TITLE_RESOURCE_KEY = "labels.app.title";
    private static final String BUTTON_ADD_COMPANY_RESOURCE_KEY = "buttons.add.company";
    private static final String BUTTON_ADD_NOTE_RESOURCE_KEY = "buttons.add.note";
    private static final String BUTTON_VIEW_ALL_RESOURCE_KEY = "buttons.view.all";
    private static final String BUTTON_GENERATE_REPORT_RESOURCE_KEY = "buttons.generate.report";

    @FXML
    Label titleLabel;

    @FXML
    Button addCompanyButton;

    @FXML
    Button viewAllButton;

    @FXML
    Button addNoteButton;

    @FXML
    Button generateReportButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(APP_TITLE_RESOURCE_KEY).toUpperCase());
        addCompanyButton.setText(resources.getString(BUTTON_ADD_COMPANY_RESOURCE_KEY));
        addNoteButton.setText(resources.getString(BUTTON_ADD_NOTE_RESOURCE_KEY));
        viewAllButton.setText(resources.getString(BUTTON_VIEW_ALL_RESOURCE_KEY));
        generateReportButton.setText(resources.getString(BUTTON_GENERATE_REPORT_RESOURCE_KEY));
    }

    public void openAddCompanyView() {
        ScreenManager.getInstance().setScreen(ScreenManager.ADD_COMPANY_VIEW_ID);
    }

}
