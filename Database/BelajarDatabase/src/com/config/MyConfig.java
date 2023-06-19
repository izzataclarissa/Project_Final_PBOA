package com.config;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class MyConfig {
    // access modifier dan final keyword
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_izzataa";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    // encapsulation
    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultset;

    // this keyword
    public static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection : Gagal");
        }
    }

    // READ
    public static void getDatabase() {
        MyConfig.connection();
        try {
            statement = connect.createStatement();
            resultset = statement.executeQuery("SELECT * FROM tb_obat ORDER BY ID DESC");

            while (resultset.next()) {
                System.out.println(
                        resultset.getString("Nama") + ": Rp." +
                        resultset.getString("Harga") + " (" +
                        resultset.getString("Jumlah") + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT
    public static void insertData() {
        String namaBaru;
        long hargaBaru;
        int jumlahBaru;

        Scanner input = new Scanner(System.in);

        System.out.print("Nama  : ");
        namaBaru = input.nextLine();

        System.out.print("Harga : ");
        hargaBaru = input.nextLong();

        System.out.print("Jumlah : ");
        jumlahBaru = input.nextInt();

        MyConfig.connection();

        try {
            PreparedStatement preparedStatement = connect
                    .prepareStatement("INSERT INTO `tb_obat` (`ID`, `Nama`, `Harga`, `Jumlah`) VALUES (NULL, ?, ?, ?)");
            preparedStatement.setString(1, namaBaru);
            preparedStatement.setLong(2, hargaBaru);
            preparedStatement.setInt(3, jumlahBaru);
            preparedStatement.executeUpdate();

            System.out.println("Data berhasil di simpan");

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDIT
    public static void editData() {
        Scanner input = new Scanner(System.in);
    
        System.out.print("ID data yang ingin diedit : ");
        int id = input.nextInt();
    
        System.out.println("Pilih opsi yang ingin diedit:");
        System.out.println("1. Nama");
        System.out.println("2. Harga");
        System.out.println("3. Jumlah");
        System.out.print("Pilihan: ");
        int pilihan = input.nextInt();
    
        switch (pilihan) {
            case 1:
                System.out.print("Nama baru: ");
                input.nextLine(); // Membersihkan \n dari masukan sebelumnya
                String namaBaru = input.nextLine();
                editNama(id, namaBaru);
                break;
            case 2:
                System.out.print("Harga baru: ");
                long hargaBaru = input.nextLong();
                editHarga(id, hargaBaru);
                break;
            case 3:
                System.out.print("Jumlah baru: ");
                int jumlahBaru = input.nextInt();
                editJumlah(id, jumlahBaru);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
    
    private static void editNama(int id, String namaBaru) {
        connection();
    
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(
                    "UPDATE `tb_obat` SET `Nama` = ? WHERE `ID` = ?");
            preparedStatement.setString(1, namaBaru);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
    
            System.out.println("Nama berhasil diedit");
    
            preparedStatement.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void editHarga(int id, long hargaBaru) {
        connection();
    
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(
                    "UPDATE `tb_obat` SET `Harga` = ? WHERE `ID` = ?");
            preparedStatement.setLong(1, hargaBaru);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
    
            System.out.println("Harga berhasil diedit");
    
            preparedStatement.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void editJumlah(int id, int jumlahBaru) {
        connection();
    
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(
                    "UPDATE `tb_obat` SET `Jumlah` = ? WHERE `ID` = ?");
            preparedStatement.setInt(1, jumlahBaru);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
    
            System.out.println("Jumlah berhasil diedit");
    
            preparedStatement.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // HAPUS
    public static void deleteData() {
        Scanner input = new Scanner(System.in);

        System.out.print("ID data yang ingin dihapus : ");
        int id = input.nextInt();

        MyConfig.connection();

        try {
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM `tb_obat` WHERE `ID` = " + id);

            System.out.println("Data berhasil dihapus");

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}