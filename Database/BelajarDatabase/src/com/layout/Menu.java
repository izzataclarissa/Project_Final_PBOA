package com.layout;

import java.util.Scanner;

public class Menu {
    static Scanner inp = new Scanner(System.in);
    static int pilihan;

    public static void showMenu() {
        System.out.println("-".repeat(40));
        System.out.println("-SELAMAT DATANG-");
        System.out.println("-".repeat(40));
        System.out.println("1. Read Data");
        System.out.println("2. Insert Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("5. Keluar");

        while (true) {
            System.out.println("-".repeat(40));
            System.out.print("PILIH : ");
            pilihan = inp.nextInt();
            System.out.println("-".repeat(40));

            switch (pilihan) {
                case 1:
                    ReadData.readData();
                    break;
                case 2:
                    InsertData.insertData();
                    break;
                case 3:
                    EditData.editData();
                    break;
                case 4:
                    DeleteData.deleteData();
                    break;
                case 5:
                    System.out.println("Terima Kasih");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan Tidak Tersedia");
                    break;
            }
        }
    }
}
