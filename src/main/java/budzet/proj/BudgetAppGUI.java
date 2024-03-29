package budzet.proj;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa BudgetAppGUI odpowiada za graficzny interfejs programu.
 * W klasie znajdują się poszczególne kompontenty odpowiadające elementom GUI.
 * @author Kacper Głód
 * @version 1.05
 */
public class BudgetAppGUI extends JFrame {
    /**
     * Tworzenie obiektu klasy BudgetManager, w celu używania funkcji z tej klasy
     */
    private BudgetManager budgetManager;
    /**
     * Tabela odpowiadająca za wyświetlanie transakcji
     */
    private JTable transactionsTable;
    /**
     * Interfejs reprezentujący dane zestawione w tabeli
     */
    private DefaultTableModel tableModel;
    /**
     * Pole tekstowe, w którym wpisujemy kwotę transakcji
     */
    private JTextField amountField;
    /**
     * Pole tekstowe, w którym wpisujemy tytuł transakcji
     */
    private JTextField titleField;
    /**
     * Pole tekstowe, w którym wpisujemy datę transakcji
     */
    private JTextField dateField;
//    private JTextField memberIdField;
    /**
     * Pole tekstowe, w którym wpisujemy imię domownika
     */
    private JTextField firstNameField;
    /**
     * Pole tekstowe, w którym wpisujemy imię domownika
     */
    private JTextField lastNameField;
    /**
     * Rozwijana lista domowników
     */
    private JComboBox<Housemates> personComboBox;

    /**
     * Konstruktor klasy BudgetAppGUI.
     * Tworzy on główne okno programu, w którym znajdują się wszystkie jego komponenty.
     * Znajdują się w nim definicje poszczególnych komponentów, tabel, panelów, layoutów.
     * Posiada także listenery opisujące zachowania danych przycisków po kliknięciu na nie.
     */
    public BudgetAppGUI() {
        budgetManager = new BudgetManager();

        setTitle("Budzet domowy");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabela wyswietlajaca transakcje
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Kwota", "Transakcja", "Data", "Osoba"});
        transactionsTable = new JTable(tableModel);
        transactionsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Tworzenie pol tekstowych
        amountField = new JTextField(10);
        titleField = new JTextField(10);
        dateField = new JTextField(10);
 //       memberIdField = new JTextField(10);

        // Inicjalizacja listy rozwijanej z domownikami
        personComboBox = new JComboBox<Housemates>();


        //JComboBox<String> memberIdField = new JComboBox<>();

        //pola tekstowe z imieniem i nazwiskiem do dodawania domowników
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);


        // Panel na ktorym przechowywane beda pola tekstowe
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Kwota:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Tytul:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Data:"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Czlonek:"));

//        inputPanel.add(memberIdField);
        inputPanel.add(personComboBox);


        // Dodawanie ComboBox z osobami do panelu formularza
        ArrayList<Housemates> persons = null;
        try {
            persons = (ArrayList<Housemates>) budgetManager.getMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //personComboBox = new JComboBox<>();
        for (Housemates person : persons) {
            personComboBox.addItem(person);
        }
//
//        personComboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Housemates) {
//                    Housemates person = (Housemates) value;
//                    setText(person.toString());
//                }
//                return this;
//            }
//        });


        JPanel housematesPanel = new JPanel();
        housematesPanel.add(new JLabel("Imie:"));
        housematesPanel.add(firstNameField);
        housematesPanel.add(new JLabel("Nazwisko:"));
        housematesPanel.add(lastNameField);


        //lista czlonkow rodziny
//        JTable membersTable = new JTable();
//        JScrollPane membersScrollPane = new JScrollPane(membersTable);
//        DefaultTableModel membersModel = new DefaultTableModel();
//        membersTable.setModel(membersModel);


        JButton totalButton = new JButton("Suma");
        /**
         * Działanie przycisku totalButton odpowiedzialnego za wyświetlanie sumy jaka znajduje się w całym budżecie
         */
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float total = budgetManager.getTotalAmount();
                JOptionPane.showMessageDialog(null, "W budzecie domowym znajduje sie: " + total + " zlotych");
            }
        });

        // Przycisk odpowiedzialny za odswiezanie tabeli z transakcjami
        JButton refreshButton = new JButton("Odswiez");
        /**
         * Działanie przycisku refreshButton, który odświeża tabelę transakcji
         */
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTransactionsTable();
            }
        });

        List<Housemates> members = null;

//        try {
//            members = budgetManager.getMembers();
//        } catch (SQLException e) {
//           e.printStackTrace();
//           JOptionPane.showMessageDialog(null, "Error retrieving members list: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        for (Housemates m : members) {
//            personComboBox.addItem(members);
//        }


        JButton addButton = new JButton("Dodaj transakcje");
        /**
         * Działanie przycisku addButton, dzięki któremu możemy dodawać transakcje do bazy
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float amount = Float.parseFloat(amountField.getText());
                String title = titleField.getText();
                String date = dateField.getText();
                //int memberId = Integer.parseInt(memberIdField.getText());

//                String selectedMemberName = (String) personComboBox.getSelectedItem();
//                int memberId = budgetManager.getIdByName(selectedMemberName);

                Housemates sm = (Housemates) personComboBox.getSelectedItem();
                int memberId = sm.getIdOsoby();

                budgetManager.createTransaction(memberId, amount, title, date);
                refreshTransactionsTable();
            }
        });

        JButton deleteButton = new JButton("Usun transakcje");
        /**
         * Działanie przycisku deleteButton, który pozwala na usuwanie wybranych rekordów (transakcji)
         */
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = transactionsTable.getSelectedRows();
                for (int row : selectedRows) {
                    int id = (int) transactionsTable.getValueAt(row, 0);
                    budgetManager.deleteTransaction(id);
                }
                refreshTransactionsTable();
            }
        });


        JButton AddMemberButton = new JButton("Dodaj czlonka rodziny");
        /**
         * Działanie przycisku AddMemberButton, pozwalającego na dodawanie członków rodziny
         */
        AddMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                budgetManager.createMember(firstName, lastName);
                //refreshMembersTable();
            }
        });

/*        JButton deleteMemberButton = new JButton("Delete Member");
        deleteMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = membersTable.getSelectedRows();
                for (int row : selectedRows) {
                    int id = (int) membersTable.getValueAt(row, 0);
                    budgetManager.deleteMember(id);
                }
                //refreshMembersTable();
            }
        });
*/

        JButton statisticsButton = new JButton("Statystyki miesieczne");
        /**
         * Przycisk statystyki otwierający nowe okno, które pozwala obliczać zestawienia miesięczne.
         */
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame statisticsFrame = new JFrame("Dane miesieczne");
                statisticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statisticsFrame.setSize(400, 300);
                statisticsFrame.setLocationRelativeTo(null);
                statisticsFrame.setVisible(true);

                // Panel do dodawania lat i miesiecy
                JPanel inputPanel = new JPanel();
                inputPanel.add(new JLabel("Rok:"));
                JTextField yearField = new JTextField(4);
                inputPanel.add(yearField);
                inputPanel.add(new JLabel("Miesiac:"));
                JTextField monthField = new JTextField(2);
                inputPanel.add(monthField);
                statisticsFrame.add(inputPanel, BorderLayout.NORTH);

                // Przycisk liczacy statystyke miesieczna
                JButton calculateButton = new JButton("Wyswietl");

                /**
                 * Ustawienie przycisku liczącego statystykę miesięczną.
                 * Opcja niedostępna w tej wersji programu.
                 */
                calculateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String yearInput = yearField.getText();
                        String monthInput = monthField.getText();
                        // obliczanie statystyki z danych podanych przez uzytkownika

                        //statisticsTextArea = BudgetAppGUI.getStatisticsTextArea();

                        if (!isValidYear(yearInput) || !isValidMonth(monthInput)) {
                            JOptionPane.showMessageDialog(statisticsFrame, "Niepoprawny rok oraz miesiac.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                      //  try {
                            // Pobieranie transakcji z danego roku i miesiaca
                            List<Transaction> transactions = budgetManager.getTransactions(yearInput, monthInput);
                            StringBuilder statistics = new StringBuilder();
                            statistics.append("Wplywy i wydatki podczas: " + monthInput + "/" + yearInput + ":\n");
                            for (Transaction t : transactions) {
                                statistics.append(t.getTitle() + " - " + t.getAmount() + " - " + t.getTransactionDate() + "\n");
                            }
                            // Wyswietlanie statystyk
                            //statisticsTextArea.setText(statistics.toString());
                        //} catch (SQLException ex) {
                       //     JOptionPane.showMessageDialog(statisticsFrame, "Error retrieving transactions from the database.", "Error", JOptionPane.ERROR_MESSAGE);
                        //}
                    }
                });


                inputPanel.add(calculateButton);

                // Tekst wyswietlany
                JTextArea statisticsTextArea = new JTextArea();
                statisticsTextArea.setEditable(false);
                JScrollPane statisticsScrollPane = new JScrollPane(statisticsTextArea);
                statisticsFrame.add(statisticsScrollPane, BorderLayout.CENTER);
            }
        });

        /**
         * Dodawanie do określonych panelów danych przycisków
         */

        inputPanel.add(addButton);

        housematesPanel.add(AddMemberButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(statisticsButton);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(housematesPanel);
        bottomPanel.add(AddMemberButton);
        bottomPanel.add(buttonPanel);
//        bottomPanel.add(membersScrollPane);


        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

//        add(buttonPanel, BorderLayout.SOUTH);

//        add(housematesPanel, BorderLayout.SOUTH);

        setVisible(true);
        refreshTransactionsTable();
    }

    /**
     * Metoda pozwalająca odświeżać tabelę transakcji
     */
    private void refreshTransactionsTable() {
        try {
            // Wyczysc dane z tabeli
            tableModel.setRowCount(0);

            //pobierz aktualne dane
            ResultSet rs = budgetManager.getTransactions();
            while (rs.next()) {
                int id = rs.getInt("id_transakcje");
                float amount = rs.getFloat("kwota");
                String title = rs.getString("tytul_transakcji");
                String date = rs.getString("data_transakcji");
                int id_person = rs.getInt("id_osoby");
                String name = budgetManager.getName(id_person);
                // Add the data to the table
                tableModel.addRow(new Object[]{id, amount, title, date, name});
                //model.addRow(new Object[]{t.getId(), t.getAmount(), t.getTitle(), t.getDate(), budgetManager.getName(t.getMemberId())});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metoda pozwalająca odświeżać tabelę domowników
     */
    public void refreshMembersTable() {
        try {
            JTable membersTable = new JTable();
            List<Housemates> members = budgetManager.getMembers();
            DefaultTableModel model = (DefaultTableModel) membersTable.getModel();
            model.setRowCount(0);
            for (Housemates m : members) {
                model.addRow(new Object[]{m.getIdOsoby(), m.getImie(), m.getNazwisko()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
}
