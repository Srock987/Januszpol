package pl.edu.agh.eaiib.io.xp;

import pl.edu.agh.eaiib.io.xp.utils.DataSaverInterface;

import java.io.Serializable;

/**
 * Created by HP on 2017-04-20.
 */
public class Company implements DataSaverInterface {

    @Override
    public String getFileName() {
        return "companies.dat";
    }
}
