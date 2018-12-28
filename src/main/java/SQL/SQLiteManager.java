package SQL;

import Logger.Log;
import decryption.Decryption;
import sample.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public Boolean updateRow(int rowID, String colName, String value) {
        String sql1 = "UPDATE main.table_t SET '"+colName+"'='"+value+"' WHERE id = "+rowID;

        Log.info("Generated query => "+sql1);

        Boolean isUpdatedSuccessfully = false;
        try (
                PreparedStatement pstmt = conn.prepareStatement(sql1)
        ) {

            // set the corresponding param
            // update

            pstmt.executeUpdate();
            isUpdatedSuccessfully = true;
        } catch (SQLException e) {
            Log.error("ERROR (UPDATE) => "+e.getMessage());
            e.printStackTrace();
        }

        return isUpdatedSuccessfully;
    }


        public void decryptedRows(){
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM main.table_t");
            int countCols = rs.getMetaData().getColumnCount();

            List<String> col_names = new LinkedList<>();

            for(int i = 1; i <= countCols; i++){
                col_names.add(rs.getMetaData().getColumnName(i));
            }

            int successfully_updates = 0;
            int current_row_identity = 0;
            while(rs.next()){
                int current_row_id = rs.getRow();

                if(current_row_id > 20)
                    break;

                for(int current_col_id=0; current_col_id < col_names.size(); current_col_id++){
                    String col_val = col_names.get(current_col_id);
                    String row_val = rs.getString(current_col_id+1);


                    if(col_val.equals("id")) current_row_identity = Integer.valueOf(row_val);
                    Log.info("Current row identity => "+current_row_identity);

                    Log.info("Current column cell(" + current_row_id + "," + (current_col_id+1) + ") => " + col_val);
                    Log.info("Current row cell(" + current_row_id + "," + (current_col_id+1) + ") => " + row_val);

                    if(!col_val.equals("text") && !col_val.equals("id")) {


                        String decryption = Decryption.Decryption(row_val);

                        Log.info("Current row cell(" + current_row_id + "," + (current_col_id+1) + ") [de-ciphered] => " + decryption);

                        Log.info("Start Trying to attempt on site update...");

                        if(updateRow(current_row_identity,col_val,decryption)){
                            successfully_updates++;
                            Log.success("Successfully updated...");
                        }else{
                            Log.error("Can't update the cell...");
                        }

                        Log.info("End Trying to end on site update...");
                    }


                }
            }
            Log.info("Total successfully updates => "+successfully_updates);
        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
        }
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
