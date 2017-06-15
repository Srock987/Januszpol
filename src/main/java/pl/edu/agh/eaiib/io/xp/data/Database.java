package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.*;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by frben on 25.05.2017.
 */
public class Database implements DatabaseRemote{

    private ArrayList<DataRecordRemote> companyListRec;
    private ArrayList<DataRecordRemote> workRecordsRec;

    public static final String COMPANY_FILE_NAME = "companies.dat";
    public static final String WORKRECORD_FILE_NAME = "workrecords.dat";

    private static Database database = null;

    public static Database getInstance(){

        if(database == null){
            database = new Database();
            database.init();
            return database;
        }else
            return database;
    }

    private void init(){

        DataLoader<DataRecordRemote> dataLoader = new DataLoader<>();
        companyListRec = dataLoader.loadData(COMPANY_FILE_NAME);
        workRecordsRec = dataLoader.loadData(WORKRECORD_FILE_NAME);
    }

    private Database(){

    }

    public void saveData()

        throws Throwable {
        DataSaver dataSaver = new DataSaver();
        dataSaver.saveData(WORKRECORD_FILE_NAME, workRecordsRec);
        dataSaver.saveData(COMPANY_FILE_NAME, companyListRec);
    }

    @Override
    public DataRecordSetRemote getDataRecordSet(String dataName) {

        // TODO: 2017-06-15 symulacja funkcji select, da sie inaczej niz switchem...? */
        switch(dataName){
            case COMPANY_FILE_NAME:
                return new CompanySet(companyListRec);
            case WORKRECORD_FILE_NAME:
                return new WorkRecordSet(workRecordsRec);
            default:
                return new DataRecordSet(new ArrayList<>());
        }
    }

    @Override
    public void setDataRecordSet(String dataName, DataRecordSetRemote dataRecordSet) {

        // TODO: 2017-06-15 symulacja funkcji insert, da sie inaczej niz switchem...? */
        switch(dataName){
            case COMPANY_FILE_NAME:
                companyListRec = dataRecordSet.getAll();
                break;
            case WORKRECORD_FILE_NAME:
                workRecordsRec = dataRecordSet.getAll();
                break;
        }
    }
}
