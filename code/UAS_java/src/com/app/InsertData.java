
import java.sql.*;
import java.util.Scanner;

public class InsertData {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/toko";
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        CallableStatement cstmt = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");

            System.out.print("Masukkan Kode Barang: ");
            String kode = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            String nama = scanner.nextLine();

            System.out.print("Harga (INT): ");
            int harga = Integer.parseInt(scanner.nextLine());

            System.out.print("Stok (INT): ");
            int stok = Integer.parseInt(scanner.nextLine());

            // Call the stored procedure
            String sql = "{CALL insert_barang_procedure(?, ?, ?, ?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, kode);
            cstmt.setString(2, nama);
            cstmt.setInt(3, harga);
            cstmt.setInt(4, stok);

            System.out.println("Executing insert procedure...");
            cstmt.execute(); // Ini akan memicu trigger setelah insert berhasil
            System.out.println("Data inserted successfully! Trigger for log_insert_barang also fired.");

        } catch (SQLException se) {
            System.err.println("Database error: " + se.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Input error: Please enter valid numbers for harga and stok.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            try {
                if (cstmt != null) cstmt.close();
            } catch (SQLException se2) {
                // nothing to do
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.err.println("Error closing connection: " + se.getMessage());
            }
            scanner.close();
        }
    }
}