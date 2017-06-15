package filters;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.testfx.framework.junit.ApplicationTest;
import pl.edu.agh.eaiib.io.xp.model.Company;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;
import pl.edu.agh.eaiib.io.xp.view.filters.AndFilter;
import pl.edu.agh.eaiib.io.xp.view.filters.FiltersFactory;
import pl.edu.agh.eaiib.io.xp.view.filters.WorkRecordsViewFilter;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class FiltersTests {
    public static class AndFilterTests {

        @Test
        public void givenFiltersWhenAllFiltersAcceptRecordThenAndFilterAcceptsRecord() {
            WorkRecordsViewFilter filter1 = new WorkRecordsViewFilter() {
                @Override
                public boolean isActive() {
                    return true;
                }

                @Override
                public boolean accepts(WorkRecordRemote record) {
                    return true;
                }
            };
            WorkRecordsViewFilter filter2 = new WorkRecordsViewFilter() {
                @Override
                public boolean isActive() {
                    return true;
                }

                @Override
                public boolean accepts(WorkRecordRemote record) {
                    return true;
                }
            };

            WorkRecord workRecord = new WorkRecord(new Company("firma", "adres"), 1, LocalDate.now());

            AndFilter andFilter = new AndFilter(Arrays.asList(filter1, filter2));
            assertTrue(andFilter.accepts(workRecord));
        }

        @Test
        public void givenFiltersWhenOneOfFiltersRejectsRecordThenAndFilterRejectsRecord() {
            WorkRecordsViewFilter filter1 = new WorkRecordsViewFilter() {
                @Override
                public boolean isActive() {
                    return true;
                }

                @Override
                public boolean accepts(WorkRecordRemote record) {
                    return true;
                }
            };
            WorkRecordsViewFilter filter2 = new WorkRecordsViewFilter() {
                @Override
                public boolean isActive() {
                    return true;
                }

                @Override
                public boolean accepts(WorkRecordRemote record) {
                    return false;
                }
            };

            WorkRecord workRecord = new WorkRecord(new Company("firma", "adres"), 1, LocalDate.now());

            AndFilter andFilter = new AndFilter(Arrays.asList(filter1, filter2));
            assertFalse(andFilter.accepts(workRecord));
        }
    }

    public static class CompanyNameFilterTest extends TextFieldBasedFilterTest {
        @Test
        public void whenTextFieldIsEmptyThenFilterIsNotActive() {
            WorkRecordsViewFilter companyNameFilter = FiltersFactory.createCompanyNameFilter(textField);
            assertFalse(companyNameFilter.isActive());
        }

        @Test
        public void givenCompanyNameWhenCompanyNameInsertedIntoFieldThenFilterIsActive() {
            String companyName = "firma";
            textField.setText(companyName);

            WorkRecordsViewFilter companyNameFilter = FiltersFactory.createCompanyNameFilter(textField);
            assertTrue(companyNameFilter.isActive());
        }

        @Test
        public void givenCompanyNameWhenCompanyNameInsertedIntoFieldThenFilterAcceptsCompaniesWithNameStartingWithFieldValue() {
            String companyName = "firma";
            textField.setText(companyName);

            WorkRecordsViewFilter companyNameFilter = FiltersFactory.createCompanyNameFilter(textField);

            WorkRecord workRecord1 = new WorkRecord(new Company("firma", "adres"), 1, LocalDate.now());
            WorkRecord workRecord2 = new WorkRecord(new Company("firma1", "adres"), 1, LocalDate.now());
            WorkRecord workRecord3 = new WorkRecord(new Company("firma2", "adres"), 1, LocalDate.now());

            assertTrue(companyNameFilter.accepts(workRecord1));
            assertTrue(companyNameFilter.accepts(workRecord2));
            assertTrue(companyNameFilter.accepts(workRecord3));
        }

        @Test
        public void givenCompanyNameWhenCompanyNameInsertedIntoFieldThenFilterRejectsCompaniesWithNameNotStartingWithFieldValue() {
            String companyName = "firma";
            textField.setText(companyName);

            WorkRecordsViewFilter companyNameFilter = FiltersFactory.createCompanyNameFilter(textField);

            WorkRecord workRecord1 = new WorkRecord(new Company("nazwaFirmy", "adres"), 1, LocalDate.now());

            assertFalse(companyNameFilter.accepts(workRecord1));
        }
    }

    public static class BeginDateFilterTest extends AbstractDateFilterTest {
        @Test
        public void whenDateIsNotSpecifiedThenFilterIsNotActive() {
            WorkRecordsViewFilter beginDateFilter = FiltersFactory.createBeginDateFilter(datePicker);
            assertFalse(beginDateFilter.isActive());
        }

        @Test
        public void givenDateWhenDateSelectedThenFilterIsActive() {
            LocalDate localDate = LocalDate.now();
            datePicker.getEditor().setText(localDate.toString());

            WorkRecordsViewFilter beginDateFilter = FiltersFactory.createBeginDateFilter(datePicker);
            assertTrue(beginDateFilter.isActive());
        }

        @Test
        public void givenDateWhenDateSelectedThenFilterRejectsWorkRecordsWithEarlierDates() {
            LocalDate localDate = LocalDate.now();
            datePicker.getEditor().setText(datePicker.getConverter().toString(localDate));

            WorkRecordsViewFilter beginDateFilter = FiltersFactory.createBeginDateFilter(datePicker);

            LocalDate date = LocalDate.of(2017, 5, 10);
            WorkRecord workRecord = new WorkRecord(new Company("firma", "adres"), 1, date);
            assertFalse(beginDateFilter.accepts(workRecord));
        }
    }

    public static class EndDateFilterTest extends AbstractDateFilterTest {
        @Test
        public void whenDateIsNotSpecifiedThenFilterIsNotActive() {
            WorkRecordsViewFilter endDateFilter = FiltersFactory.createEndDateFilter(datePicker);
            assertFalse(endDateFilter.isActive());
        }

        @Test
        public void givenDateWhenDateSelectedThenFilterIsActive() {
            LocalDate localDate = LocalDate.now();
            datePicker.getEditor().setText(localDate.toString());

            WorkRecordsViewFilter endDateFilter = FiltersFactory.createEndDateFilter(datePicker);
            assertTrue(endDateFilter.isActive());
        }

        @Test
        public void givenDateWhenDateSelectedThenFilterRejectsWorkRecordsWithLaterDates() {
            LocalDate localDate = LocalDate.of(2017, 5, 10);
            datePicker.getEditor().setText(datePicker.getConverter().toString(localDate));

            WorkRecordsViewFilter endDateFilter = FiltersFactory.createEndDateFilter(datePicker);

            LocalDate date = LocalDate.now();
            WorkRecord workRecord = new WorkRecord(new Company("firma", "adres"), 1, date);
            assertFalse(endDateFilter.accepts(workRecord));
        }
    }

    public static class NumberOfHoursFilterTests extends TextFieldBasedFilterTest {
        @Test
        public void whenTextFieldIsEmptyThenFilterIsNotActive() {
            WorkRecordsViewFilter numberOfHoursFilter = FiltersFactory.createNumberOfHoursFilter(textField);
            assertFalse(numberOfHoursFilter.isActive());
        }

        @Test
        public void givenCompanyNameWhenCompanyNameInsertedIntoFieldThenFilterIsActive() {
            String numberOfHours = String.valueOf(1);
            textField.setText(numberOfHours);

            WorkRecordsViewFilter numberOfHoursFilter = FiltersFactory.createNumberOfHoursFilter(textField);
            assertTrue(numberOfHoursFilter.isActive());
        }

        @Test
        public void givenNumberOfHoursWhenNumberOfHoursInsertedIntoFieldThenFilterAcceptsRecordsWithSpecifiedNumberOfHours() {
            String numberOfHours = String.valueOf(1);
            textField.setText(numberOfHours);

            WorkRecordsViewFilter numberOfHoursFilter = FiltersFactory.createNumberOfHoursFilter(textField);
            WorkRecord workRecord1 = new WorkRecord(new Company("firma", "adres"), 1, LocalDate.now());

            assertTrue(numberOfHoursFilter.accepts(workRecord1));
        }

        @Test
        public void givenNumberOfHoursWhenNumberOfHoursInsertedIntoFieldThenFilterRejectsRecordsWithNumberOfHoursOtherThanSpecified() {
            String numberOfHours = String.valueOf(1);
            textField.setText(numberOfHours);

            WorkRecordsViewFilter numberOfHoursFilter = FiltersFactory.createNumberOfHoursFilter(textField);
            WorkRecord workRecord1 = new WorkRecord(new Company("firma", "adres"), 2, LocalDate.now());

            assertFalse(numberOfHoursFilter.accepts(workRecord1));
        }
    }

    public static abstract class AbstractDateFilterTest extends FilterTest {
        DatePicker datePicker;

        @Before
        public void setUp() {
            datePicker = new DatePicker();
        }
    }

    public static abstract class TextFieldBasedFilterTest extends FilterTest {
        TextField textField;

        @Before
        public void setUp() {
            textField = new TextField();
        }
    }

    public static abstract class FilterTest extends ApplicationTest {
        @Override
        public void start(Stage stage) throws Exception {}
    }
}
