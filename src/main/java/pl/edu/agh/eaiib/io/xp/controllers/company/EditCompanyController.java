package pl.edu.agh.eaiib.io.xp.controllers.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

public class EditCompanyController
    extends CompanyController {

    private Company editingCompany;

    public EditCompanyController() {
        COMPANY_TITLE = "labels.edit_company.title";
    }

    @FXML
    public void onSaveCompanyButtonClick(ActionEvent e) throws Throwable {
        String companyName = companyNameTextField.getText();
        String address = companyAddressTextField.getText();
        try{
            Company newCompany = new Company(companyName, address);
            editingCompany.setName(newCompany.getName());
            editingCompany.setAddress(newCompany.getAddress());
            ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
        } catch (RuntimeException exc){
            ScreenManager.getInstance().showErrorDialog(exc.getMessage());
        }
    }


    public void setEditingCompany(Company editingCompany) {
        this.editingCompany = editingCompany;
        companyNameTextField.setText(editingCompany.getName());
        companyAddressTextField.setText(editingCompany.getAddress());
    }
}
