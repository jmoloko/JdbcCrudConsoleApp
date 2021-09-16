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

    private static DBConnector instance;

    private DBConnector() {}

    public static DBConnector getConnector() {
        if (instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    private static Connection DB_CONNECT;

    public Connection getConnect() {
        try {
            if (DB_CONNECT == null || DB_CONNECT.isClosed()) {
                try {
                    DB_CONNECT = DriverManager.getConnection(
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
        return DB_CONNECT;
    }
}
