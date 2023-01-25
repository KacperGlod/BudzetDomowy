package budzet.proj;

public class Main {
    public static void main(String[] args) {
        //BudgetManager.connect();
        //new BudgetForm();
        //new BudgetApp();

        BudgetApp Budzet = new BudgetApp();

        BudgetAppGUI GUI = new BudgetAppGUI();


        //app.createMember("Jan", "Nowak");
        //app.readMembers();
        //app.createTransaction(1, 100.50f, "Zakupy spożywcze", "2022-05-10");
        //app.readTransactions();
    }
}


        /*
        try {
            ResultSet result = Queries.executeSelect("SELECT * FROM budzet_domowy.Domownicy");
            result.next();
            String imie = result.getString("IMIE");
            System.out.println("Znaleziono nastepujacych domownikow: " + imie);
        } catch (SQLException e){
            e.printStackTrace();
        }*/