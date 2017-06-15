package pl.edu.agh.eaiib.io.xp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Karol on 2017-06-03.
 */
public class TimeAccumulator {
    private final ArrayList<DataRecordRemote> workRecords;

    public TimeAccumulator(ArrayList<DataRecordRemote> workRecords) {
        this.workRecords = workRecords;
    }

    public Integer calculateSumOfTime(CompanyRemote company) {
        return workRecords.stream().filter(
                workRecord -> ((WorkRecordRemote) workRecord).getCompany().equals(company))
                .mapToInt(o -> ((WorkRecordRemote) o).getHours()).sum();
    }

    public Integer calculateSumOfTime(CompanyRemote company, LocalDate timePeriodStart, LocalDate timePeriodEnd) {
        return workRecords.stream().filter(
                workRecord -> ((WorkRecordRemote) workRecord).getCompany().equals(company)
                        && ((WorkRecordRemote) workRecord).getDate().isBefore(timePeriodEnd)
                        && ((WorkRecordRemote) workRecord).getDate().isAfter(timePeriodStart))
                .mapToInt(o -> ((WorkRecordRemote) o).getHours()).sum();
    }
}
