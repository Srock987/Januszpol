package pl.edu.agh.eaiib.io.xp.utils;

import java.io.*;

/**
 * Created by HP on 2017-04-20.
 */
public class DataSaver {

    public static boolean saveData(DataSaverInterface object){

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(object.getFileName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fos == null)
            return false;
        else
            return true;
    }
}
