import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

import java.time.LocalDate;
import java.util.List;

public class DatabaseTests {

    @Test
    public void checkCompanySaving(){
        Company newCompany = new Company("CompanyName" , "SomeAdress");
        Database.addCompany(newCompany);
        List<Company> savedCompanies = Database.getCompanyList();
        Assert.assertTrue(savedCompanies.contains(newCompany));
    }

//    @Test
//    public void checkWorkRecordSaving(){
//        Company newCompany = new Company("CompanyName" , "SomeAdress");
//        WorkRecord newWorkRecord = new WorkRecord(newCompany , 3);
//        Database.addWorkRecord(newWorkRecord);
//        List<WorkRecord> savedWorkRecords = Database.getWorkRecords();
//        Assert.assertTrue(savedWorkRecords.contains(newWorkRecord));
//    }


}