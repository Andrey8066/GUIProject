package com.quizlet;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    Connection connection;
    Statement statement;

    public Database(String jdbcUrl, String username, String password) throws SQLException{
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        this.statement = this.connection.createStatement(); 
        
    }

    public ArrayList<String[]> getAll(String table) throws SQLException{
        ArrayList <String[]> lines = new ArrayList<>();
        ResultSet r = this.statement.executeQuery("SELECT * FROM " + table);
        
        while (r.next()) {
            String[] row = new String[r.getMetaData().getColumnCount()];
            System.out.println(r.getMetaData().getColumnCount());
            for (int i = 0; i < r.getMetaData().getColumnCount(); i++)
            {
                row[i] = r.getString(i+1);
            }
            lines.add(row);
            
        }
        return lines;
    }

    public ArrayList<String[]> getAllByParam(String table, String param, String value) throws SQLException{
        ArrayList <String[]> lines = new ArrayList<>();
        ResultSet r = this.statement.executeQuery("SELECT * FROM " + table + " WHERE " + param + "=" + value);
        
        while (r.next()) {
            String[] row = new String[r.getMetaData().getColumnCount()];
            
            for (int i = 0; i < r.getMetaData().getColumnCount(); i++)
            {
                row[i] = r.getString(i);
            }
            
        }
        return lines;
    }

   
}
