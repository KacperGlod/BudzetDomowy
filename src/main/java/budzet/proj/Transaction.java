package budzet.proj;

import java.sql.Date;
import java.time.LocalDate;
public class Transaction {
    private int id;
    private double amount;
    private String title;
    private Date date;
    private int memberId;

    public Transaction(int id, double amount, String title, Date date, int memberId) {
        this.id = id;
        this.amount = amount;
        this.title = title;
        this.date = date;
        this.memberId = memberId;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getTransactionDate() {
        return date;
    }

    public int getMemberId() {
        return memberId;
    }

}
