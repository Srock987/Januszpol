import org.junit.Rule;
import org.junit.Test;

import org.junit.Assert;
import org.junit.rules.ExpectedException;
import pl.edu.agh.eaiib.io.xp.model.Company;


public class CompanyTests {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void companyContainsValidData() {
        String name = "Nazwa firmy";
        String address = "Adress firmy";
        Company company = new Company(name, address);
        Assert.assertEquals(name, company.getName());
        Assert.assertEquals(address, company.getAddress());
    }

    @Test
    public void exceptionOnEmptyCompanyName(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Company name is empty.");
        String name = "";
        String address = "Some address";
        new Company(name, address);
    }

    @Test
    public void exceptionOnEmptyAddress(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Address is empty.");
        String name = "Some company";
        String address = "";
        new Company(name, address);
    }

    @Test
    public void exceptionOnNullCompanyName(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Company name is empty.");
        String name = null;
        String address = "Some address";
        new Company(name, address);
    }

    @Test
    public void exceptionOnNullAddress(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Address is empty.");
        String name = "Some company";
        String address = null;
        new Company(name, address);
    }

    @Test
    public void exceptionOnTooShortCompanyName(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Company name is min 3 and max 70 signs.");
        String name = "12";
        String address = "Some address";
        new Company(name, address);
    }

    @Test
    public void exceptionOnTooShortAddress(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Address is min 3 and max 70 signs.");
        String name = "Some company";
        String address = "12";
        new Company(name, address);
    }

    @Test
    public void exceptionOnTooLongCompanyName(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Company name is min 3 and max 70 signs.");
        String name = "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1";
        String address = "Some address";
        new Company(name, address);
    }

    @Test
    public void exceptionOnTooLongAddress(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Address is min 3 and max 70 signs.");
        String name = "Some company";
        String address = "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1";
        new Company(name, address);
    }
}
