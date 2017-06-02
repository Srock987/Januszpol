package pl.edu.agh.eaiib.io.xp.view.filters;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;

import java.util.Collections;
import java.util.List;

public class FiltersFactory {
    private FiltersFactory() {
    }

    public static List<WorkRecordsViewFilter> getDefaultFilters() {
        return Collections.emptyList();
    }

    public static WorkRecordsViewFilter createBeginDateFilter(DatePicker beginDatePicker) {
        return new Filters.BeginDateFilter(beginDatePicker);
    }

    public static WorkRecordsViewFilter createEndDateFilter(DatePicker endDatePicker) {
        return new Filters.EndDateFilter(endDatePicker);
    }

    public static WorkRecordsViewFilter createCompanyNameFilter(TextField companyNameField) {
        return new Filters.CompanyNameFilter(companyNameField);
    }

    public static WorkRecordsViewFilter createNumberOfHoursFilter(TextField numberOfHoursField) {
        return new Filters.NumberOfHoursFilter(numberOfHoursField);
    }

    public static WorkRecordsViewFilter getEmptyFilter() {
        return new AbstractWorkRecordsViewFilter() {
            @Override
            protected boolean doAccepts(WorkRecord record) {
                return true;
            }

            @Override
            public boolean isActive() {
                return true;
            }
        };
    }
}
