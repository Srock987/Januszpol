package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.*;
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

            CompanySetRemote companySet = (CompanySetRemote) Database.getInstance().getDataRecordSet(Database.COMPANY);
            companySet.getCompanyByName(companyName);

            CompanyRemote company = companySet.getCompanyByName(companyName);
            WorkRecordSetRemote workRecordSet = (WorkRecordSetRemote) Database.getInstance().getDataRecordSet(Database.WORKRECORD);
            workRecordSet.add(company, hours, localDate);
            workRecordSet.save();

            this.goToMainView();
        } catch (Exception exc) {
            ScreenManager
                .getInstance()
                .showErrorDialog(exc.getMessage());
        }
    }

}
