package SQL;

import Logger.Log;
import sample.Main;

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

    public void getAllRowsData(String sql) {
        Log.info("Make a query on => "+sql);
        PreparedStatement pstmt = null;
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
}
