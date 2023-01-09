package budzet.proj;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBConnector.connect();

        /*
        try {
            ResultSet result = Queries.executeSelect("SELECT * FROM budzet_domowy.Domownicy");
            result.next();
            String imie = result.getString("IMIE");
            System.out.println("Znaleziono nastepujacych domownikow: " + imie);
        } catch (SQLException e){
            e.printStackTrace();
        }*/
    }
}