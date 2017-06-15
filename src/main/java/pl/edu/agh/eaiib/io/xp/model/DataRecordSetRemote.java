package pl.edu.agh.eaiib.io.xp.model;

import java.util.ArrayList;

/**
 * Created by HP on 2017-06-15.
 */
public interface DataRecordSetRemote {

    void save();

    void delete(int i);

    DataRecordRemote add();

    DataRecordRemote get(int i);

    ArrayList<DataRecordRemote> getAll();

}
