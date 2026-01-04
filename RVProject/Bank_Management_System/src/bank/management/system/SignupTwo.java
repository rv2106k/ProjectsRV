//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignupTwo extends JFrame implements ActionListener {
    JComboBox religion;
    JComboBox category;
    JComboBox income;
    JTextField eduField;
    JTextField quaField;
    JTextField occField;
    JTextField aadharField;
    JTextField panField;
    JRadioButton syes;
    JRadioButton sno;
    JRadioButton eyes;
    JRadioButton eno;
    JButton next;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        this.setLayout((LayoutManager)null);
        this.setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        JLabel ads = new JLabel("PAGE 2: ADDITIONAL DETAILS");
        ads.setFont(new Font("Raleway", 1, 22));
        ads.setBounds(250, 60, 400, 30);
        this.add(ads);

        //Religion
        JLabel rel = new JLabel("Religion:");
        rel.setFont(new Font("Raleway", 1, 20));
        rel.setBounds(100, 100, 200, 30);
        this.add(rel);
        String[] valReligion = new String[]{"Choose", "Hindu", "Muslim", "Sikh", "Christian", "Other"};
        this.religion = new JComboBox(valReligion);
        this.religion.setBounds(300, 100, 400, 30);
        this.religion.setBackground(Color.WHITE);
        this.add(this.religion);

        //Category
        JLabel cato = new JLabel("Category:");
        cato.setFont(new Font("Raleway", 1, 20));
        cato.setBounds(100, 140, 200, 30);
        this.add(cato);
        String[] valcategory = new String[]{"Choose", "General", "OBC", "SC", "ST", "Other"};
        this.category = new JComboBox(valcategory);
        this.category.setBounds(300, 140, 400, 30);
        this.category.setBackground(Color.WHITE);
        this.add(this.category);

        //Income
        JLabel inco = new JLabel("Income:");
        inco.setFont(new Font("Raleway", 1, 20));
        inco.setBounds(100, 180, 200, 30);
        this.add(inco);
        String[] valincome = new String[]{"Choose", "Null", "Less than ₹1.25 lakh", "Between ₹1.25 lakh and ₹5 lakh", "Between ₹5 lakh and ₹15 lakh", "Between ₹5 lakh and ₹30 lakh", "more than ₹30 lakh"};
        this.income = new JComboBox(valincome);
        this.income.setBounds(300, 180, 400, 30);
        this.income.setBackground(Color.WHITE);
        this.add(this.income);

        //Educational
        JLabel edu = new JLabel("Educational:");
        edu.setFont(new Font("Raleway", 1, 20));
        edu.setBounds(100, 220, 200, 30);
        this.add(edu);
        this.eduField = new JTextField();
        this.eduField.setFont(new Font("Raleway", 1, 15));
        this.eduField.setBounds(300, 220, 400, 30);
        this.add(this.eduField);

        //Qualification
        JLabel qua = new JLabel("Qualification:");
        qua.setFont(new Font("Raleway", 1, 20));
        qua.setBounds(100, 260, 200, 30);
        this.add(qua);
        this.quaField = new JTextField();
        this.quaField.setFont(new Font("Raleway", 1, 15));
        this.quaField.setBounds(300, 260, 400, 30);
        this.add(this.quaField);

        //Occupation
        JLabel occ = new JLabel("Occupation:");
        occ.setFont(new Font("Raleway", 1, 20));
        occ.setBounds(100, 300, 200, 30);
        this.add(occ);
        this.occField = new JTextField();
        this.occField.setFont(new Font("Raleway", 1, 15));
        this.occField.setBounds(300, 300, 400, 30);
        this.add(this.occField);

        //AadharCard
        JLabel aadhar = new JLabel("AadharCard No:");
        aadhar.setFont(new Font("Raleway", 1, 20));
        aadhar.setBounds(100, 340, 200, 30);
        this.add(aadhar);
        this.aadharField = new JTextField();
        this.aadharField.setFont(new Font("Raleway", 1, 15));
        this.aadharField.setBounds(300, 340, 400, 30);
        this.add(this.aadharField);

        //PanCard
        JLabel pan = new JLabel("Pancard No:");
        pan.setFont(new Font("Raleway", 1, 20));
        pan.setBounds(100, 380, 200, 30);
        this.add(pan);
        this.panField = new JTextField();
        this.panField.setFont(new Font("Raleway", 1, 15));
        this.panField.setBounds(300, 380, 400, 30);
        this.add(this.panField);

        // Senior Citizen
        JLabel seci = new JLabel("Senior Citizen:");
        seci.setFont(new Font("Raleway", Font.BOLD, 20));
        seci.setBounds(100, 420, 200, 30);
        add(seci);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 420, 100, 30);
        syes.setBackground(Color.WHITE);
        sno = new JRadioButton("No");
        sno.setBounds(450, 420, 100, 30);
        sno.setBackground(Color.WHITE);
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);
        add(syes);
        add(sno);

        // Existing Account
        JLabel exa = new JLabel("Existing Account:");
        exa.setFont(new Font("Raleway", Font.BOLD, 20));
        exa.setBounds(100, 460, 200, 30);
        add(exa);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 460, 100, 30);
        eyes.setBackground(Color.WHITE);
        eno = new JRadioButton("No");
        eno.setBounds(450, 460, 100, 30);
        eno.setBackground(Color.WHITE);
        ButtonGroup existGroup = new ButtonGroup();
        existGroup.add(eyes);
        existGroup.add(eno);
        add(eyes);
        add(eno);

        //Next Button
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
        String sreligion = (String)this.religion.getSelectedItem();
        String scategory = (String)this.category.getSelectedItem();
        String sincome = (String)this.income.getSelectedItem();
        String seniorcitizen = null;
        if (this.syes.isSelected()) {
            seniorcitizen = "Yes";
        } else if (this.sno.isSelected()) {
            seniorcitizen = "No";
        }

        String exaa = null;
        if (this.eyes.isSelected()) {
            exaa = "Yes";
        } else if (this.eno.isSelected()) {
            exaa = "No";
        }

        String sedu = this.eduField.getText();
        String squa = this.quaField.getText();
        String socc = this.occField.getText();
        String saad = this.aadharField.getText();
        String span = this.panField.getText();

        try {
            Conn c = new Conn();
            String query = "insert into signuptwo values('" + this.formno + "','" + sreligion + "','" + scategory + "','" + sincome + "','" + seniorcitizen + "','" + sedu + "','" + squa + "','" + socc + "','" + saad + "','" + span + "','" + exaa + "')";
            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}
