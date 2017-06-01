package pl.edu.agh.eaiib.io.xp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.view.ScreenManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AllWorkRecordsController
    implements Initializable {

    @FXML
    TableView<WorkRecord> workRecordsTableView;

    @FXML
    TableColumn<WorkRecord, String> companyNameColumn;

    @FXML
    TableColumn<WorkRecord, Integer> hoursColumn;

    @FXML
    TableColumn<WorkRecord, LocalDate> dateColumn;

    @FXML
    TableColumn<WorkRecord, String> deleteColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyNameColumn.setResizable(false);
        hoursColumn.setResizable(false);
        dateColumn.setResizable(false);
        deleteColumn.setResizable(false);

        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        Callback<TableColumn<WorkRecord, String>, TableCell<WorkRecord, String>> cellFactory = new
            Callback<TableColumn<WorkRecord, String>, TableCell<WorkRecord, String>>() {
                @Override
                public TableCell call(final TableColumn<WorkRecord, String> param) {
                    final TableCell<WorkRecord, String> cell = new TableCell<WorkRecord, String>() {

                        final Button deleteButton = new Button("Usuń");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                deleteButton.setOnAction((ActionEvent event) -> {
                                    WorkRecord workRecord = getTableView()
                                        .getItems()
                                        .get(getIndex());
                                    ScreenManager
                                        .getInstance()
                                        .showConfirmationDialog("Czy na pewno usunąć rekord?", () -> {
                                            Database
                                                .getWorkRecords()
                                                .remove(workRecord);
                                            workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));
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
        deleteColumn.setCellFactory(cellFactory);

        workRecordsTableView.setItems(FXCollections.observableList(Database.getWorkRecords()));
    }
}
