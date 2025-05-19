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

    public Database(String jdbcUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        this.statement = this.connection.createStatement();

    }

    public ArrayList<String[]> getAll(String table) throws SQLException {
        ArrayList<String[]> lines = new ArrayList<>();
        ResultSet r = this.statement.executeQuery("SELECT * FROM " + table);

        while (r.next()) {
            String[] row = new String[r.getMetaData().getColumnCount()];
            for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
                row[i] = r.getString(i + 1);
            }
            lines.add(row);

        }
        return lines;
    }

    public ArrayList<String[]> getAllByParam(String table, String param, String value) throws SQLException {
        ArrayList<String[]> lines = new ArrayList<>();
        ResultSet r = this.statement.executeQuery("SELECT * FROM " + table + " WHERE " + param + " = '" + value + "'");

        while (r.next()) {
            String[] row = new String[r.getMetaData().getColumnCount()];

            for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
                row[i] = r.getString(i + 1);
            }
            lines.add(row);

        }
        return lines;
    }

    public ArrayList<String[]> getDataByParam(String table, String data, String param, String value)
            throws SQLException {
        ArrayList<String[]> lines = new ArrayList<>();
        System.out.println("SELECT " + data + " FROM " + table + " WHERE " + param + "=" + value);
        ResultSet r = this.statement
                .executeQuery("SELECT " + data + " FROM " + table + " WHERE " + param + "=" + value);

        while (r.next()) {
            String[] row = new String[r.getMetaData().getColumnCount()];

            for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
                row[i] = r.getString(i + 1);
            }
            lines.add(row);
        }
        return lines;
    }

    public ArrayList<String> getDataByParamWithJoin(String table1, String table2, String join, String data,
            String param, String value) throws SQLException {
        ArrayList<String> lines = new ArrayList<>();
        ResultSet r = this.statement.executeQuery("SELECT " + table1 + "." + data + " FROM " + table1 + " JOIN "
                + table2 + " ON " + join + "  WHERE " + param + " = '" + value + "'");

        while (r.next()) {
            lines.add(r.getString(1));
        }
        return lines;
    }

    public void insertIntoDatabase(String table1, String values_names, String values) throws SQLException {
        System.out.println("INSERT INTO " + table1 + " (" + values_names + ") VALUES (" + values + ")");
        this.statement.execute("INSERT INTO " + table1 + " (" + values_names + ") VALUES (" + values + ")");
    }

    public void updateValueInDatabase(String table1, String value_name, String value, String param, String paramValue)
            throws SQLException {
        this.statement.execute(
                "UPDATE " + table1 + " SET " + value_name + " = " + value + " WHERE " + param + " = " + paramValue);
    }
}
