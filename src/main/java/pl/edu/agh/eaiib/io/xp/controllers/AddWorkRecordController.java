package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWorkRecordController implements Initializable {
    private static final String ADD_WORK_RECORD_VIEW_TITLE = "labels.add_work_record.title";
    private static final String COMPANY_LABEL = "labels.add_work_record.company_label";
    private static final String DATE_LABEL = "labels.add_work_record.date_label";
    private static final String HOURS_LABEL = "labels.add_work_record.hours_label";
    private static final String SAVE_BUTTON = "buttons.add_work_record.save_button";
    private static final String CANCEL_BUTTON = "buttons.add_work_record.cancel_button";

    @FXML
    Label titleLabel;

    @FXML
    Label selectCompanyLabel;

    @FXML
    Label selectDateLabel;

    @FXML
    Label selectHoursLabel;

    @FXML
    Button saveButton;

    @FXML
    Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(ADD_WORK_RECORD_VIEW_TITLE));
        selectCompanyLabel.setText(resources.getString(COMPANY_LABEL));
        selectDateLabel.setText(resources.getString(DATE_LABEL));
        selectHoursLabel.setText(resources.getString(HOURS_LABEL));
        saveButton.setText(resources.getString(SAVE_BUTTON));
        cancelButton.setText(resources.getString(CANCEL_BUTTON));
    }
}
