package pl.edu.agh.eaiib.io.xp.view.filters;

import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

public interface WorkRecordsViewFilter {
    boolean isActive();

    boolean accepts(WorkRecord record);
}
