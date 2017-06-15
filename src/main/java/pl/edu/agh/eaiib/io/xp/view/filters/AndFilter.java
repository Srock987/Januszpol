package pl.edu.agh.eaiib.io.xp.view.filters;

import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;

import java.util.List;

public final class AndFilter extends AbstractWorkRecordsViewFilter {
    private final List<WorkRecordsViewFilter> filters;

    public AndFilter(List<WorkRecordsViewFilter> filters) {
        this.filters = filters;
    }

    @Override
    protected boolean doAccepts(WorkRecordRemote record) {
        return filters.stream().allMatch(filter -> filter.accepts(record));
    }

    @Override
    public boolean isActive() {
        return filters.stream().allMatch(WorkRecordsViewFilter::isActive);
    }
}
