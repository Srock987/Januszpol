import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;
import pl.edu.agh.eaiib.io.xp.utils.DataSaverInterface;

import java.io.Serializable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by HP on 2017-04-20.
 */
public class DateSaverTest implements Serializable{


    @Test
    public void testSaving() {

        class Test implements DataSaverInterface{

            @Override
            public String getFileName() {
                return "data.dat";
            }
        }

        class Test2 implements DataSaverInterface{

            @Override
            public String getFileName() {
                return null;
            }
        }

        assertTrue(DataSaver.saveData(new Test()));
        assertFalse(DataSaver.saveData(new Test2()));
    }

}
