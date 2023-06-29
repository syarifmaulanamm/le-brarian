package unsia.pbo.lebrarian;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySQLConnectionTest {
    public static void main(String[] args) {
        String jdbcUrl = null;
        String username = null;
        String password = null;
        
        // Membaca nilai kredensial dari berkas properties
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            
            jdbcUrl = properties.getProperty("db.jdbcUrl");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Gagal membaca berkas konfigurasi: " + e.getMessage());
            System.exit(1);
        }
        
        try {
            // Menghubungkan ke database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Jika koneksi berhasil, cetak pesan sukses
            System.out.println("Koneksi ke MySQL berhasil!");
            
            // Membuat objek DatabaseMetaData
            DatabaseMetaData metaData = connection.getMetaData();
            
            // Mendapatkan daftar tabel
            ResultSet resultSet = metaData.getTables(null, null, null, new String[] {"TABLE"});
            
            // Mencetak daftar tabel
            System.out.println("Tabel yang tersedia:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);
            }
            
            // Menutup ResultSet
            resultSet.close();
            
            // Menutup koneksi
            connection.close();
        } catch (SQLException e) {
            // Jika terjadi kesalahan, cetak pesan kesalahan
            System.err.println("Koneksi ke MySQL gagal: " + e.getMessage());
        }
    }
}
