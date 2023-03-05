import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.*;
import java.sql.*;
import java.io.*;

public class DbSaveNote {

    public DbSaveNote(String id, String text) {

        Connection c;
        Statement stmt;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pwd");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE NOTES SET NOTE = '" + text + "' WHERE (NOTES.ID = '" + id + "');";
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
