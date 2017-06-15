package pl.edu.agh.eaiib.io.xp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataLoader<T> {

    public ArrayList<T> loadData(String fileName) {
        if (fileName.isEmpty())
            throw new IllegalArgumentException("File name is empty!");
        try {
            return this.readDataFromFile(fileName);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    protected ArrayList<T> readDataFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(inputStream);
        ArrayList<T> list = (ArrayList<T>) in.readObject();
        in.close();
        inputStream.close();

        return list == null ? new ArrayList<T>() : list;
    }
}
