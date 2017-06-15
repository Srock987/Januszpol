package pl.edu.agh.eaiib.io.xp.model;

import java.util.ArrayList;

/**
 * Created by HP on 2017-06-15.
 */
public class DataRecordSet implements DataRecordSetRemote{

    private ArrayList<DataRecordRemote> dataRecords;

    public DataRecordSet(ArrayList<DataRecordRemote> dataRecords){
        this.dataRecords = dataRecords;
    }

    @Override
    public DataRecordRemote get(int i){
        return dataRecords.get(i);
    }

    @Override
    public ArrayList<DataRecordRemote> getAll() {
        return dataRecords;
    }

    @Override
    public DataRecordRemote add() {

        return new DataRecord();
    }

    @Override
    public void save() {

    }

    @Override
    public void delete(int i) {
        dataRecords.remove(i);
    }

}
