package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    String pinnumber;

    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement");

        setLayout(null);

        JLabel bank = new JLabel("Reserve Bank");
        bank.setBounds(150, 20, 200, 30);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 300, 30);
        add(card);

        JTextArea mini = new JTextArea();
        mini.setBounds(20, 100, 360, 400);
        mini.setEditable(false);
        mini.setFont(new Font("Arial", Font.PLAIN, 14));
        add(mini);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                String cardNumber = rs.getString("cardnumber");
                if (cardNumber != null && cardNumber.length() >= 16) {
                    card.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
                }
            }
        } catch (Exception e) {
            System.out.println("Card number error: " + e);
        }

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            StringBuilder statement = new StringBuilder();
            while (rs.next()) {
                statement.append("Date: ").append(rs.getString("date")).append("\n")
                        .append("Type: ").append(rs.getString("type")).append("\n")
                        .append("Amount: Rs ").append(rs.getString("amount")).append("\n\n");
            }
            mini.setText(statement.toString());
        } catch (Exception e) {
            System.out.println("Statement error: " + e);
        }

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement(""); // Replace with a valid PIN from your DB
    }
}
