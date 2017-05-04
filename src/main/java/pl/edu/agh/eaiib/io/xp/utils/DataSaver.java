package pl.edu.agh.eaiib.io.xp.utils;

import java.io.*;

public class DataSaver {
    public boolean saveData(String fileName, Serializable object) {
        if (fileName.isEmpty())
            throw new IllegalArgumentException("File name is empty!");

        try {
            this.writeDataToFile(fileName, object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected void writeDataToFile(String fileName, Serializable object) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
    }
}
