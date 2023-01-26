package budzet.proj;

/**
 * Klasa Main to główna klasa do zarządzania budżetem domowym.
 * W klasie tworzymy obiekt klasy BudgetManager pozwalającej na połączenie z bazą danych MySQL oraz wykonywanie funkcji i metod na bazie.
 * W klasie tworzymy także obiekt klasy BudgetAppGUI tworzący nowe okno interfejsu graficznego.
 * @author Kacper Głód
 * @version 1.06
 * @see BudgetManager
 * @see BudgetAppGUI
 */
public class Main {
    /**
     * Metoda main tworzy obiekt klasy {@link BudgetManager} oraz obiekt klasy {@link BudgetAppGUI}.
     * @param args argumenty metody, które są przekazywane z linii komend.
     */
    public static void main(String[] args) {
        //BudgetManager.connect();
        //new BudgetForm();
        //new BudgetManager();

        BudgetManager Budzet = new BudgetManager();

        BudgetAppGUI GUI = new BudgetAppGUI();


        //app.createMember("Jan", "Nowak");
        //app.readMembers();
        //app.createTransaction(1, 100.50f, "Zakupy spożywcze", "2022-05-10");
        //app.readTransactions();
    }
}
