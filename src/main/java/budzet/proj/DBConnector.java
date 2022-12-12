package budzet.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String URL = "jdbc:mysql://localhost:3306/budzet_domowy";
    private static String USER = "root";
    private static String PASSWORD = "12345";

    public static Connection connect(){

        Connection connection = null;
        try {
            DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
