

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Aplikasi Manajemen Barang ---");
            System.out.println("1. Insert Data Barang");
            System.out.println("2. Tampilkan Data Barang");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        InsertData.main(null); // Panggil main method dari InsertData
                        break;
                    case 2:
                        DisplayData.main(null); // Panggil main method dari DisplayData
                        break;
                    case 0:
                        System.out.println("Terima kasih! Aplikasi berakhir.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                choice = -1; 
            }

        } while (choice != 0);

        scanner.close();
    }
}