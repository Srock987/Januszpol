package pl.edu.agh.eaiib.io.xp.utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.eaiib.io.xp.StartupClass;
import pl.edu.agh.eaiib.io.xp.controllers.AbstractController;
import pl.edu.agh.eaiib.io.xp.controllers.workRecord.AllWorkRecordsController;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.CompanyRemote;
import pl.edu.agh.eaiib.io.xp.model.DataRecordRemote;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;
import pl.edu.agh.eaiib.io.xp.view.filters.FiltersFactory;
import pl.edu.agh.eaiib.io.xp.view.filters.WorkRecordsViewFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by damian on 05/06/2017.
 */
public class CsvExporter {

    private static final Logger logger = LoggerFactory.getLogger(CsvExporter.class);
    private Stage primaryStage;
    private AbstractController controller;

    public CsvExporter(Stage primaryStage, AbstractController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    public void exportRecords() {
        File chosenFile = showSaveSystemDialog(primaryStage);
        if (chosenFile != null) {
            String extension = "";
            int i = chosenFile.getName().lastIndexOf('.');
            extension = chosenFile.getName().substring(i + 1);
            saveToFile(getFormattedRecords(extension), chosenFile);
        }
    }

    private File showSaveSystemDialog(Window ownerWindow) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilterTxt);
        fileChooser.getExtensionFilters().add(extFilterCsv);
        return fileChooser.showSaveDialog(ownerWindow);
    }

    private String getFormattedRecords(String extension) {
        final WorkRecordsViewFilter activeFilter;
        if (controller instanceof AllWorkRecordsController) {
            activeFilter = ((AllWorkRecordsController) controller).getActiveFilter();
        } else {
            activeFilter = FiltersFactory.getEmptyFilter();
        }
        List<DataRecordRemote> workRecords = Database.getInstance().getDataRecordSet(Database.WORKRECORD_FILE_NAME).getAll().stream().filter(
                record -> activeFilter.accepts((WorkRecordRemote) record)).collect(Collectors.toList());

        switch (extension) {
            case "txt":
                return buildTxtString(workRecords);
            case "csv":
                return buildCsvString(workRecords);
            default:
                logger.error("Format pliku nieobsługiwany!");
        }
        return null;
    }

    private String buildCsvString(List<DataRecordRemote> records) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa firmy,Data,Godzin\n");
        for (DataRecordRemote record : records) {
            sb.append(((WorkRecordRemote) record).getCompanyName());
            sb.append(',');
            sb.append(((WorkRecordRemote) record).getDate().toString());
            sb.append(',');
            sb.append(((WorkRecordRemote) record).getHours());
            sb.append('\n');
        }
        return sb.toString();
    }

    private String buildTxtString(List<DataRecordRemote> records) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-20s | %-15s | %-10s|\n", "NAZWA FIRMY", "DATA", "GODZIN"));
        for (DataRecordRemote record : records) {
            sb.append(String.format("|%-20s | %-15s | %-10s|", ((WorkRecordRemote) record).getCompanyName(),
                    ((WorkRecordRemote) record).getDate(),
                    ((WorkRecordRemote) record).getHours()));
            sb.append('\n');
        }
        return sb.toString();
    }

    private void saveToFile(String content, File file) {
        if (content != null && file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(content);
                fileWriter.close();
            } catch (IOException ex) {
                logger.error(ex.toString());
            }
        }
    }

}
