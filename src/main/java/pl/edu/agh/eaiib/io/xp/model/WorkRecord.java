package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class WorkRecord implements Serializable {

    private Company company;
    private int hours;
    LocalDate date;

    public WorkRecord(Company company, int hours, LocalDate date) {
        this.company = company;
        this.hours = hours;
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
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
