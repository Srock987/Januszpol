import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTests {

    private static ArrayList<WorkRecord> records;
    private static ArrayList<Company> companies;

    @BeforeClass
    public static void beforeStateSave() {
        records = Database.getWorkRecords();
        companies = Database.getCompanyList();
    }

    @AfterClass
    public static void afterStateSave() {
        Database.setWorkRecordList(records);
        Database.setCompanyList(companies);
    }

    @Test
    public void checkCompanySaving(){
        Company newCompany1 = new Company("CompanyName1" , "SomeAdress1");
        Company newCompany2 = new Company("CompanyName2" , "SomeAdress2");
        Database.addCompany(newCompany1);
        Database.addCompany(newCompany2);
        List<Company> savedCompanies = Database.getCompanyList();
        Assert.assertTrue(savedCompanies.contains(newCompany1));
        Assert.assertTrue(savedCompanies.contains(newCompany2));
    }

    @Test(expected = RuntimeException.class)
    public void checkSameCompanyNameSaving(){
        Company newCompany1 = new Company("ala" , "b");
        Company newCompany2 = new Company("ala" , "a");
        Database.addCompany(newCompany1);
        Database.addCompany(newCompany2);
    }

    @Test(expected = RuntimeException.class)
    public void checkSameCompanyAddressSaving(){
        Company newCompany1 = new Company("ela" , "ana");
        Company newCompany2 = new Company("ula" , "ana");
        Database.addCompany(newCompany1);
        Database.addCompany(newCompany2);
    }

}