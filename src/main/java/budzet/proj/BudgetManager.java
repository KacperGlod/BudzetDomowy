package budzet.proj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private Connection connection;

    private static String URL = "jdbc:mysql://localhost:3306/budzet_domowy";
    private static String USER = "root";
    private static String PASSWORD = "";

    public BudgetManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addHousemate(String imie,String nazwisko) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO domownicy (imie,nazwisko) VALUES (?)(?)");
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(float kwota, int id_transakcje, int id_osoby) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO transakcje (id_transakcje, kwota, id_osoby) VALUES (?, ?, ?)");
            statement.setInt(1, id_transakcje);
            statement.setFloat(2, kwota);
            statement.setInt(3, id_osoby);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getHousemates() {
        List<String> domownicy = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM domownicy");
            while (resultSet.next()) {
                int id_osoby = resultSet.getInt("id_osoby");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                domownicy.add(id_osoby + " " + imie + " " + nazwisko);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return domownicy;
    }

    public float getTotal() {
        float total = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transakcje");
            while (resultSet.next()) {
                float kwota = resultSet.getFloat("kwota");
                total += kwota;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}


