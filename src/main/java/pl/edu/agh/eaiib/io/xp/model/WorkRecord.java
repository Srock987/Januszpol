package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class WorkRecord extends DataRecord implements WorkRecordRemote, Serializable {

    private CompanyRemote company;
    private int hours;
    LocalDate date;

    public WorkRecord() {

    }

    public WorkRecord(CompanyRemote company, int hours, LocalDate date) {
        this.company = company;
        this.hours = hours;
        this.date = date;
    }

    public CompanyRemote getCompany() {
        return company;
    }

    public void setCompany(CompanyRemote company) {
        this.company = company;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompanyName() {
        return this.company.getName();
    }
}
