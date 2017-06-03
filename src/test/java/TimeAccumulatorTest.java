import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.TimeAccumulator;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import java.time.LocalDate;
import java.util.ArrayList;

public class TimeAccumulatorTest
{
    @Test
    public void sumTime_ValidNumbers_SumJustTimeFromGivenCompany() {
        Company testedCompany = new Company("firma", "adres");
        WorkRecord workRecord1 = new WorkRecord(testedCompany, 6, LocalDate.now());
        WorkRecord workRecord2 = new WorkRecord(new Company("firma1", "adres"), 7, LocalDate.now());
        WorkRecord workRecord3 = new WorkRecord(testedCompany, 13, LocalDate.now());
        Integer expectedSumOfTime = 19;
        ArrayList<WorkRecord> workRecords = new ArrayList<>();
        workRecords.add(workRecord1);
        workRecords.add(workRecord2);
        workRecords.add(workRecord3);
        TimeAccumulator timeAccumulator = new TimeAccumulator(workRecords);
        Assert.assertEquals(timeAccumulator.calculateSumOfTime(testedCompany), expectedSumOfTime);
    }
};


