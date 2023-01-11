package budzet.proj;

public class Main {
    public static void main(String[] args) {
        //DBConnector.connect();
        new BudgetForm();

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