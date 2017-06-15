package pl.edu.agh.eaiib.io.xp.data;

import pl.edu.agh.eaiib.io.xp.model.*;
import pl.edu.agh.eaiib.io.xp.utils.DataLoader;
import pl.edu.agh.eaiib.io.xp.utils.DataSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by frben on 25.05.2017.
 */
public class Database {

    public static final String COMPANY = "company";
    public static final String WORKRECORD = "workrecord";

    private static DatabaseRemote database = null;

    public static DatabaseRemote getInstance(){

        if(database == null){
            database = new InfilesDatabase();
            return database;
        }else
            return database;
    }

}
