package budzet.proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Klasa Housemates przedstawia domowników zamieszczonych w tabeli "domownicy" w bazie danych MySQL
 * @author Kacper Głód
 * @version 1.02
 */
public class Housemates {

    /**
     * Jest to unikalne id osoby
     */
        private int id_osoby;
    /**
     * Jest to imie osoby
     */
        private String imie;
    /**
     * Jest to nazwisko osoby
     */
        private String nazwisko;

    /**
     *
     * @param id_osoby to unikalny identyfikator domownika
     * @param imie to imię domownika
     * @param nazwisko to nazwisko domownika
     */
        public Housemates(int id_osoby, String imie, String nazwisko) {
            this.id_osoby = id_osoby;
            this.imie = imie;
            this.nazwisko = nazwisko;
        }

    /**
     * Funkcja zwracająca id domownika
     * @return id domownika
     */
        public int getIdOsoby() {
            return id_osoby;
        }
    /**
     * Funkcja zwracająca imie domownika
     * @return imię domownika
     */
        public String getImie() {
            return imie;
        }
    /**
     * Funkcja zwracająca nazwisko domownika
     * @return nazwisko domownika
     */
        public String getNazwisko() {
            return nazwisko;
        }

    /**
     * Metoda pozwalająca ustawić wybrane imię domownika
     * @param imie to nowe imie domownika
     */
        public void setImie(String imie) {
            this.imie = imie;
        }
    /**
     * Metoda pozwalająca ustawić wybrane nazwisko domownika
     * @param nazwisko to nowe nazwisko domownika
     */
        public void setNazwisko(String nazwisko) {
            this.nazwisko = nazwisko;
        }

    /**
     * Funkcja typu String łącząca imie i nazwisko domownika w jeden ciąg
     * @return imię i nazwisko domownika
     */
    public String getFullName() {
        return imie + " " + nazwisko;
    }

    /**
     * Funkcja toString łączy imię i nazwisko domownika w jeden ciąg
     * @return imię i nazwisko domownika
     */
    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
/*
    public void saveDomownik(Housemates domownik) {
        Connection connection = BudgetManager.getConnection();
        String query = "INSERT INTO domownicy (id_osoby, imie, nazwisko) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, domownik.getIdOsoby());
            statement.setString(2, domownik.getImie());
            statement.setString(3, domownik.getNazwisko());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
}
