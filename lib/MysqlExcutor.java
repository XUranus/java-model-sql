package lib;

import java.sql.*;
import java.util.ArrayList;

public class MysqlExcutor {
    private static Connection conn;
    private static Statement stmt;

    static final String DB_NAME = "Test1";
    static final String USER = "root";
    static final String PASS = "password";

    /********************************************************/

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;

    public MysqlExcutor() {
        Connection conn = null;
        Statement stmt = null;
    }

    public void ConnectionInit() {
        //Initialize Connection to MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to MySQL...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("creating instance statement...");
            stmt = conn.createStatement();
            System.out.println("connected success");
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void excute(String sql) {
        /**
         This Method is used to excute all SQL command
         No Return value
         **/
        ConnectionInit();
        try{
            System.out.println("excuting query: "+sql);
            Boolean rs = stmt.execute(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("excute query finished");
    }

    public int excuteUpdate(String sql) {
        /**
         * Used to Excute
         * update，insert，delete
        **/
        ConnectionInit();
        int rs = 0;
        try {
            System.out.println("excuteUpdate query: "+sql);
            rs = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("query finished");
        return rs;
    }


    public ArrayList excuteQuery(ArrayList<Attribute> attrList,String sql) {
        /**
         * Used to Excute
         * select
         **/
        ResultSet rs = null;
        ArrayList<ArrayList> modelArrays = null;
        ConnectionInit();
        try {
            System.out.println("excuteQuery query: "+sql);
            rs = stmt.executeQuery(sql);

            modelArrays = new ArrayList<>();
            while(rs.next()){
                ArrayList<Attribute> arrays = new ArrayList();
                for(Attribute attr : attrList) {
                    arrays.add(new Attribute(attr.getKey(),rs.getObject(attr.getKey())));
                }
                modelArrays.add(arrays);
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("query finished");
        return modelArrays;
    }
}
