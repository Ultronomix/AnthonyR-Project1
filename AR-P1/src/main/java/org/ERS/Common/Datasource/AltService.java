
package org.ERS.Common.Datasource;
import java.sql.*;

public class AltService {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    static final String USER = "postgres";
    static final String PASS = "123pescao";

    public static Connection Connect() {
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName("org.postgresql.Driver");


            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_id, given_name, surname, email FROM app_users";


            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){

                String id  = rs.getString("user_id");
                String first = rs.getString("given_name");
                String last = rs.getString("surname");


                System.out.print("user_id: " + id);
                System.out.print(", given_name: " + first);
                System.out.println(", surname: " + last);
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
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return conn;
    }
}