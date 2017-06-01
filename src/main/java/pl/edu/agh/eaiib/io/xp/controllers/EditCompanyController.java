package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCompanyController
    extends AbstractController {

    private static final String EDIT_COMPANY_TITLE = "labels.edit_company.title";
    private static final String COMPANY_NAME_LABEL = "labels.add_company.company_name";
    private static final String ADDRESS_LABEL = "labels.add_company.address";
    private static final String ADD_BUTTON_TEXT = "buttons.add_company.submit";
    private static final String CANCEL_BUTTON_TEXT = "buttons.add_company.cancel";

    @FXML
    Label editCompanyTitleLabel;

    @FXML
    Label editCompanyNameLabel;

    @FXML
    Label editCompanyAddressLabel;

    @FXML
    Button editCompanyAddButton;

    @FXML
    Button editCompanyCancelButton;

    @FXML
    TextField editCompanyNameTextField;

    @FXML
    TextField editCompanyAddressTextField;

    Company editingCompany;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editCompanyTitleLabel.setText(resources.getString(EDIT_COMPANY_TITLE));
        editCompanyNameLabel.setText(resources.getString(COMPANY_NAME_LABEL));
        editCompanyAddressLabel.setText(resources.getString(ADDRESS_LABEL));
        editCompanyAddButton.setText(resources.getString(ADD_BUTTON_TEXT));
        editCompanyCancelButton.setText(resources.getString(CANCEL_BUTTON_TEXT));
    }

    @FXML
    public void onSaveCompanyButtonClick(ActionEvent e) throws Throwable {
        String companyName = editCompanyNameTextField.getText();
        String address = editCompanyAddressTextField.getText();
        try{
            Company newCompany = new Company(companyName, address);
            editingCompany.setName(newCompany.getName());
            editingCompany.setAddress(newCompany.getAddress());
            ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
        } catch (RuntimeException exc){
            ScreenManager.getInstance().showErrorDialog(exc.getMessage());
        }
    }

    @FXML
    public void onCancelEditingButtonClick(ActionEvent e) throws Throwable {
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
    }

    public void setEditingCompany(Company editingCompany) {
        this.editingCompany = editingCompany;
        editCompanyNameTextField.setText(editingCompany.getName());
        editCompanyAddressTextField.setText(editingCompany.getAddress());
    }
}
