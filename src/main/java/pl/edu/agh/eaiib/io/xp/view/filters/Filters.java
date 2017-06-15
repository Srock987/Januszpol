package pl.edu.agh.eaiib.io.xp.view.filters;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pl.edu.agh.eaiib.io.xp.model.WorkRecord;
import pl.edu.agh.eaiib.io.xp.model.WorkRecordRemote;

import java.time.LocalDate;

class Filters {
    private static boolean isEmpty(TextField textField) {
        String text = textField.getText();
        return text == null || text.isEmpty();
    }

    static class BeginDateFilter implements WorkRecordsViewFilter {
        private final DatePicker beginDatePicker;

        BeginDateFilter(DatePicker beginDatePicker) {
            this.beginDatePicker = beginDatePicker;
        }

        @Override
        public boolean isActive() {
            return !isEmpty(beginDatePicker.getEditor());
        }

        @Override
        public boolean accepts(WorkRecordRemote record) {
            LocalDate beginDate = beginDatePicker.getConverter().fromString(beginDatePicker.getEditor().getText());
            return record.getDate().isAfter(beginDate) || record.getDate().isEqual(beginDate);
        }
    }

    static class EndDateFilter implements WorkRecordsViewFilter {
        private final DatePicker endDatePicker;

        EndDateFilter(DatePicker endDatePicker) {
            this.endDatePicker = endDatePicker;
        }

        @Override
        public boolean isActive() {
            return !isEmpty(endDatePicker.getEditor());
        }

        @Override
        public boolean accepts(WorkRecordRemote record) {
            LocalDate endDate = endDatePicker.getConverter().fromString(endDatePicker.getEditor().getText());
            return record.getDate().isBefore(endDate) || record.getDate().isEqual(endDate);
        }
    }

    static class CompanyNameFilter implements WorkRecordsViewFilter {
        private final TextField companyNameField;

        CompanyNameFilter(TextField companyNameField) {
            this.companyNameField = companyNameField;
        }

        @Override
        public boolean isActive() {
            return !isEmpty(companyNameField);
        }

        @Override
        public boolean accepts(WorkRecordRemote record) {
            return record.getCompanyName().toUpperCase().contains(companyNameField.getText().toUpperCase());
        }
    }

    static class NumberOfHoursFilter implements WorkRecordsViewFilter {
        private static final String ERROR_STYLE = "-fx-border-color: #990000 ; -fx-background-color: lightcoral; -fx-border-radius: 2px";
        private final TextField numberOfHoursField;

        NumberOfHoursFilter(TextField numberOfHoursField) {
            this.numberOfHoursField = numberOfHoursField;
        }

        @Override
        public boolean isActive() {
            resetStyle();
            return !isEmpty(numberOfHoursField) && getNumberOfHours() > 0;
        }

        @Override
        public boolean accepts(WorkRecordRemote record) {
            return record.getHours() == getNumberOfHours();
        }

        private int parseNumberOfHours() throws InvalidNumberOfHoursException {
            try {
                int hours = Integer.parseInt(numberOfHoursField.getText());
                if (hours <= 0) {
                    throw new InvalidNumberOfHoursException();
                }
                return hours;
            } catch (Exception e) {
                throw new InvalidNumberOfHoursException(e);
            }
        }

        private int getNumberOfHours() {
            try {
                int hours = parseNumberOfHours();
                resetStyle();
                return hours;
            } catch (InvalidNumberOfHoursException e) {
                setErrorStyle();
            }
            return -1;
        }

        private void resetStyle() {
            numberOfHoursField.setStyle("");
        }

        private void setErrorStyle() {
            numberOfHoursField.setStyle(ERROR_STYLE);
        }

        private static final class InvalidNumberOfHoursException extends Exception {
            private InvalidNumberOfHoursException(Exception e) {
                super(e);
            }

            private InvalidNumberOfHoursException() {
            }
        }
    }
}

