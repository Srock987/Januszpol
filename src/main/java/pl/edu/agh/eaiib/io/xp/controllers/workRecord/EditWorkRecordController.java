package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.time.LocalDate;

public class EditWorkRecordController
    extends WorkRecordController {

    private WorkRecord editingWorkRecord;

    public EditWorkRecordController() {
        WORK_RECORD_VIEW_TITLE = "labels.edit_work_record.title";
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
        String companyName = companyComboBox
            .getSelectionModel()
            .getSelectedItem();
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
            ScreenManager
                .getInstance()
                .showErrorDialog(exc.getMessage());
        }
    }

    public void setEditingWorkRecord(WorkRecord editingWorkRecord) {
        this.editingWorkRecord = editingWorkRecord;
        companyComboBox
            .getSelectionModel()
            .select(editingWorkRecord.getCompanyName());
        datePicker.setValue(editingWorkRecord.getDate());
        hoursSpinner
            .getValueFactory()
            .setValue(editingWorkRecord.getHours());
    }
}
