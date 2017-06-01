package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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

    private static final String ALL_COMPANIES_DELETE_COLUMN_HEADER_RESOURCE_KEY = "labels.allCompanies.delete";

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(resources.getString(ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY));
        backButton.setText(resources.getString(ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY));
        companyNameColumn.setText(resources.getString(ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY));
        companyAddressColumn.setText(resources.getString(ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY));
        deleteCompanyColumn.setText(resources.getString(ALL_COMPANIES_DELETE_COLUMN_HEADER_RESOURCE_KEY));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        deleteCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        Callback<TableColumn<Company, String>, TableCell<Company, String>> cellFactory = new
            Callback<TableColumn<Company, String>, TableCell<Company, String>>() {
            @Override
            public TableCell call(final TableColumn<Company, String> param) {
                final TableCell<Company, String> cell = new TableCell<Company, String>() {

                    final Button deleteButton = new Button("Usuń");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            deleteButton.setOnAction((ActionEvent event) -> {
                                Company company = getTableView()
                                    .getItems()
                                    .get(getIndex());
                                ScreenManager
                                    .getInstance()
                                    .showConfirmationDialog("Czy na pewno usunąć firmę?", () -> {
                                        Database
                                            .getCompanyList()
                                            .remove(company);
                                        companiesList.setItems(FXCollections.observableList(Database.getCompanyList()));
                                    });
                            });
                            setGraphic(deleteButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        deleteCompanyColumn.setCellFactory(cellFactory);

        companiesList.setItems(FXCollections.observableList(Database.getCompanyList()));
    }

    @FXML
    public void onBackButtonClick() {
        ScreenManager
            .getInstance()
            .setScreen(ScreenManager.WORK_RECORD_VIEW_ID);
    }

}
