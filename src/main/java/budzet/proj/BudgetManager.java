package budzet.proj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Klasa BudgetManager pozwala na połączenie się z bazą danych oraz wykonywanie podstawowych operacji na bazie.
 * @author Kacper Głód
 * @version 1.04
 */
public class BudgetManager {
    /**
     * Prywatna zmienna typu Connection będąca zmienną służącą do odwoływania się do połączenia
     */
    private Connection conn;

    /**
     * Konstruktor klasy BudgetManager pozwalający połączyć się z bazą danych MySQL.
     * W metodzie getConnection dodajemy argumenty jakimi są url bazy danych, nazwa użytkownika bazy oraz hasło.
     */
    public BudgetManager() {
        // Połączenie z bazą danych MySQL
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/budzet_domowy", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pozwalająca na dodawanie nowego członka rodziny
     * @param firstName to imie domownika
     * @param lastName to nazwisko domownika
     */
    public void createMember(String firstName, String lastName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO domownicy (imie, nazwisko) VALUES (?, ?)");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Czlonek rodziny dodany poprawnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pozwalająca na wyświetlanie wszystkich domowników
     */
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

    /**
     * Metoda pozwalająca na aktualizowanie członka rodziny
     * @param id to id użytkownika
     * @param firstName to nowe imie domownika
     * @param lastName to nowe nazwisko domownika
     */
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

    /**
     * Metoda pozwalająca na usuwanie członka rodziny
     * @param id to id domownika, którego chcemy usunąć
     */
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

    /**
     * Metoda pozwalająca na dodawanie nowych transakcji
     * @param id_person to id domownika, który dokonał transakcji
     * @param amount to kwota transakcji
     * @param title to tytuł transakcji
     * @param transaction_date to data transakcji
     */
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

    /**
     * Metoda pozwalająca na wyświetlanie wszystkich transakcji
     */
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

    /**
     * Metoda pozwalająca na aktualizowanie transakcji
     * @param id to id transakcji
     * @param amount to kwota transakcji
     * @param title to tytuł transakcji
     * @param transaction_date to data transakcji
     */
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

    /**
     * Metoda pozwalająca na usuwanie transakcji
     * @param id to id transakcji, którą chcemy usunąć
     */
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

    /**
     * Funkcja zwracająca wszystkie transakcje w bazie danych
     * @return transakcje w tabeli "transakcje"
     */
    public ResultSet getTransactions() {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT * FROM transakcje");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Funkcja pozwalająca zwrócić imię i nazwisko domownika
     * @param id_person id domownika którego chcemy pozyskać imię i nazwisko
     * @return imię i nazwisko domownika
     */
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

    /**
     * Funkcja liczy sumę środków znajdujących się w budżecie domowym
     * @return suma środków w budżecie
     */
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

    /**
     * Funkcja typu boolean sprwadzająca poprawność roku.
     * Używana przy wyświetlaniu statystyki miesięcznej.
     * @param year to rok będący argumentem funkcji
     * @return zwraca prawdę lub fałsz, w zależności czy rok zgadzą się z podanym przez użytkownika
     */
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

    /**
     * Funkcja typu boolean sprwadzająca poprawność miesiąca.
     * Używana przy wyświetlaniu statystyki miesięcznej.
     * @param month to miesiąc będący argumentem funkcji
     * @return zwraca prawdę lub fałsz, w zależności czy miesiąc zgadzą się z podanym przez użytkownika
     */
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

    /**
     * Funkcja zwracająca listę tranksacji
     * @param year to rok dokonania transakcji
     * @param month to miesiąc dokonania transakcji
     * @return lista dokonanych transakcji
     */
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
                // Dodaj transakcję do listy
                transactions.add(new Transaction(id, amount, title, date, memberId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Funkcja zwracająca listę domowników
     * @return Funkcja zwraca listę domowników
     * @throws SQLException to wyjątek aktywowany w przypadku błędów związanych z bazą danych
     */
    public List<Housemates> getMembers() throws SQLException {
        List<Housemates> members = new ArrayList<>();

            String query = "SELECT * FROM domownicy";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_osoby");
                String firstName = rs.getString("imie");
                String lastName = rs.getString("nazwisko");
                members.add(new Housemates(id, firstName, lastName));
            }
        return members;
    }

    /**
     * Funkcja pozwalająca nam uzyskać id domownika przy użyciu jego imienia i nazwiska
     * @param name przechowuje imie i nazwisko poszukiwanego domownika
     * @return id użytkownika
     */
    public int getIdByName(String name) {
        int id = 0;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id_osoby FROM domownicy WHERE imie = ? AND nazwisko = ?");
            String[] nameArray = name.split(" ");
            statement.setString(1, nameArray[0]);
            statement.setString(2, nameArray[1]);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_osoby");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


}


