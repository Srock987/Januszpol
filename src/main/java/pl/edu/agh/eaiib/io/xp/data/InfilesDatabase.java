package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.*;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.util.ArrayList;

/**
 * Created by HP on 2017-06-15.
 */
public class InfilesDatabase extends Database implements DatabaseRemote {

    private ArrayList<DataRecordRemote> companyListRec;
    private ArrayList<DataRecordRemote> workRecordsRec;

    public static final String COMPANY_FILE_NAME = "companies.dat";
    public static final String WORKRECORD_FILE_NAME = "workrecords.dat";

    public InfilesDatabase() {
        DataLoader<DataRecordRemote> dataLoader = new DataLoader<>();
        companyListRec = dataLoader.loadData(COMPANY_FILE_NAME);
        workRecordsRec = dataLoader.loadData(WORKRECORD_FILE_NAME);
    }

    public void saveData() throws Throwable {

        DataSaver dataSaver = new DataSaver();
        dataSaver.saveData(WORKRECORD_FILE_NAME, workRecordsRec);
        dataSaver.saveData(COMPANY_FILE_NAME, companyListRec);
    }

    @Override
    public DataRecordSetRemote getDataRecordSet(String dataName) {

        // TODO: 2017-06-15 symulacja funkcji select, "select * from 'dataName' */
        switch(dataName){
            case COMPANY:
                return new CompanySet(companyListRec);
            case WORKRECORD:
                return new WorkRecordSet(workRecordsRec);
            default:
                return new DataRecordSet(new ArrayList<>());
        }
    }

    @Override
    public void setDataRecordSet(String dataName, DataRecordSetRemote dataRecordSet) {

        // TODO: 2017-06-15 symulacja funkcji insert, insert 'dataRecordSet' into 'dataName' */
        switch(dataName){
            case COMPANY:
                companyListRec = dataRecordSet.getAll();
                break;
            case WORKRECORD:
                workRecordsRec = dataRecordSet.getAll();
                break;
        }
    }
}
