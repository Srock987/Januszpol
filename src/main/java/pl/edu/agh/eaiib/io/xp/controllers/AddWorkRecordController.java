package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.InvalidValueException;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddWorkRecordController extends AbstractController {
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

    @FXML
    ComboBox<String> companyComboBox;

    @FXML
    DatePicker datePicker;

    @FXML
    Spinner<Integer> hoursSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWidgetsText(resources);
        loadCompanies();
        loadHours();
    }

    private void initializeWidgetsText(ResourceBundle resources) {
        titleLabel.setText(resources.getString(ADD_WORK_RECORD_VIEW_TITLE));
        selectCompanyLabel.setText(resources.getString(COMPANY_LABEL));
        selectDateLabel.setText(resources.getString(DATE_LABEL));
        selectHoursLabel.setText(resources.getString(HOURS_LABEL));
        saveButton.setText(resources.getString(SAVE_BUTTON));
        cancelButton.setText(resources.getString(CANCEL_BUTTON));
    }

    private void loadCompanies() {
        this.companyComboBox.getItems().removeAll(this.companyComboBox.getItems());
        List<Company> companies = Database.getCompanyList();
        for (Company c : companies)
            this.companyComboBox.getItems().add(c.getName());
    }

    private void loadHours() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        hoursSpinner.setValueFactory(valueFactory);
    }

    @FXML
    public void onCancelButtonClicked(ActionEvent e) {
        this.goToMainView();
    }

    private void goToMainView() {
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
        String companyName = this.companyComboBox.getSelectionModel().getSelectedItem();
        int hours = this.hoursSpinner.getValue();
        LocalDate localDate = this.datePicker.getValue();

        try {
            this.validateCompanyName(companyName);
            this.validateDate(localDate);

            Company company = this.findCompany(companyName);
            WorkRecord workRecord = new WorkRecord(company, hours, localDate);
            Database.addWorkRecord(workRecord);
//            this.showInfoDialog("Dodano firmę","Poprawnie dodano czas pracy dla firmy " + company.getName() + ".");
            this.goToMainView();
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

    private Company findCompany(String companyName) {
        ArrayList<Company> companies =  Database.getCompanyList();
        List<Company> matchingCompanies = companies.stream().filter(company -> company.getName().equals(companyName))
                                                    .collect(Collectors.toList());
        return matchingCompanies.get(0);
    }

    private void goToWorkRecordsList() {

    }
}
