/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dzaja
 */
public class DbConnectionFactory {

    private static DbConnectionFactory instance;
    private Connection conn;

    private DbConnectionFactory() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = konfiguracija.Konfiguracija.getInstanca().getProperty("url");
                String username = konfiguracija.Konfiguracija.getInstanca().getProperty("username");
                String password = konfiguracija.Konfiguracija.getInstanca().getProperty("password");
                conn = DriverManager.getConnection(url, username, password);
                conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

}
