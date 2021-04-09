package com.drgnman.management_server_gradle.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDBConnector {
    private Connection con = null;

    public Connection dbConnection(String address, String user, String pass) {
        try {
            con = DriverManager.getConnection(address, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
}
