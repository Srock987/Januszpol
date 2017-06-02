package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void addCompany(Company company) {
        companyList.add(company);
    }

    public static void addWorkRecord(WorkRecord workRecord) {
        workRecords.add(0, workRecord);
    }

    public static ArrayList<Company> getCompanyList() {
        return companyList;
    }

    public static Company getCompanyByName(String companyName) {
        List<Company> matchingCompanies = companyList
            .stream()
            .filter(company -> company
                .getName()
                .equals(companyName))
            .collect(Collectors.toList());
        if(matchingCompanies.size() < 1){
            throw new RuntimeException("Podana firma nie istnieje już w bazie danych.");
        }
        return matchingCompanies.get(0);
    }

    public static ArrayList<WorkRecord> getWorkRecords() {
        return workRecords;
    }

    public static void saveData()
        throws Throwable {
        DataSaver dataSaver = new DataSaver();
        dataSaver.saveData(WORKRECORD_FILE_NAME, workRecords);
        dataSaver.saveData(COMPANY_FILE_NAME, companyList);
    }
}
