import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.model.*;

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
        ArrayList<DataRecordRemote> workRecords = new ArrayList<>();
        workRecords.add(workRecord1);
        workRecords.add(workRecord2);
        workRecords.add(workRecord3);
        TimeAccumulator timeAccumulator = new TimeAccumulator(workRecords);
        Assert.assertEquals(timeAccumulator.calculateSumOfTime(testedCompany), expectedSumOfTime);
    }

    @Test
    public void sumTime_ValidNumbers_SumJustTimeFromGivenCompanyAndForGivenTimePeriod() {
        Company testedCompany = new Company("firma", "adres");
        WorkRecord workRecord1 = new WorkRecord(testedCompany, 6, LocalDate.of(2017,01,03));
        WorkRecord workRecord2 = new WorkRecord(testedCompany, 7, LocalDate.of(2017,01,04));
        WorkRecord workRecord3 = new WorkRecord(testedCompany, 13, LocalDate.of(2017,01,06));
        WorkRecord workRecord4 = new WorkRecord(testedCompany, 15, LocalDate.of(2018,01,03));
        Integer expectedSumOfTime = 13;
        ArrayList<DataRecordRemote> workRecords = new ArrayList<>();
        workRecords.add(workRecord1);
        workRecords.add(workRecord2);
        workRecords.add(workRecord3);
        workRecords.add(workRecord4);
        TimeAccumulator timeAccumulator = new TimeAccumulator(workRecords);
        Assert.assertEquals(expectedSumOfTime, timeAccumulator.calculateSumOfTime(testedCompany, LocalDate.of(2017,01,02), LocalDate.of(2017,01,05)));
    }
};


