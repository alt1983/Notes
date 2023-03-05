import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.*;
import java.sql.*;
import java.io.*;

public class DbNewNote {

    public DbNewNote(String id, String text) {

        Connection c;
        Statement stmt;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pwd");
            c.setAutoCommit(false);
            String sql = "INSERT INTO NOTES (ID, NOTE) VALUES ('" + id + "','" + text + "');";

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

}