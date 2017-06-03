package pl.edu.agh.eaiib.io.xp.model;

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

    public Integer calculateSumOfTime(Company company) {
        return workRecords.stream()
            .filter(workRecord -> workRecord.getCompany().equals(company))
            .mapToInt(o -> o.getHours()).sum();
    }
}
