package pl.edu.agh.eaiib.io.xp.controllers.workRecord;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.TableButtonCallback;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AllWorkRecordsController
    extends AbstractController {

    @FXML
    private TableView<WorkRecord> workRecordsTableView;

    @FXML
    private TableColumn<WorkRecord, String> companyNameColumn;

    @FXML
    private TableColumn<WorkRecord, Integer> hoursColumn;

    @FXML
    private TableColumn<WorkRecord, LocalDate> dateColumn;

    @FXML
    private TableColumn<WorkRecord, String> editColumn;

    @FXML
    private TableColumn<WorkRecord, String> deleteColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyNameColumn.setResizable(false);
        hoursColumn.setResizable(false);
        dateColumn.setResizable(false);
        deleteColumn.setResizable(false);

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        TableButtonCallback<WorkRecord> editButtonCallback = new TableButtonCallback<>();
        editButtonCallback.setButtonText("Edytuj");
        editButtonCallback.setListener(item -> {
            ScreenManager.getInstance().setScreen(ScreenManager.EDIT_WORK_RECORD_VIEW_ID);
            ((EditWorkRecordController) ScreenManager.currentController).setEditingWorkRecord((WorkRecord) item);
        });
        editColumn.setCellFactory(editButtonCallback);

        TableButtonCallback<WorkRecord> deleteButtonCallback = new TableButtonCallback<>();
        deleteButtonCallback.setButtonText("Usuń");
        deleteButtonCallback.setListener(item -> ScreenManager
            .getInstance()
            .showConfirmationDialog("Czy na pewno usunąć rekord?", () -> {
                Database
                    .getWorkRecords()
                    .remove(item);
                workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));
            }));
        deleteColumn.setCellFactory(deleteButtonCallback);

        workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));
    }
}
