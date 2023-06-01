package com.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection getMySqlConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "quanlynhankhau";
        String userName = "root";
        String password = "";
        return getMysqlConnection(hostName, dbName, userName, password);
    }

    public static Connection getMysqlConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        return DriverManager.getConnection(connectionUrl, userName, password);
    }
}
