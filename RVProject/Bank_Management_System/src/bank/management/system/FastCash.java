package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton r1,r2,r3,r4,r5,r6,back;
    String pinnumber;
    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdrawl/Deposit Amount ");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        r1 = new JButton("Rs 100");
        r1.setBounds(170, 350, 150, 30);
        r1.addActionListener(this);
        image.add(r1);

        r2 = new JButton("Rs 500");
        r2.setBounds(355, 350, 150, 30);
        r2.addActionListener(this);
        image.add(r2);

        r3 = new JButton("Rs 1000");
        r3.setBounds(170, 400, 150, 30);
        r3.addActionListener(this);
        image.add(r3);

        r4 = new JButton("Rs 2000");
        r4.setBounds(355, 400, 150, 30);
        r4.addActionListener(this);
        image.add(r4);

        r5 = new JButton("Rs 5000");
        r5.setBounds(170, 450, 150, 30);
        r5.addActionListener(this);
        image.add(r5);

        r6 = new JButton("Rs 10000");
        r6.setBounds(355, 450, 150, 30);
        r6.addActionListener(this);
        image.add(r6);

        back = new JButton("Back");
        back.setBounds(355, 500, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900,900);
        setLocation(350,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else{
            String amount1 = ((JButton)ae.getSource()).getText().substring(3);
            Conn conn = new Conn();
            try{
                ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0;
                while(rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (ae.getSource() != back && balance < Integer.parseInt(amount1)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('" + pinnumber + "','" + date + "','Withdrawl','" + amount1 + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+amount1+" Amount Withdrawl Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
    public static void main(String[] args) {
        new FastCash("");
    }
}

