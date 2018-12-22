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

        Log.info("Loading existing encrypted table...");
        String rel_file_location_EncData = "encrypted_data.roa";
        Log.success("Completed process to fetch absolute path of existing encrypted table...");
        String abs_path_target_db = (new ResourceFile(rel_file_location_EncData)).getAbsolutePath();


        Log.info("Create new Table 't3' to store decrypted data in it...");
        String old_table_name = "table_t";
        String new_table_name = "decrypted";

        Log.info("Starting sqlite manager process...");
        final SQLiteManager sqLiteManager = new SQLiteManager(abs_path_target_db);

        Log.info("Deciphering and cloning the table to new table...");
        sqLiteManager.CloneDecrypted();

        Log.success("End Application...");
    }
}
