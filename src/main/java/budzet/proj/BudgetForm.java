package budzet.proj;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BudgetForm extends JFrame {

    private BudgetManager budgetManager;
    private JList housematesList;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField amountField;
    private JLabel totalLabel;

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public BudgetForm() {
        budgetManager = new BudgetManager();
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Imie: "));
        nameField = new JTextField();
        inputPanel.add(nameField);

        /*Container container = new Container();
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(SCREEN_SIZE.width, 1));
        container.add(label);*/

        inputPanel.add(new JLabel("Nazwisko: "));
        surnameField = new JTextField();
        inputPanel.add(surnameField);

        /*JLabel label2 = new JLabel();
        label.setPreferredSize(new Dimension(SCREEN_SIZE.width, 1));
        container.add(label);*/

        inputPanel.add(new JLabel(" Kwota: "));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JButton addButton = new JButton("Dodaj transakcje");
        addButton.addActionListener(e -> {
            String imie = nameField.getText();
            String nazwisko = surnameField.getText();
            String amountText = amountField.getText();

            if (!imie.isEmpty() && !amountText.isEmpty()) {
                float kwota = Float.parseFloat(amountText);
                if (kwota > 0) {
                    budgetManager.addTransaction(kwota,4,-1);
                } else {
                    budgetManager.addHousemate(imie,nazwisko);
                }

                updateHousematesList();
                updateTotalLabel();

                nameField.setText("");
                surnameField.setText("");
                amountField.setText("");
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        JPanel housematesPanel = new JPanel();
        housematesPanel.setLayout(new BorderLayout());
        housematesPanel.add(new JLabel("Domownicy:"), BorderLayout.NORTH);
        housematesList = new JList();
        updateHousematesList();
        housematesPanel.add(new JScrollPane(housematesList), BorderLayout.CENTER);
        add(housematesPanel, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        totalPanel.add(new JLabel("Laczna kwota: "), BorderLayout.WEST);
        totalLabel = new JLabel();
        updateTotalLabel();
        totalPanel.add(totalLabel, BorderLayout.EAST);
        add(totalPanel, BorderLayout.SOUTH);

        setTitle("Budzet domowy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    private void updateHousematesList() {
        List<String> housemates = budgetManager.getHousemates();
        housematesList.setListData(housemates.toArray());
    }

    private void updateTotalLabel() {
        totalLabel.setText(String.format("%.2f", budgetManager.getTotal()));
    }

}
