package pl.edu.agh.eaiib.io.xp.model;

import java.io.Serializable;

/**
 * Created by HP on 2017-06-15.
 */
public interface CompanySetRemote extends DataRecordSetRemote, Serializable {
    CompanyRemote add(String name, String address);
    CompanyRemote getCompanyByName(String companyName);
}
