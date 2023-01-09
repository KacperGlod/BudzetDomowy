package budzet.proj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class MainForm {
    private JTable table1;
    private JTextField NazwaWpisz;
    private JTextField KwotaWpisz;
    private JTextArea Kwota;
    private JButton dodajButton;
    private JButton usuńButton;
    private JButton nowyButton;
    private JButton zaktualizujButton;
    private JTextArea Nazwa;

    PreparedStatement pst;

    public MainForm() {

    dodajButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String nazwa = NazwaWpisz.getText();
        String kwota = KwotaWpisz.getText();

        pst = connection.prepareStatement("INSERT INTO ");
        pst.setString(1, nazwa);
        pst.setString(2, kwota);

        int up = pst.executeUpdate();
        if(up==1){
            JOptionPane.showMessageDialog(this,"Dodano");
            NazwaWpisz.setText("");
            KwotaWpisz.setText("");
        }
        else {
            JOptionPane.showMessageDialog(this, "Nie udalo się dodac rekordu");
        }

        }
    });
}
}
