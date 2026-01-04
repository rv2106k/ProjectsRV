package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "1234");
            this.s = this.c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
