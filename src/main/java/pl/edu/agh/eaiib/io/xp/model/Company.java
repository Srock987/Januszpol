package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;

public class Company implements Serializable {
    private String name;
    private String address;

    public Company(String name, String address) {
        setName(name);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new RuntimeException("Company name is empty.");
        }
        if(name.length() < 3 || name.length() > 70){
            throw new RuntimeException("Company name is min 3 and max 70 signs.");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address == null || address.isEmpty()){
            throw new RuntimeException("Address is empty.");
        }
        if(address.length() < 3 || address.length() > 70){
            throw new RuntimeException("Address is min 3 and max 70 signs.");
        }
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        return address != null ? address.equals(company.address) : company.address == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
