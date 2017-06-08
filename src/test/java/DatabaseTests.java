import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.data.Database;
import pl.edu.agh.eaiib.io.xp.model.Company;
import java.util.List;

public class DatabaseTests {

    @Test
    public void checkCompanySaving(){
        Company newCompany = new Company("CompanyName" , "SomeAdress");
        Database.addCompany(newCompany);
        List<Company> savedCompanies = Database.getCompanyList();
        Assert.assertTrue(savedCompanies.contains(newCompany));
    }

}