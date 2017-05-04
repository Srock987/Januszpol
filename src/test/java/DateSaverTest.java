import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.io.IOException;
import java.io.Serializable;
import static org.junit.Assert.*;

public class DateSaverTest {
    @Test(expected=IllegalArgumentException.class)
    public void saveData_onEmptyFileName_throwsException() {
        Serializable obj = new Serializable() {};
        DataSaver saver = new DataSaver();
        saver.saveData("", obj);
    }

    class DataSaverMock extends DataSaver {
        public boolean called = false;
        @Override
        protected void writeDataToFile(String fileName, Serializable object) throws IOException {
            this.called = true;
        }
    }

    @Test
    public void saveData_onValidFileName_invokesSave() {
        Serializable obj = new Serializable() {};
        DataSaverMock saver = new DataSaverMock();
        saver.saveData("data.obj", obj);
        Assert.assertTrue(saver.called);
    }
}


