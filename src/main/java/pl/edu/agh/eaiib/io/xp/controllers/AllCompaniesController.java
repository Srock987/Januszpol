package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AllCompaniesController
    implements Initializable {
    private static final String ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY = "labels.allCompanies.title";
    private static final String ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY = "buttons.allCompanies.back";
    private static final String ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.name";
    private static final String ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.address";

    @FXML
    private Label titleLabel;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Company> companiesList;

    @FXML
    private TableColumn<Company, String> companyNameColumn;

    @FXML
    private TableColumn<Company, String> companyAddressColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY));
        backButton.setText(resources.getString(ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY));
        companyNameColumn.setText(resources.getString(ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY));
        companyAddressColumn.setText(resources.getString(ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        companiesList.setItems(FXCollections.observableList(Database.getCompanyList()));
    }

    @FXML
    public void onBackButtonClick() {
        ScreenManager.getInstance().setScreen(ScreenManager.WORK_RECORD_VIEW_ID);
    }

}
