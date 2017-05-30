package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;

public class WorkRecord implements Serializable {

    private Company company;
    private float hours;

    public WorkRecord(Company company, float hours) {
        this.company = company;
        this.hours = hours;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }
}
