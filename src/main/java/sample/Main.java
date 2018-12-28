package sample;

import Logger.Log;
import ResourceDir.ResourceFile;
import SQL.SQLiteManager;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args){

        Log.info("Entering Application...");

        String rel_file_location_EncData = "encrypted_data.roa";
        String abs_path_target_db = (new ResourceFile(rel_file_location_EncData)).getAbsolutePath();

        final SQLiteManager sqLiteManager = new SQLiteManager(abs_path_target_db);

        sqLiteManager.decryptedRows();
    }
}
