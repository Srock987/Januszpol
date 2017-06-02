package pl.edu.agh.eaiib.io.xp.view.filters;

import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

abstract class AbstractWorkRecordsViewFilter implements WorkRecordsViewFilter {

    @Override
    public final boolean accepts(WorkRecord record) {
        return !isActive() || doAccepts(record);
    }

    protected abstract boolean doAccepts(WorkRecord record);
}
