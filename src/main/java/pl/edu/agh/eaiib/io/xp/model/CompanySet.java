package pl.edu.agh.eaiib.io.xp.model;

import pl.edu.agh.eaiib.io.xp.data.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HP on 2017-06-15.
 */
public class CompanySet extends DataRecordSet implements CompanySetRemote {

    public CompanySet(ArrayList<DataRecordRemote> dataRecords) {
        super(dataRecords);
    }

    @Override
    public DataRecordRemote add() {
        Company company = new Company();
        getAll().add(company);
        return company;
    }

    @Override
    public void save() {
        super.save();
        Database.getInstance().setDataRecordSet(Database.COMPANY, this);
    }

    @Override
    public CompanyRemote add(String name, String address) {

        Company company = new Company(name, address);
        for (DataRecordRemote savedCompany : getAll()) {
            if (((Company) savedCompany).getName().equals(company.getName())) {
                throw new RuntimeException("Podana nazwa firmy istnieje już w bazie danych.");
            }
            if (((Company) savedCompany).getAddress().equals(company.getAddress())) {
                throw new RuntimeException("Podany adres istnieje już w bazie danych dla firmy " + ((Company) savedCompany).getName());
            }
        }
        getAll().add(company);

        return company;
    }

    @Override
    public CompanyRemote getCompanyByName(String companyName) {

        List<DataRecordRemote> matchingCompanies = Database.getInstance().getDataRecordSet(Database.COMPANY).getAll()
                .stream()
                .filter(company -> ((CompanyRemote) company)
                        .getName()
                        .equals(companyName))
                .collect(Collectors.toList());
        if(matchingCompanies.size() < 1){
            throw new RuntimeException("Podana firma nie istnieje już w bazie danych.");
        }

        return (CompanyRemote) matchingCompanies.get(0);
    }
}
