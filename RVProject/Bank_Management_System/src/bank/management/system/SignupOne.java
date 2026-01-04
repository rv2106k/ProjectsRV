package bank.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignupOne extends JFrame implements ActionListener {
    long random;
    JTextField nameField;
    JTextField fnameField;
    JTextField mnameField;
    JTextField emailField;
    JTextField maritialField;
    JTextField addressField;
    JTextField cityField;
    JTextField stateField;
    JTextField pinField;
    JTextField phoneField;
    JTextField countryField;
    JButton next;
    JRadioButton male;
    JRadioButton female;
    JRadioButton trans;
    JDateChooser dateChooser;

    SignupOne() {
        this.setLayout((LayoutManager)null);
        Random ran = new Random();
        this.random = Math.abs(ran.nextLong() % 9000L + 1000L);
        JLabel formno = new JLabel("APPLICATION FORM NO." + this.random);
        formno.setFont(new Font("Raleway", 1, 38));
        formno.setBounds(120, 20, 600, 40);
        this.add(formno);
        JLabel pds = new JLabel("PAGE 1: PERSONAL DETAILS");
        pds.setFont(new Font("Raleway", 1, 22));
        pds.setBounds(250, 60, 400, 30);
        this.add(pds);
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", 1, 20));
        name.setBounds(100, 100, 200, 30);
        this.add(name);
        this.nameField = new JTextField();
        this.nameField.setFont(new Font("Raleway", 1, 15));
        this.nameField.setBounds(300, 100, 400, 30);
        this.add(this.nameField);
        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", 1, 20));
        fname.setBounds(100, 140, 200, 30);
        this.add(fname);
        this.fnameField = new JTextField();
        this.fnameField.setFont(new Font("Raleway", 1, 15));
        this.fnameField.setBounds(300, 140, 400, 30);
        this.add(this.fnameField);
        JLabel mname = new JLabel("Mother's Name:");
        mname.setFont(new Font("Raleway", 1, 20));
        mname.setBounds(100, 180, 200, 30);
        this.add(mname);
        this.mnameField = new JTextField();
        this.mnameField.setFont(new Font("Raleway", 1, 15));
        this.mnameField.setBounds(300, 180, 400, 30);
        this.add(this.mnameField);
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", 1, 20));
        gender.setBounds(100, 220, 200, 30);
        this.add(gender);
        this.male = new JRadioButton("Male");
        this.male.setFont(new Font("Raleway", 1, 14));
        this.male.setBounds(300, 220, 100, 20);
        this.male.setBackground(Color.WHITE);
        this.add(this.male);
        this.female = new JRadioButton("Female");
        this.female.setFont(new Font("Raleway", 1, 14));
        this.female.setBounds(450, 220, 100, 20);
        this.female.setBackground(Color.WHITE);
        this.add(this.female);
        this.trans = new JRadioButton("Transgender");
        this.trans.setFont(new Font("Raleway", 1, 14));
        this.trans.setBounds(590, 220, 200, 20);
        this.trans.setBackground(Color.WHITE);
        this.add(this.trans);
        ButtonGroup bg = new ButtonGroup();
        bg.add(this.male);
        bg.add(this.female);
        bg.add(this.trans);
        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", 1, 20));
        dob.setBounds(100, 260, 200, 30);
        this.add(dob);
        this.dateChooser = new JDateChooser();
        this.dateChooser.setBounds(300, 260, 400, 30);
        this.dateChooser.setForeground(new Color(105, 105, 105));
        this.add(this.dateChooser);
        JLabel email = new JLabel("Email Id:");
        email.setFont(new Font("Raleway", 1, 20));
        email.setBounds(100, 300, 200, 30);
        this.add(email);
        this.emailField = new JTextField();
        this.emailField.setFont(new Font("Raleway", 1, 15));
        this.emailField.setBounds(300, 300, 400, 30);
        this.add(this.emailField);
        JLabel maritalStatus = new JLabel("Marital Status:");
        maritalStatus.setFont(new Font("Raleway", 1, 20));
        maritalStatus.setBounds(100, 340, 200, 30);
        this.add(maritalStatus);
        this.maritialField = new JTextField();
        this.maritialField.setFont(new Font("Raleway", 1, 15));
        this.maritialField.setBounds(300, 340, 400, 30);
        this.add(this.maritialField);
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", 1, 20));
        address.setBounds(100, 380, 200, 30);
        this.add(address);
        this.addressField = new JTextField();
        this.addressField.setFont(new Font("Raleway", 1, 15));
        this.addressField.setBounds(300, 380, 400, 30);
        this.add(this.addressField);
        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", 1, 20));
        city.setBounds(100, 420, 200, 30);
        this.add(city);
        this.cityField = new JTextField();
        this.cityField.setFont(new Font("Raleway", 1, 15));
        this.cityField.setBounds(300, 420, 400, 30);
        this.add(this.cityField);
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", 1, 20));
        state.setBounds(100, 460, 200, 30);
        this.add(state);
        this.stateField = new JTextField();
        this.stateField.setFont(new Font("Raleway", 1, 15));
        this.stateField.setBounds(300, 460, 400, 30);
        this.add(this.stateField);
        JLabel pin = new JLabel("Pin Code:");
        pin.setFont(new Font("Raleway", 1, 20));
        pin.setBounds(100, 500, 200, 30);
        this.add(pin);
        this.pinField = new JTextField();
        this.pinField.setFont(new Font("Raleway", 1, 15));
        this.pinField.setBounds(300, 500, 400, 30);
        this.add(this.pinField);
        JLabel phone = new JLabel("Phone No:");
        phone.setFont(new Font("Raleway", 1, 20));
        phone.setBounds(100, 540, 200, 30);
        this.add(phone);
        this.phoneField = new JTextField();
        this.phoneField.setFont(new Font("Raleway", 1, 15));
        this.phoneField.setBounds(300, 540, 400, 30);
        this.add(this.phoneField);
        JLabel Country = new JLabel("Country:");
        Country.setFont(new Font("Raleway", 1, 20));
        Country.setBounds(100, 580, 200, 30);
        this.add(Country);
        this.countryField = new JTextField();
        this.countryField.setFont(new Font("Raleway", 1, 15));
        this.countryField.setBounds(300, 580, 400, 30);
        this.add(this.countryField);
        this.next = new JButton("Next");
        this.next.setBackground(Color.BLACK);
        this.next.setForeground(Color.WHITE);
        this.next.setFont(new Font("Raleway", 1, 20));
        this.next.setBounds(620, 620, 80, 30);
        this.next.addActionListener(this);
        this.add(this.next);
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(850, 800);
        this.setLocation(350, 10);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = "" + this.random;
        String name = this.nameField.getText();
        String fname = this.fnameField.getText();
        String mname = this.mnameField.getText();
        String gender = null;
        if (this.male.isSelected()) {
            gender = "Male";
        } else if (this.female.isSelected()) {
            gender = "Female";
        } else if (this.trans.isSelected()) {
            gender = "Transgender";
        }

        String dob = ((JTextField)this.dateChooser.getDateEditor().getUiComponent()).getText();
        String email = this.emailField.getText();
        String maritalStatus = this.maritialField.getText();
        String address = this.addressField.getText();
        String city = this.cityField.getText();
        String state = this.stateField.getText();
        String pin = this.pinField.getText();
        String phone = this.phoneField.getText();
        String country = this.countryField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog((Component)null, "Please enter your First Name", "Error", 0);
                return;
            }

            Conn c = new Conn();
            String query = "insert into signup values('" + formno + "','" + name + "','" + fname + "','" + mname + "','" + gender + "','" + dob + "','" + email + "','" + maritalStatus + "','" + address + "','" + city + "','" + state + "','" + pin + "','" + phone + "','" + country + "')";
            c.s.executeUpdate(query);
            this.setVisible(false);
            (new SignupTwo(formno)).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
