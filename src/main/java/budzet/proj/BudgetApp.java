package budzet.proj;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class BudgetApp {
    private Connection conn;

    public BudgetApp() {
        // Połączenie z bazą danych MySQL
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/budzet_domowy", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Dodawanie nowego członka rodziny
    public void createMember(String firstName, String lastName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO domownicy (imie, nazwisko) VALUES (?, ?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Członek rodziny dodany poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Wyświetlanie wszystkich domowników
    public void readMembers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM domownicy");
            while (rs.next()) {
                System.out.println(rs.getInt("id_osoby") + " " + rs.getString("imie") + " " + rs.getString("nazwisko"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Aktualizowanie członka rodziny
    public void updateMember(int id, String firstName, String lastName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE domownicy SET imie = ?, nazwisko = ? WHERE id_osoby = ?");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Czlonek rodziny zaktualizowany poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Usuwanie członka rodziny
    public void deleteMember(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM domownicy WHERE id_osoby = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Czlonek rodziny poprawnie usuniety");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Dodawanie nowych transakcji
    public void createTransaction(int id_person, float amount, String title, String transaction_date) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transakcje (id_osoby, kwota, tytul_transakcji, data_transakcji) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, id_person);
            stmt.setFloat(2, amount);
            stmt.setString(3, title);
            stmt.setString(4, transaction_date);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transakcja dodana poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Wyswietlanie wszystkich transakcji
    public void readTransactions() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transakcje");
            while (rs.next()) {
                System.out.println(rs.getInt("id_transakcje") + " " + rs.getFloat("kwota") + " " + rs.getString("tytul_transakcji") + " " + rs.getString("data_transakcji") + " " + rs.getInt("id_osoby"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Aktualizowanie transakcji
    public void updateTransaction(int id, float amount, String title, String transaction_date) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE transakcje SET kwota = ?, tytul_transakcji = ?, data_transakcji = ? WHERE id_transakcje = ?");
            stmt.setFloat(1, amount);
            stmt.setString(2, title);
            stmt.setString(3, transaction_date);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transakcja zaktualizowana poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Usuwanie transakcji
    public void deleteTransaction(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM transakcje WHERE id_transakcje = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transakcja usunieta pomyslnie.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getTransactions() {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT * FROM transakcje");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName(int id_person) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT imie, nazwisko FROM domownicy WHERE id_osoby = ?");
            stmt.setInt(1, id_person);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("imie") + " " + rs.getString("nazwisko");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public float getTotalAmount() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(kwota) as total FROM transakcje");
            if (rs.next()) {
                return rs.getFloat("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean isValidYear(String year) {
        try {
            int yearNum = Integer.parseInt(year);
            if (yearNum > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidMonth(String month) {
        try {
            int monthNum = Integer.parseInt(month);
            if (monthNum >= 1 && monthNum <= 12) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Transaction> getTransactions(String year, String month) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            //Zapytanie zwracajace wszystkie transakcje z danego roku i miesiaca
            String query = "SELECT * FROM transakcje WHERE YEAR(data_transakcji) = ? AND MONTH(data_transakcji) = ?";
            // Przygotuj statement
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, year);
            stmt.setString(2, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_transakcje");
                double amount = rs.getDouble("kwota");
                String title = rs.getString("tytul_transakcji");
                Date date = rs.getDate("data_transakcji");
                int memberId = rs.getInt("id_osoby");
                // Add the transaction to the list
                transactions.add(new Transaction(id, amount, title, date, memberId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Housemates> getMembers() throws SQLException {
        List<Housemates> members = new ArrayList<>();
        try {
            String query = "SELECT * FROM domownicy";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_osoby");
                String firstName = rs.getString("imie");
                String lastName = rs.getString("nazwisko");
                members.add(new Housemates(id, firstName, lastName));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return members;
    }

}


