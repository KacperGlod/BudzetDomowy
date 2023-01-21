package budzet.proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Housemates {

        private int id_osoby;
        private String imie;
        private String nazwisko;

        public Housemates(int id_osoby, String imie, String nazwisko) {
            this.id_osoby = id_osoby;
            this.imie = imie;
            this.nazwisko = nazwisko;
        }

        public int getIdOsoby() {
            return id_osoby;
        }

        public String getImie() {
            return imie;
        }

        public String getNazwisko() {
            return nazwisko;
        }

        public void setImie(String imie) {
            this.imie = imie;
        }

        public void setNazwisko(String nazwisko) {
            this.nazwisko = nazwisko;
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
