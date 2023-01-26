package budzet.proj;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Klasa Transaction przedstawia transakcje na podstawie tabeli "transakcje" utworzonej w MySQL.
 * @author Kacper Głód
 * @version 1.02
 */
public class Transaction {

    /**
     * Jest to id tranksacji
     */
    private int id;
    /**
     * Jest to kwota tranksacji
     */
    private double amount;
    /**
     * Jest to tytuł tranksacji
     */
    private String title;
    /**
     * Jest to data dokonania tranksacji
     */
    private Date date;
    /**
     * Jest to id odnoszące się do domownika, który odpowiada za transakcje, który jest kluczem obcym
     */
    private int memberId;

    /**
     * Konstruktor tworzący nowy obiekt transakcji
     *
     * @param id to id tranksacji
     * @param amount jest kwotą transakcji
     * @param title to tytuł transakcji
     * @param date jest parametrem typu Date, który przedstawia datę wykonania transakcji
     * @param memberId to klucz obcy odwołujący się do id domownika, który odpowiada za daną transakcję
     */
    public Transaction(int id, double amount, String title, Date date, int memberId) {
        this.id = id;
        this.amount = amount;
        this.title = title;
        this.date = date;
        this.memberId = memberId;
    }

    /**
     * Zwraca unikalny identyfikator transakcji
     * @return unikalne id transakcji
     */
    public int getId() {
        return id;
    }

    /**
     * Zwraca kwotę transakcji
     * @return kwota transakcji
     */
    public double getAmount() {
        return amount;
    }
    /**
     * Zwraca tytuł transakcji
     * @return tytuł transakcji
     */
    public String getTitle() {
        return title;
    }
    /**
     * Metoda pozwalająca ustawić wybrane id transakcji
     * @param id nowe id transakcji
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda pozwalająca ustawić kwotę transakcji
     * @param amount nowe id transakcji
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    /**
     * Metoda pozwalająca ustawić tytuł transakcji
     * @param title nowy tytuł transakcji
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Metoda zwraca datę transakcji
     * @return data transakcji
     */
    public Date getDate() {
        return date;
    }
    /**
     * Metoda pozwalająca ustawić datę transakcji
     * @param date nowa data transakcji
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Metoda pozwalająca ustawić id domownika, który dokonał transakcji
     * @param memberId nowe id domownika
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    /**
     * Metoda zwracająca datę transakcji
     * @return data transakcji
     */
    public Date getTransactionDate() {
        return date;
    }
    /**
     * Metoda zwracająca id domownika, który dokonał transakcji
     * @return id domownika
     */
    public int getMemberId() {
        return memberId;
    }
}
