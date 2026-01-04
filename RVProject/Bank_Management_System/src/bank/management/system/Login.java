package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JButton login;
    JButton clear;
    JButton signup;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        this.setTitle("AUTOMATED TELLER MACHINE");
        this.setLayout((LayoutManager)null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        this.add(label);
        JLabel text = new JLabel("Welcome To ATM");
        text.setFont(new Font("Osward", 1, 38));
        text.setBounds(200, 40, 400, 40);
        this.add(text);
        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", 1, 28));
        cardno.setBounds(120, 150, 150, 30);
        this.add(cardno);
        this.cardTextField = new JTextField();
        this.cardTextField.setBounds(300, 150, 230, 30);
        this.cardTextField.setFont(new Font("Arial", 1, 14));
        this.add(this.cardTextField);
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", 1, 28));
        pin.setBounds(120, 220, 250, 30);
        this.add(pin);
        this.pinTextField = new JPasswordField();
        this.pinTextField.setBounds(300, 220, 230, 30);
        this.pinTextField.setFont(new Font("Arial", 1, 14));
        this.add(this.pinTextField);
        this.login = new JButton("LOGIN");
        this.login.setBounds(300, 300, 100, 30);
        this.login.setBackground(Color.BLACK);
        this.login.setForeground(Color.WHITE);
        this.login.addActionListener(this);
        this.add(this.login);
        this.clear = new JButton("CLEAR");
        this.clear.setBounds(430, 300, 100, 30);
        this.clear.setBackground(Color.BLACK);
        this.clear.setForeground(Color.WHITE);
        this.clear.addActionListener(this);
        this.add(this.clear);
        this.signup = new JButton("SIGN UP");
        this.signup.setBounds(300, 350, 230, 30);
        this.signup.setBackground(Color.BLACK);
        this.signup.setForeground(Color.WHITE);
        this.signup.addActionListener(this);
        this.add(this.signup);
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(800, 400);
        this.setVisible(true);
        this.setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.clear) {
            this.cardTextField.setText("");
            this.pinTextField.setText("");
        } else if (ae.getSource() == this.login) {
            Conn conn = new Conn();
            String cardnumber = this.cardTextField.getText();
            String pinnumber = this.pinTextField.getText();
            String query = "select * from login where cardnumber='" + cardnumber + "' and pin='" + pinnumber + "'";
            try{
               ResultSet rs= conn.s.executeQuery(query);
               if(rs.next()){
                   this.setVisible(false);
                   new Transactions(pinnumber).setVisible(true);
               }else{
                   JOptionPane.showMessageDialog(null, "Invalid Credentials");
               }
               conn.s.close();
               conn.c.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else if (ae.getSource() == this.signup) {
            this.setVisible(false);
            (new SignupOne()).setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}

