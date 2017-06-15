import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2017-06-15.
 */

public class DatabaseTests {

    private static ArrayList<DataRecordRemote> workRecords;
    private static ArrayList<DataRecordRemote> companies;

    @BeforeClass
    public static void beforeStateSave() {
        workRecords = Database.getInstance().getDataRecordSet(Database.WORKRECORD_FILE_NAME).getAll();
        companies = Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME).getAll();
    }

    @AfterClass
    public static void afterStateSave() {
        Database.getInstance().setDataRecordSet(Database.WORKRECORD_FILE_NAME, new DataRecordSet(workRecords));
        Database.getInstance().setDataRecordSet(Database.WORKRECORD_FILE_NAME, new DataRecordSet(companies));
    }

    @Test
    public void checkCompanySaving(){
        CompanySetRemote companySet = (CompanySetRemote) Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME);
        CompanyRemote newCompany1 = companySet.add("CompanyName11" , "SomeAdress11");
        CompanyRemote newCompany2 = companySet.add("CompanyName21" , "SomeAdress21");
        companySet.save();

        List<DataRecordRemote> savedCompanies = Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME).getAll();
        Assert.assertTrue(savedCompanies.contains(newCompany1));
        Assert.assertTrue(savedCompanies.contains(newCompany2));
    }

    @Test(expected = RuntimeException.class)
    public void checkSameCompanyNameSaving(){

        CompanySetRemote companySet = (CompanySetRemote) Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME);
        companySet.add("CompanyName12" , "SomeAdress12");
        companySet.add("CompanyName12" , "SomeAdress22");
        companySet.save();
    }

    @Test(expected = RuntimeException.class)
    public void checkSameCompanyAddressSaving(){
        CompanySetRemote companySet = (CompanySetRemote) Database.getInstance().getDataRecordSet(Database.COMPANY_FILE_NAME);
        companySet.add("CompanyName13" , "SomeAdress13");
        companySet.add("CompanyName23" , "SomeAdress13");
        companySet.save();
    }

}