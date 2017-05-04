import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.Company;
import pl.edu.agh.eaiib.io.xp.WorkRecord;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.io.Serializable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by HP on 2017-04-20.
 */
public class DateSaverTest {

    @Test
    public void testSaving() {

        assertTrue(DataSaver.saveData(new Company()));
        assertTrue(DataSaver.saveData(new WorkRecord()));
    }

}
