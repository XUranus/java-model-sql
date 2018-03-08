package lib;

import java.sql.*;

public class MysqlExcutor {

    static final String DB_NAME = "Test1";
    static final String USER = "root";
    static final String PASS = "password";

    /********************************************************/

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;

    public void query(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to mysql...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("creating instance statement");
            stmt = conn.createStatement();
            System.out.println("excuting query: "+sql);
           // ResultSet rs =
            Boolean rs = stmt.execute(sql);

            /*while(rs.next()){
                int id  = rs.getInt("id");
                String name = rs.getString("name");

                System.out.print("ID: " + id);
                System.out.print("name" + name);
                System.out.print("\n");
            }*/

            //rs.close();
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
}
