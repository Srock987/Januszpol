package pl.edu.agh.eaiib.io.xp.view.filters;

import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;

public interface WorkRecordsViewFilter {
    boolean isActive();

    boolean accepts(WorkRecordRemote record);
}
