package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.view.filters.AndFilter;
import pl.edu.agh.eaiib.io.xp.view.filters.FiltersFactory;
import pl.edu.agh.eaiib.io.xp.view.filters.WorkRecordsViewFilter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WorkRecordsViewController implements Initializable {

    @FXML
    TableView<WorkRecord> workRecordsTableView;

    @FXML
    TableColumn<WorkRecord, String> companyNameColumn;

    @FXML
    TableColumn<WorkRecord, Integer> hoursColumn;

    @FXML
    TableColumn<WorkRecord, LocalDate> dateColumn;

    @FXML
    TextField companyNameField;

    @FXML
    TextField nrOfHoursField;

    @FXML
    DatePicker beginDateControl;

    @FXML
    DatePicker endDateControl;

    @FXML
    Button clearButton;

    @FXML
    Button applyButton;

    private List<WorkRecordsViewFilter> filters = FiltersFactory.getDefaultFilters();
    private WorkRecordsViewFilter activeFilter = FiltersFactory.getEmptyFilter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyNameColumn.setResizable(false);
        hoursColumn.setResizable(false);
        dateColumn.setResizable(false);

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));

        filters = createFilters();

        clearButton.setOnAction(e -> {
            clearFiltersControls();
            refreshFilters();
        });

        applyButton.setOnAction(e -> {
            refreshFilters();
        });
    }

    private List<WorkRecordsViewFilter> createFilters() {
        List<WorkRecordsViewFilter> filters = new ArrayList<>();
        filters.add(FiltersFactory.createCompanyNameFilter(companyNameField));
        filters.add(FiltersFactory.createBeginDateFilter(beginDateControl));
        filters.add(FiltersFactory.createEndDateFilter(endDateControl));
        filters.add(FiltersFactory.createNumberOfHoursFilter(nrOfHoursField));

        return filters;
    }

    private void clearFiltersControls() {
        String empty = "";
        companyNameField.setText(empty);
        nrOfHoursField.setText(empty);
        beginDateControl.getEditor().setText(empty);
        endDateControl.getEditor().setText(empty);
    }

    private void refreshFilters() {
        activeFilter = getActiveFilter();
        refreshListView();
    }

    private WorkRecordsViewFilter getActiveFilter() {
        List<WorkRecordsViewFilter> activeFilters = filters.stream().filter(WorkRecordsViewFilter::isActive).collect(Collectors.toList());
        return new AndFilter(activeFilters);
    }

    private void refreshListView() {
        List<WorkRecord> workRecords = Database.getWorkRecords().stream().filter(record -> activeFilter.accepts(record)).collect(Collectors.toList());
        workRecordsTableView.setItems(FXCollections.observableList(workRecords));
    }
}
