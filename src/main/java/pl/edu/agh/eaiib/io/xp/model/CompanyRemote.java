package pl.edu.agh.eaiib.io.xp.model;

/**
 * Created by HP on 2017-06-15.
 */
public interface CompanyRemote extends DataRecordRemote {

    void setName(String name);
    void setAddress(String Address);
    String getName();
    String getAddress();

}
