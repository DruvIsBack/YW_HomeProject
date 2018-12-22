package SQL;

import Logger.Log;
import ResourceDir.ResourceFile;
import sample.Main;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;

public class SQLiteManager {

    private ResultSet rs;
    private Connection conn = null;
    private String connection_url = null;

    //Constructors...
    SQLiteManager(){
        super();
    }

    public SQLiteManager(String relational_file_path){
        super();
        connection_url = "jdbc:sqlite:"+relational_file_path;
        if(buildConnection()){
            Log.success("SQLite DB Connection successfully build.");
        }else{
            if(connection_url == null || connection_url.isEmpty()) connection_url = "[ blank ]";

            Log.error("Invalid file url => "+connection_url);
        }
    }

    public boolean buildConnection(){
        try {
            Log.info("Connection string => "+connection_url);
            conn = DriverManager.getConnection(connection_url);

            Log.success("Connection to SQLite has been established.");

        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setConnection_url(String connection_url) {
        this.connection_url = connection_url;
    }

    public Connection getConn() {
        return conn;
    }

    public void CloneDecrypted() {
        String res_abs_path = new ResourceFile().getResourseDirAbsPath();

        String old_db_file_url = "jdbc:sqlite:"+res_abs_path+"encrypted_data.roa";
        String new_db_file_url = "jdbc:sqlite:"+res_abs_path+"decrypted_data.roa";

        String table_new = "table_t";
        String sql_create_new_table = "create table IF NOT EXISTS "+table_new+"\n" +
                "(\n" +
                "  id                                   INTEGER\n" +
                "    primary key,\n" +
                "  text                                 TEXT,\n" +
                "  value1                               TEXT,\n" +
                "  \"IQUALIF 712 White and Yellow\"       TEXT,\n" +
                "  \"IQUALIF Belgium White\"              TEXT,\n" +
                "  \"IQUALIF Belgium Yellow\"             TEXT,\n" +
                "  \"IQUALIF Canada White\"               TEXT,\n" +
                "  \"IQUALIF Canada Yellow\"              TEXT,\n" +
                "  \"IQUALIF England White\"              TEXT,\n" +
                "  \"IQUALIF England Yellow\"             TEXT,\n" +
                "  \"IQUALIF France White\"               TEXT,\n" +
                "  \"IQUALIF France Yellow\"              TEXT,\n" +
                "  \"IQUALIF Luxembourg White\"           TEXT,\n" +
                "  \"IQUALIF Luxembourg Yellow\"          TEXT,\n" +
                "  \"IQUALIF Madagascar White\"           TEXT,\n" +
                "  \"IQUALIF Madagascar Yellow\"          TEXT,\n" +
                "  \"IQUALIF Morocco White\"              TEXT,\n" +
                "  \"IQUALIF Morocco Yellow\"             TEXT,\n" +
                "  \"IQUALIF Netherlands White\"          TEXT,\n" +
                "  \"IQUALIF Netherlands Yellow\"         TEXT,\n" +
                "  \"IQUALIF Polynesia White\"            TEXT,\n" +
                "  \"IQUALIF Polynesia Yellow\"           TEXT,\n" +
                "  \"IQUALIF Switzerland White\"          TEXT,\n" +
                "  \"IQUALIF Switzerland Yellow\"         TEXT,\n" +
                "  \"IQUALIF United States White\"        TEXT,\n" +
                "  \"IQUALIF United States Yellow\"       TEXT,\n" +
                "  \"IQUALIF PP Yellow\"                  TEXT,\n" +
                "  \"IQUALIF BCoin White and Yellow\"     TEXT,\n" +
                "  \"IQUALIF Viva White and Yellow\"      TEXT default null,\n" +
                "  \"IQUALIF Tunisia Yellow\"             TEXT,\n" +
                "  \"IQUALIF Kapa White and Yellow\"      TEXT,\n" +
                "  \"IQUALIF United Kingdom White\"       TEXT,\n" +
                "  \"IQUALIF United Kingdom Yellow\"      TEXT,\n" +
                "  \"IQUALIF Australia Yellow\"           TEXT,\n" +
                "  \"IQUALIF Australia White\"            TEXT,\n" +
                "  \"IQUALIF Germany White\"              TEXT,\n" +
                "  \"IQUALIF Germany Yellow\"             TEXT,\n" +
                "  \"IQUALIF United Kingdom YL Yellow\"   TEXT,\n" +
                "  \"IQUALIF Spain Yellow\"               TEXT,\n" +
                "  \"IQUALIF Spain White\"                TEXT,\n" +
                "  \"IQUALIF Italy Yellow\"               TEXT,\n" +
                "  \"IQUALIF Italy White\"                TEXT,\n" +
                "  \"IQUALIF Portugal White\"             TEXT,\n" +
                "  \"IQUALIF Portugal Yellow\",\n" +
                "  \"IQUALIF Hungary White\"              TEXT,\n" +
                "  \"IQUALIF Hungary Yellow\"             TEXT,\n" +
                "  \"IQUALIF Africa Yellow\"              TEXT,\n" +
                "  \"IQUALIF Belgium 07 Yellow\"          TEXT,\n" +
                "  \"IQUALIF Dominica White\"             TEXT,\n" +
                "  \"IQUALIF Lucia White\"                TEXT,\n" +
                "  \"IQUALIF First Name White\"           TEXT,\n" +
                "  \"IQUALIF Denmark Yellow\"             TEXT,\n" +
                "  \"IQUALIF Sweden Yellow\"              TEXT,\n" +
                "  \"IQUALIF Norway Yellow\"              TEXT,\n" +
                "  \"IQUALIF Finland Yellow\"             TEXT,\n" +
                "  \"IQUALIF United States WP White\"     TEXT,\n" +
                "  \"IQUALIF TAdvisor Yellow\"            TEXT,\n" +
                "  \"IQUALIF Anbis White and Yellow\"     TEXT,\n" +
                "  \"IQUALIF Tuti White and Yellow\"      TEXT,\n" +
                "  \"IQUALIF Croatia Yellow\"             TEXT,\n" +
                "  \"IQUALIF Austria Yellow\"             TEXT,\n" +
                "  \"IQUALIF Peru Yellow\"                TEXT,\n" +
                "  \"IQUALIF India Yellow\"               TEXT,\n" +
                "  \"IQUALIF Sbito White and Yellow\"     TEXT,\n" +
                "  \"IQUALIF Auto24 White and Yellow\"    TEXT,\n" +
                "  \"IQUALIF Immo24 White and Yellow\"    TEXT,\n" +
                "  \"IQUALIF PAnnonces White and Yellow\" TEXT,\n" +
                "  \"IQUALIF Canada 41 White\"            TEXT,\n" +
                "  \"IQUALIF Saudi Arabia Yellow\"        TEXT,\n" +
                "  \"IQUALIF Philippines Yellow\"         TEXT,\n" +
                "  \"IQUALIF Oedkniss White and Yellow\"  TEXT,\n" +
                "  \"IQUALIF Singapore Yellow\"           TEXT,\n" +
                "  \"IQUALIF Bangladesh Yellow\"          TEXT,\n" +
                "  \"IQUALIF Russia Yellow\"              TEXT,\n" +
                "  \"IQUALIF AConcession Yellow\"         TEXT,\n" +
                "  \"IQUALIF Multi White and Yellow\"     TEXT,\n" +
                "  \"IQUALIF Switzerland CH White\"       TEXT,\n" +
                "  \"IQUALIF France PI Yellow\"           TEXT,\n" +
                "  \"IQUALIF Germany GS Yellow\"          TEXT,\n" +
                "  \"IQUALIF Dliveroo Yellow\"            TEXT,\n" +
                "  \"IQUALIF Poland Yellow\"              TEXT,\n" +
                "  \"IQUALIF Italy PG Yellow\"            TEXT,\n" +
                "  \"IQUALIF Switzerland CH Yellow\"      TEXT,\n" +
                "  \"IQUALIF Switzerland ZP Yellow\"      TEXT,\n" +
                "  \"IQUALIF Architecte FR Yellow\"       TEXT,\n" +
                "  \"IQUALIF Slovakia Yellow\"            TEXT\n" +
                ");\n";


        try (
                PreparedStatement stmt =  conn.prepareStatement(sql)
        ){
            rs = stmt.executeQuery();

            ResultSetMetaData metadata = rs.getMetaData();

            int columnCount = metadata.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                Log.info(metadata.getColumnName(i) + ", ");
            }

        } catch (SQLException e) {
            Log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    boolean insertDataInTable(String fileNameWithLocation){
        Log.info("<= New SQLITE data insert process start => ");
        Log.info("DB FileName with location => "+fileNameWithLocation);

        String dbUrl = "jdbc:sqlite:" + fileNameWithLocation;
        try (
                Connection conn = DriverManager.getConnection(dbUrl)
        ){
            conn.
            if (conn != null) {

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

        Log.info("<= New SQLITE data insert process end => ");
    }


    boolean createNewDatabase(String fileNameWithLocation) {
        Log.info("New SQLITE DB Location => "+fileNameWithLocation);
        String url = "jdbc:sqlite:" + fileNameWithLocation;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Log.info("The driver name is " + meta.getDriverName());
                Log.success("A new database has been created.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
