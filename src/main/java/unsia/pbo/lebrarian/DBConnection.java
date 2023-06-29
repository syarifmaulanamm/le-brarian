/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unsia.pbo.lebrarian;

/**
 *
 * @author Tomi
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class DBConnection {
    
    public static Connection conn;

    public static void openConnection() {
        Properties properties = new Properties();
        try {
            // Membaca file config.properties
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
        } catch (IOException e) {
            // Jika terjadi kesalahan saat membaca file
            JOptionPane.showMessageDialog(null, "Failed to read config.properties");
            e.printStackTrace();
            return;
        }

        String jdbcUrl = properties.getProperty("db.jdbcUrl");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Koneksi ke MySQL berhasil!");
        } catch (ClassNotFoundException e) {
            // Jika driver JDBC tidak ditemukan
            JOptionPane.showMessageDialog(null, "Failed to load JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            // Jika terjadi kesalahan saat koneksi ke database
            JOptionPane.showMessageDialog(null, "Failed to connect to MySQL");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}