package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.DataRecordSet;
import pl.edu.agh.eaiib.io.xp.model.DataRecordSetRemote;

/**
 * Created by HP on 2017-06-15.
 */
public interface DatabaseRemote {

    DataRecordSetRemote getDataRecordSet(String dataName);
    void setDataRecordSet(String dataName, DataRecordSetRemote dataRecordSetRemote);

}
