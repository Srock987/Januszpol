package pl.edu.agh.eaiib.io.xp.controllers.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class CompanyController
    extends AbstractController {

    String COMPANY_TITLE = "labels.add_company.title";
    private static final String COMPANY_NAME_LABEL = "labels.add_company.company_name";
    private static final String ADDRESS_LABEL = "labels.add_company.address";
    private static final String ADD_BUTTON_TEXT = "buttons.add_company.submit";
    private static final String CANCEL_BUTTON_TEXT = "buttons.add_company.cancel";

    @FXML
    protected Label companyTitleLabel;

    @FXML
    protected Label companyNameLabel;

    @FXML
    protected Label companyAddressLabel;

    @FXML
    protected Button companyAddButton;

    @FXML
    protected Button companyCancelButton;

    @FXML
    protected TextField companyNameTextField;

    @FXML
    protected TextField companyAddressTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyTitleLabel.setText(resources.getString(COMPANY_TITLE));
        companyNameLabel.setText(resources.getString(COMPANY_NAME_LABEL));
        companyAddressLabel.setText(resources.getString(ADDRESS_LABEL));
        companyAddButton.setText(resources.getString(ADD_BUTTON_TEXT));
        companyCancelButton.setText(resources.getString(CANCEL_BUTTON_TEXT));
    }

    @FXML
    public void onCancelButtonClick(ActionEvent e) throws Throwable {
        ScreenManager.getInstance().setScreen(ScreenManager.ALL_COMPANIES_VIEW_ID);
    }
}
