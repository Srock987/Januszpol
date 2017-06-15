package pl.edu.agh.eaiib.io.xp.controllers.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.*;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

public class AddCompanyController extends CompanyController {

    @FXML
    public void onAddCompanyButtonClick(ActionEvent e) throws Throwable {
        String companyName = companyNameTextField.getText();
        String address = companyAddressTextField.getText();
        try{

            Database database = Database.getInstance();
            CompanySetRemote companySet = (CompanySetRemote) database.getDataRecordSet(Database.COMPANY_FILE_NAME);
            companySet.add(companyName, address);
            companySet.save();

            ScreenManager.getInstance().setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
        } catch (RuntimeException exc){
            ScreenManager.getInstance().showErrorDialog(exc.getMessage());
        }
    }

}
