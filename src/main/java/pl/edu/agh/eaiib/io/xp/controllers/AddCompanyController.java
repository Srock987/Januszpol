package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCompanyController implements Initializable{

    private static final String ADD_COMPANY_TITLE = "labels.add_company.title";
    private static final String COMPANY_NAME_LABEL = "labels.add_company.company_name";
    private static final String ADDRESS_LABEL = "labels.add_company.address";
    private static final String ADD_BUTTON_TEXT = "buttons.add_company.submit";

    @FXML
    Label newCompanyTitleLabel;

    @FXML
    Label newCompanyNameLabel;

    @FXML
    Label newCompanyAddressLabel;

    @FXML
    Button newCompanyAddButton;

    @FXML
    TextField newCompanyNameTextField;

    @FXML
    TextField newCompanyAddressTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newCompanyTitleLabel.setText(resources.getString(ADD_COMPANY_TITLE));
        newCompanyNameLabel.setText(resources.getString(COMPANY_NAME_LABEL));
        newCompanyAddressLabel.setText(resources.getString(ADDRESS_LABEL));
        newCompanyAddButton.setText(resources.getString(ADD_BUTTON_TEXT));
    }


    @FXML
    public void onAddCompanyButtonClick(ActionEvent e){
        String companyName = newCompanyNameLabel.getText();
        String address = newCompanyAddressLabel.getText();
        Company newCompany = new Company(companyName, address);
        //TODO: save company
    }
}
