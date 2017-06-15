package pl.edu.agh.eaiib.io.xp.model;

import pl.edu.agh.eaiib.io.xp.data.Database;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by HP on 2017-06-15.
 */
public class WorkRecordSet extends DataRecordSet implements WorkRecordSetRemote {

    public WorkRecordSet(ArrayList<DataRecordRemote> dataRecords) {
        super(dataRecords);
    }

    @Override
    public DataRecordRemote add() {

        WorkRecord workRecord = new WorkRecord();
        getAll().add(workRecord);
        return  workRecord;
    }

    @Override
    public WorkRecordRemote add(CompanyRemote company, int hours, LocalDate date) {

        WorkRecord workRecord = new WorkRecord(company, hours, date);
        getAll().add(workRecord);
        return  workRecord;
    }

    @Override
    public void save() {

        super.save();
        Database.getInstance().setDataRecordSet(Database.WORKRECORD_FILE_NAME, this);
    }
}
