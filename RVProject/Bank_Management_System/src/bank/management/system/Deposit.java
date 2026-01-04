package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener{
    JTextField amount;
    JButton deposit,back,clear;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please Enter Amount To Deposit");
        text.setBounds(200, 300, 700, 35);
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        text.setForeground(Color.WHITE);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(200, 350, 250, 30);
        amount.setFont(new Font("Arial", 1, 14));
        image.add(amount);

        clear = new JButton("Clear");
        clear.setBounds(200, 390, 90, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(e -> amount.setText(""));
        image.add(clear);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 450, 150, 30);
        deposit.setBackground(Color.BLACK);
        deposit.setForeground(Color.WHITE);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 500, 150, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(350,0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit ) {
            String amount1 = amount.getText();
            Date date = new Date();

            if (amount1.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Amount");
            } else {
                Conn conn = new Conn();
                String query = "insert into bank values('" + pinnumber + "','" + date + "','Deposit','" + amount1 + "')";
                try {
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs "+amount1+" Amount Deposited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        }

    }
    public static void main(String[] args) {
        new Deposit("");
    }
}
