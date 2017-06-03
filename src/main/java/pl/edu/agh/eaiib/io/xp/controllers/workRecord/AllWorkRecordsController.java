package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.TimeAccumulator;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.TableButtonCallback;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;
import pl.edu.agh.eaiib.io.xp.view.filters.AndFilter;
import pl.edu.agh.eaiib.io.xp.view.filters.FiltersFactory;
import pl.edu.agh.eaiib.io.xp.view.filters.WorkRecordsViewFilter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AllWorkRecordsController
    extends AbstractController
{
    private static final String SUM_OF_TIME_FIELD_RESOURCE_KEY = "textfields.sumOfTime";

    private static final String SUM_OF_TIME_FIELD_ACTIVE_RESOURCE_KEY = "textfields.sumOfTimeActive";

    private static final String FULL_SUM_OF_TIME_FIELD_ACTIVE_RESOURCE_KEY = "textfields.fullSumOfTimeActive";

    private static final String FILL_BOTH_RESOURCE_KEY = "textfields.fillBoth";

    @FXML private TableView<WorkRecord> workRecordsTableView;

    @FXML private TableColumn<WorkRecord, String> companyNameColumn;

    @FXML private TableColumn<WorkRecord, Integer> hoursColumn;

    @FXML private TableColumn<WorkRecord, LocalDate> dateColumn;

    @FXML private TableColumn<WorkRecord, String> editColumn;

    @FXML private TableColumn<WorkRecord, String> deleteColumn;

    @FXML private TextField companyNameField;

    @FXML private TextField nrOfHoursField;

    @FXML private DatePicker beginDateControl;

    @FXML private DatePicker endDateControl;

    @FXML private Button clearButton;

    @FXML private Button applyButton;

    @FXML private TextField sumOfTimeField;

    @FXML private DatePicker sumOfTimeBeg;

    @FXML private DatePicker sumOfTimeEnd;

    private List<WorkRecordsViewFilter> filters = FiltersFactory.getDefaultFilters();

    private WorkRecordsViewFilter activeFilter = FiltersFactory.getEmptyFilter();

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        companyNameColumn.setResizable(false);
        hoursColumn.setResizable(false);
        dateColumn.setResizable(false);
        deleteColumn.setResizable(false);

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        sumOfTimeField.setText(resources.getString(SUM_OF_TIME_FIELD_RESOURCE_KEY));

        TableButtonCallback<WorkRecord> editButtonCallback = new TableButtonCallback<>();
        editButtonCallback.setButtonText("Edytuj");
        editButtonCallback.setListener(item -> {
            ScreenManager.getInstance().setScreen(ScreenManager.EDIT_WORK_RECORD_VIEW_ID);
            ((EditWorkRecordController)ScreenManager.currentController).setEditingWorkRecord((WorkRecord)item);
        });
        editColumn.setCellFactory(editButtonCallback);

        TableButtonCallback<WorkRecord> deleteButtonCallback = new TableButtonCallback<>();
        deleteButtonCallback.setButtonText("Usuń");
        deleteButtonCallback.setListener(
            item -> ScreenManager.getInstance().showConfirmationDialog("Czy na pewno usunąć rekord?", () -> {
                Database.getWorkRecords().remove(item);
                workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));
            }));
        deleteColumn.setCellFactory(deleteButtonCallback);

        workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));

        workRecordsTableView.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                ouputAccumulatedtime(resources, newSelection);
            });

        sumOfTimeBeg.setOnAction(e -> {
            ouputAccumulatedtime(resources, workRecordsTableView.getSelectionModel().getSelectedItem());
        });
        sumOfTimeEnd.setOnAction(e -> {
            ouputAccumulatedtime(resources, workRecordsTableView.getSelectionModel().getSelectedItem());
        });

        filters = createFilters();

        clearButton.setOnAction(e -> {
            clearFiltersControls();
            refreshFilters();
        });

        applyButton.setOnAction(e -> {
            refreshFilters();
        });
    }

    private void ouputAccumulatedtime(ResourceBundle resources, WorkRecord newSelection)
    {
        if (newSelection != null) {
            TimeAccumulator timeAccumulator = new TimeAccumulator(Database.getWorkRecords());
            final boolean areAllDatesAvailable = !isEmpty(sumOfTimeBeg) && !isEmpty(sumOfTimeEnd);
            final boolean areAnyDatesAvailable = (!isEmpty(sumOfTimeBeg) || !isEmpty(sumOfTimeEnd)) && !areAllDatesAvailable;
            if (areAnyDatesAvailable) {
                sumOfTimeField.setText(resources.getString(FILL_BOTH_RESOURCE_KEY));
                return;
            }
            if (areAllDatesAvailable) {
                sumOfTimeField.setText(
                    resources.getString(SUM_OF_TIME_FIELD_ACTIVE_RESOURCE_KEY) + timeAccumulator.calculateSumOfTime(
                        newSelection.getCompany(), getDate(sumOfTimeBeg), getDate(sumOfTimeEnd)).toString());
            } else {
                sumOfTimeField.setText(resources.getString(FULL_SUM_OF_TIME_FIELD_ACTIVE_RESOURCE_KEY)
                    + timeAccumulator.calculateSumOfTime(newSelection.getCompany()).toString());
            }
        } else
            sumOfTimeField.setText(resources.getString(SUM_OF_TIME_FIELD_RESOURCE_KEY));
    }

    private List<WorkRecordsViewFilter> createFilters()
    {
        List<WorkRecordsViewFilter> filters = new ArrayList<>();
        filters.add(FiltersFactory.createCompanyNameFilter(companyNameField));
        filters.add(FiltersFactory.createBeginDateFilter(beginDateControl));
        filters.add(FiltersFactory.createEndDateFilter(endDateControl));
        filters.add(FiltersFactory.createNumberOfHoursFilter(nrOfHoursField));

        return filters;
    }

    private void clearFiltersControls()
    {
        String empty = "";
        companyNameField.setText(empty);
        nrOfHoursField.setText(empty);
        beginDateControl.getEditor().setText(empty);
        endDateControl.getEditor().setText(empty);
    }

    private void refreshFilters()
    {
        activeFilter = getActiveFilter();
        refreshListView();
    }

    private WorkRecordsViewFilter getActiveFilter()
    {
        List<WorkRecordsViewFilter> activeFilters = filters.stream().filter(WorkRecordsViewFilter::isActive).collect(
            Collectors.toList());
        return new AndFilter(activeFilters);
    }

    private void refreshListView()
    {
        List<WorkRecord> workRecords = Database.getWorkRecords().stream().filter(
            record -> activeFilter.accepts(record)).collect(Collectors.toList());
        workRecordsTableView.setItems(FXCollections.observableList(workRecords));
    }

    private Integer calculateSumOfTime(Company company, DatePicker beg, DatePicker end)
    {
        TimeAccumulator timeAccumulator = new TimeAccumulator(Database.getWorkRecords());
        if (isEmpty(beg) || isEmpty(end))
            return timeAccumulator.calculateSumOfTime(company);
        else
            return timeAccumulator.calculateSumOfTime(company, getDate(beg), getDate(end));
    }

    private static boolean isEmpty(DatePicker datePicker)
    {
        String text = datePicker.getEditor().getText();
        return text == null || text.isEmpty();
    }

    private static LocalDate getDate(DatePicker datePicker)
    {
        return datePicker.getConverter().fromString(datePicker.getEditor().getText());
    }
}
