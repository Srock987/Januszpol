package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.model.CompaniesListener;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ViewAllController implements Initializable, CompaniesListener {
    private static final String VIEW_ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY = "labels.view.all.title";
    private static final String VIEW_ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY = "buttons.view.all.back";
    private static final String VIEW_ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY = "labels.view.all.name";
    private static final String VIEW_ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY = "labels.view.all.address";

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
        titleLabel.setText(resources.getString(VIEW_ALL_COMPANIES_TITLE_LABEL_RESOURCE_KEY));
        backButton.setText(resources.getString(VIEW_ALL_COMPANIES_BACK_BUTTON_RESOURCE_KEY));
        companyNameColumn.setText(resources.getString(VIEW_ALL_COMPANIES_NAME_COLUMN_HEADER_RESOURCE_KEY));
        companyAddressColumn.setText(resources.getString(VIEW_ALL_COMPANIES_ADDRESS_COLUMN_HEADER_RESOURCE_KEY));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        updateCompaniesView();
    }

    @FXML
    public void onBackButtonClick() {
        ScreenManager.getInstance().setScreen(ScreenManager.MAIN_VIEW_ID);
    }

    @Override
    public void notifyCompaniesChanged() {
        updateCompaniesView();
    }

    private void updateCompaniesView() {
        List<Company> companies = CompaniesManager.getInstance().getCompanies();
        companiesList.setItems(FXCollections.observableList(companies));
    }

    //TODO replace with proper implementation
    private static final class CompaniesManager {
        private final List<Company> companies;
        private final List<CompaniesListener> listeners;

        private static CompaniesManager INSTANCE;

        static synchronized CompaniesManager getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new CompaniesManager();
            }
            return INSTANCE;
        }

        private CompaniesManager() {
            companies = new ArrayList<>();
            listeners = new CopyOnWriteArrayList<>();

            addCompany(new Company("Firma", "Adres"));
        }

        public void addCompany(Company company) {
            companies.add(company);
            notifyListeners();
        }

        public void addCompaniesListener(CompaniesListener listener) {
            listeners.add(listener);
        }

        private void notifyListeners() {
            listeners.forEach(CompaniesListener::notifyCompaniesChanged);
        }

        List<Company> getCompanies() {
            return companies;
        }

    }
}
