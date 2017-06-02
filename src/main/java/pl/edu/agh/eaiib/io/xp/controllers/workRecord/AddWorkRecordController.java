package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.time.LocalDate;

public class AddWorkRecordController
    extends WorkRecordController {

    @FXML
    public void onSaveButtonClicked(ActionEvent e) {
        String companyName = this.companyComboBox
            .getSelectionModel()
            .getSelectedItem();
        int hours = this.hoursSpinner.getValue();
        LocalDate localDate = this.datePicker.getValue();

        try {
            this.validateCompanyName(companyName);
            this.validateDate(localDate);

            Company company = Database.getCompanyByName(companyName);
            WorkRecord workRecord = new WorkRecord(company, hours, localDate);
            Database.addWorkRecord(workRecord);
            this.goToMainView();
        } catch (Exception exc) {
            ScreenManager
                .getInstance()
                .showErrorDialog(exc.getMessage());
        }
    }

}
