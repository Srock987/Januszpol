import org.junit.Test;

import org.junit.Assert;
import pl.edu.agh.eaiib.io.xp.model.Company;


public class CompanyTests {
    @Test
    public void Company_onValidData_containsData() {
        String name = "Nazwa firmy";
        String address = "Adress firmy";
        Company company = new Company(name, address);
        Assert.assertEquals(name, company.getName());
        Assert.assertEquals(address, company.getAddress());
    }
}
