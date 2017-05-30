package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frben on 25.05.2017.
 */
public class Database {

    private static ArrayList<Company> companyList;
    private static ArrayList<WorkRecord> workRecords;

    private static String COMPANY_FILE_NAME = "companies.dat";
    private static String WORKRECORD_FILE_NAME = "workrecords.dat";

    static {
        DataLoader<Company> companyDataLoader = new DataLoader<>();
        DataLoader<WorkRecord> workRecordDataLoader = new DataLoader<>();
        companyList = companyDataLoader.loadData(COMPANY_FILE_NAME);
        workRecords = workRecordDataLoader.loadData(WORKRECORD_FILE_NAME);
    }

    public static void addCompany(Company company){
        companyList.add(company);
    }

    public static void addWorkRecord(WorkRecord workRecord){
        workRecords.add(workRecord);
    }

    public static ArrayList<Company> getCompanyList() {
        return companyList;
    }

    public static ArrayList<WorkRecord> getWorkRecords() {
        return workRecords;
    }

    public static void saveData() throws Throwable {
        DataSaver dataSaver = new DataSaver();
        dataSaver.saveData(WORKRECORD_FILE_NAME, workRecords);
        dataSaver.saveData(COMPANY_FILE_NAME, companyList);
    }
}
