import java.sql.*;

public class DisplayData {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/toko";
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");

            System.out.println("\n--- Displaying Data from view_data_barang ---");
            stmt = conn.createStatement();
            String sql = "SELECT kode, nama, harga, stok, total_nilai FROM view_data_barang";
            rs = stmt.executeQuery(sql);

            // Print header
            System.out.printf("%-10s %-20s %-10s %-10s %-15s\n", "KODE", "NAMA", "HARGA", "STOK", "TOTAL NILAI");
            System.out.println("------------------------------------------------------------------");

            // Extract data from result set
            while (rs.next()) {
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                int totalNilai = rs.getInt("total_nilai");

                System.out.printf("%-10s %-20s %-10d %-10d %-15d\n", kode, nama, harga, stok, totalNilai);
            }

        } catch (SQLException se) {
            System.err.println("Database error: " + se.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.err.println("Error closing connection: " + se.getMessage());
            }
        }
    }
}