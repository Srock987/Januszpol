import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by HP on 2017-06-08.
 */
public class WorkRecordTest {

    @Test
    public void workRecordContainsValidData() {
        String name = "Nazwa firmy";
        String address = "Adress firmy";
        Company company = new Company(name, address);
        int hours = 3;
        LocalDate date = LocalDate.of(2017, 6, 6);
        WorkRecord workRecord = new WorkRecord(company, hours, date);
        Assert.assertEquals(name, workRecord.getCompanyName());
        Assert.assertEquals(address, workRecord.getCompany().getAddress());
        Assert.assertEquals(hours, workRecord.getHours());
        Assert.assertEquals(date, workRecord.getDate());
    }
}
