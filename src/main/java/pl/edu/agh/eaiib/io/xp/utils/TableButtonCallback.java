package pl.edu.agh.eaiib.io.xp.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

/**
 * Created by frben on 01.06.2017.
 */
public class TableButtonCallback<T>  implements Callback<TableColumn<T, String>, TableCell<T, String>> {

    private TableButtonListener listener;
    private String buttonText;

    public interface TableButtonListener<T>{
        void onButtonClick(T item);
    }

    @Override
    public TableCell<T, String> call(TableColumn<T, String> param) {
        return new TableCell<T, String>() {

            final Button cellButton = new Button(buttonText);

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    T tableItem = getTableView().getItems().get(getIndex());
                    cellButton.setOnAction((ActionEvent event) -> {
                        listener.onButtonClick(tableItem);
                    });
                    setGraphic(cellButton);
                    setText(null);
                    if (buttonText.equals("Edytuj")) {
                        if (tableItem instanceof Company) {
                            cellButton.setId("companyEdit-" + ((Company) tableItem).getName());
                        } else if (tableItem instanceof WorkRecord) {
                            cellButton.setId("workRecordEdit-" + (((WorkRecord) tableItem).getCompanyName()));
                        }
                    }else if (buttonText.equals("Usuń")){
                        if (tableItem instanceof Company) {
                            cellButton.setId("companyDelete-" + ((Company) tableItem).getName());
                        } else if (tableItem instanceof WorkRecord) {
                            cellButton.setId("workRecordDelete-" + (((WorkRecord) tableItem).getCompanyName()));
                        }
                    }

                }
            }
        };
    }

    public void setListener(TableButtonListener listener) {
        this.listener = listener;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

}
