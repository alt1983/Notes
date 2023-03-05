/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.*;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Alt
 */
public class DbNotesList {

    ArrayList<String> notesIds = new ArrayList<>();
    Boolean conf = false;

    public DbNotesList() {

        Connection c;
        Statement stmt;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pwd");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT NOTES.ID FROM NOTES;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.conf = true;
                this.notesIds.add(rs.getString("ID"));
            }
            rs.close();
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
