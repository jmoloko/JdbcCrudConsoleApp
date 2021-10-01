package com.milk.consoleapp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Jack Milk
 */
public class DBConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/jdbc_crud";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection DATABASE_CONNECTION;;

    private DBConnector() {}

    public static Connection getConnection() {
        try {
            if (DATABASE_CONNECTION == null || DATABASE_CONNECTION.isClosed()) {
                try {
                    DATABASE_CONNECTION = DriverManager.getConnection(
                            DATABASE_URL,
                            USER,
                            PASSWORD
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DATABASE_CONNECTION;
    }
}
