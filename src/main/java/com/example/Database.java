package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mariadb://localhost:3306/hengerdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Kapcsolódási hiba: " + e.getMessage());
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cylinders ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY, "
                   + "radius DOUBLE, "
                   + "height DOUBLE, "
                   + "surface DOUBLE)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Hiba a tábla létrehozásakor: " + e.getMessage());
        }
    }

    public static void insertCylinder(double radius, double height, double surface) {
        String sql = "INSERT INTO cylinders (radius, height, surface) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, radius);
            pstmt.setDouble(2, height);
            pstmt.setDouble(3, surface);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Hiba az adatok beszúrásakor: " + e.getMessage());
        }
    }

    public static List<Henger> getAllCylinders() {
        List<Henger> hengerek = new ArrayList<>();
        String sql = "SELECT radius, height, surface FROM cylinders";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                hengerek.add(new Henger(rs.getDouble("radius"), rs.getDouble("height"), rs.getDouble("surface")));
            }
        } catch (SQLException e) {
            System.out.println("Hiba az adatok lekérdezésekor: " + e.getMessage());
        }
        return hengerek;
    }
}
