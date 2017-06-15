package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.CompanyRemote;
import pl.edu.agh.eaiib.io.xp.model.DataRecordRemote;
import pl.edu.agh.eaiib.io.xp.utils.InvalidValueException;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by frben on 02.06.2017.
 */
public abstract class WorkRecordController extends AbstractController {

    String WORK_RECORD_VIEW_TITLE = "labels.add_work_record.title";
    private static final String COMPANY_LABEL = "labels.add_work_record.company_label";
    private static final String DATE_LABEL = "labels.add_work_record.date_label";
    private static final String HOURS_LABEL = "labels.add_work_record.hours_label";
    private static final String SAVE_BUTTON = "buttons.add_work_record.save_button";
    private static final String CANCEL_BUTTON = "buttons.add_work_record.cancel_button";

    @FXML
    protected Label titleLabel;

    @FXML
    protected Label selectCompanyLabel;

    @FXML
    protected Label selectDateLabel;

    @FXML
    protected Label selectHoursLabel;

    @FXML
    protected Button saveButton;

    @FXML
    protected Button cancelButton;

    @FXML
    protected ComboBox<String> companyComboBox;

    @FXML
    protected DatePicker datePicker;

    @FXML
    protected Spinner<Integer> hoursSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeWidgetsText(resources);
        loadCompanies();
        loadHours();
    }

    private void initializeWidgetsText(ResourceBundle resources) {
        titleLabel.setText(resources.getString(WORK_RECORD_VIEW_TITLE));
        selectCompanyLabel.setText(resources.getString(COMPANY_LABEL));
        selectDateLabel.setText(resources.getString(DATE_LABEL));
        selectHoursLabel.setText(resources.getString(HOURS_LABEL));
        saveButton.setText(resources.getString(SAVE_BUTTON));
        cancelButton.setText(resources.getString(CANCEL_BUTTON));
    }

    private void loadCompanies() {
        companyComboBox.getItems().removeAll(companyComboBox.getItems());
        List<DataRecordRemote> companies = Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME).getAll();
        for (DataRecordRemote c : companies)
            companyComboBox.getItems().add(((CompanyRemote) c).getName());
    }

    private void loadHours() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        hoursSpinner.setValueFactory(valueFactory);
    }

    void validateCompanyName(String name) throws InvalidValueException {
        if (name == null || name.isEmpty())
            throw new InvalidValueException("Musisz wybrać firmę!");
    }

    void validateDate(LocalDate date) throws InvalidValueException {
        if (date == null)
            throw new InvalidValueException("Musisz wybrać datę!");

        LocalDate now = LocalDate.now();
        if (date.isAfter(now))
            throw new InvalidValueException("Nie możesz wybrać daty z przyszłości!");
    }

    @FXML
    protected void onCancelButtonClicked(ActionEvent e) {
        goToMainView();
    }

    protected void goToMainView() {
        ScreenManager
            .getInstance().setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
    }

}
