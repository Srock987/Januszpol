package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.InvalidValueException;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EditWorkRecordController
    extends AbstractController {
    private static final String EDIT_WORK_RECORD_VIEW_TITLE = "labels.edit_work_record.title";
    private static final String COMPANY_LABEL = "labels.add_work_record.company_label";
    private static final String DATE_LABEL = "labels.add_work_record.date_label";
    private static final String HOURS_LABEL = "labels.add_work_record.hours_label";
    private static final String SAVE_BUTTON = "buttons.add_work_record.save_button";
    private static final String CANCEL_BUTTON = "buttons.add_work_record.cancel_button";

    @FXML
    private Label titleLabel;

    @FXML
    private Label selectCompanyLabel;

    @FXML
    private Label selectDateLabel;

    @FXML
    private Label selectHoursLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> companyComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> hoursSpinner;

    private WorkRecord editingWorkRecord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWidgetsText(resources);
        loadCompanies();
        loadHours();
    }

    private void initializeWidgetsText(ResourceBundle resources) {
        titleLabel.setText(resources.getString(EDIT_WORK_RECORD_VIEW_TITLE));
        selectCompanyLabel.setText(resources.getString(COMPANY_LABEL));
        selectDateLabel.setText(resources.getString(DATE_LABEL));
        selectHoursLabel.setText(resources.getString(HOURS_LABEL));
        saveButton.setText(resources.getString(SAVE_BUTTON));
        cancelButton.setText(resources.getString(CANCEL_BUTTON));
    }

    private void loadCompanies() {
        companyComboBox.getItems().removeAll(companyComboBox.getItems());
        List<Company> companies = Database.getCompanyList();
        for (Company c : companies)
            companyComboBox.getItems().add(c.getName());
    }

    private void loadHours() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        hoursSpinner.setValueFactory(valueFactory);
    }

    @FXML
    public void onCancelButtonClicked(ActionEvent e) {
        goToMainView();
    }

    private void goToMainView() {
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
        String companyName = companyComboBox.getSelectionModel().getSelectedItem();
        int hours = hoursSpinner.getValue();
        LocalDate localDate = datePicker.getValue();

        try {
            validateCompanyName(companyName);
            validateDate(localDate);

            Company company = Database.getCompanyByName(companyName);
            editingWorkRecord.setCompany(company);
            editingWorkRecord.setHours(hours);
            editingWorkRecord.setDate(localDate);
            goToMainView();
        } catch (Exception exc) {
            ScreenManager.getInstance().showErrorDialog(exc.getMessage());
        }
    }

    private void validateCompanyName(String name) throws InvalidValueException {
        if (name == null || name.isEmpty())
            throw new InvalidValueException("Musisz wybrać firmę!");
    }

    private void validateDate(LocalDate date) throws InvalidValueException {
        if (date == null)
            throw new InvalidValueException("Musisz wybrać datę!");

        LocalDate now = LocalDate.now();
        if (date.isAfter(now))
            throw new InvalidValueException("Nie możesz wybrać daty z przyszłości!");
    }


    public void setEditingWorkRecord(WorkRecord editingWorkRecord) {
        this.editingWorkRecord = editingWorkRecord;
        companyComboBox.getSelectionModel().select(editingWorkRecord.getCompanyName());
        datePicker.setValue(editingWorkRecord.getDate());
        hoursSpinner.getValueFactory().setValue(editingWorkRecord.getHours());
    }
}
