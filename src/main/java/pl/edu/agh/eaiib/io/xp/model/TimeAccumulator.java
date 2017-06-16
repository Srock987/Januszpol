package pl.edu.agh.eaiib.io.xp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Karol on 2017-06-03.
 */
public class TimeAccumulator
{
    private final ArrayList<WorkRecord> workRecords;

    public TimeAccumulator(ArrayList<WorkRecord> workRecords)
    {
        this.workRecords = workRecords;
    }

    public Integer calculateSumOfTime(Company company)
    {
        return workRecords.stream().filter(workRecord -> workRecord.getCompany().equals(company)).mapToInt(
            o -> o.getHours()).sum();
    }

    public Integer calculateSumOfTime(Company company, LocalDate timePeriodStart, LocalDate timePeriodEnd)
    {
        return workRecords.stream().filter(
            workRecord -> workRecord.getCompany().equals(company) &&
                    ( workRecord.getDate().isBefore(timePeriodEnd) || workRecord.getDate().isEqual(timePeriodEnd) )
                && ( workRecord.getDate().isAfter(timePeriodStart) || workRecord.getDate().isEqual(timePeriodStart) )
        ).mapToInt(o -> o.getHours()).sum();
    }
}
