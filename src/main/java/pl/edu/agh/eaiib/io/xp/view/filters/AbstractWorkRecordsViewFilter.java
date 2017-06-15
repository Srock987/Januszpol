package pl.edu.agh.eaiib.io.xp.view.filters;

import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;

abstract class AbstractWorkRecordsViewFilter implements WorkRecordsViewFilter {

    @Override
    public final boolean accepts(WorkRecordRemote record) {
        return !isActive() || doAccepts(record);
    }

    protected abstract boolean doAccepts(WorkRecordRemote record);
}
