package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by HP on 2017-06-15.
 */
public interface WorkRecordSetRemote extends DataRecordSetRemote, Serializable{

    WorkRecordRemote add(CompanyRemote company, int hours, LocalDate date);
}
