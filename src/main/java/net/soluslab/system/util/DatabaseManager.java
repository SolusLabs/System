package net.soluslab.system.util;

import org.checkerframework.common.aliasing.qual.Unique;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            // Create or connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:system.db");
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Create the frozen player table
    public void createFreezedPlayerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS freezed_players (\n"
                + " uuid TEXT PRIMARY KEY\n"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Freezed player table created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add a player to the frozen player table
    public void addFreezedPlayer(UUID uuid) {
        String sql = "INSERT INTO freezed_players(uuid) VALUES(?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid.toString());
            pstmt.executeUpdate();
            System.out.println("Player added: " + uuid);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get all players from the frozen player table
    public List<String> getFreezedPlayers() {
        List<String> players = new ArrayList<>();
        String sql = "SELECT uuid FROM freezed_players";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                players.add(rs.getString("uuid"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

    // Remove a player from the frozen player table
    public void removeFreezedPlayer(UUID uuid) {

        String sql = "DELETE FROM freezed_players WHERE uuid = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}