package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.utils.TableButtonCallback;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AllCompaniesController
    extends AbstractController {
    private static final String ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY = "labels.allCompanies.title";
    private static final String ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY = "buttons.allCompanies.back";
    private static final String ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.name";
    private static final String ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.address";
    private static final String ALL_COMPANIES_DELETE_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.delete";
    private static final String ALL_COMPANIES_EDIT_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.edit";

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

    @FXML
    private TableColumn<Company, String> deleteCompanyColumn;

    @FXML
    private TableColumn<Company, String> editCompanyColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY));
        backButton.setText(resources.getString(ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY));
        companyNameColumn.setText(resources.getString(ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY));
        companyAddressColumn.setText(resources.getString(ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY));
        editCompanyColumn.setText(resources.getString(ALL_COMPANIES_EDIT_COLUMN_HEADER_RESOURCE_KEY));
        deleteCompanyColumn.setText(resources.getString(ALL_COMPANIES_DELETE_COLUMN_HEADER_RESOURCE_KEY));

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        editCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        deleteCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        TableButtonCallback<Company> deleteButtonCallback = new TableButtonCallback<>();
        deleteButtonCallback.setButtonText("Usuń");
        deleteButtonCallback.setListener(item -> ScreenManager
            .getInstance()
            .showConfirmationDialog("Czy na pewno usunąć firmę?", () -> {
                Database
                    .getCompanyList()
                    .remove(item);
                companiesList.setItems(FXCollections.observableList(Database.getCompanyList()));
            }));
        deleteCompanyColumn.setCellFactory(deleteButtonCallback);

        TableButtonCallback<Company> editButtonCallback = new TableButtonCallback<>();
        editButtonCallback.setButtonText("Edytuj");
        editButtonCallback.setListener(item -> {
            ScreenManager.getInstance().setScreen(ScreenManager.EDIT_COMPANY_VIEW_ID);
            ((EditCompanyController) ScreenManager.currentController).setEditingCompany((Company) item);
        });
        editCompanyColumn.setCellFactory(editButtonCallback);

        companiesList.setItems(FXCollections.observableList(Database.getCompanyList()));
    }

    @FXML
    public void onBackButtonClick() {
        ScreenManager
            .getInstance()
            .setScreen(ScreenManager.ALL_WORK_RECORDS_VIEW_ID);
    }

}
