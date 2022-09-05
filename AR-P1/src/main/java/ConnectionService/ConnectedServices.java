package ConnectionService;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectedServices{

    private final String url = "jdbc:postgresql://rev.cwkbyfm1mymj.us-east-1.rds.amazonaws.com:5432/Rev";
    private final String user = "postgres";
    private final String password = "ABC321!!";



    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }





}
