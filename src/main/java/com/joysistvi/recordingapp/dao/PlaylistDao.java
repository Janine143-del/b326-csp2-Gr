package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;

import java.sql.*;

public class PlaylistDao {

    private final DbConnection dbConnection;

    public PlaylistDao(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void readAllPlaylists() {
        String query = "SELECT * FROM playlists WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            System.out.println("+-------+----------------------+");
            System.out.printf("| %-5s | %-20s |%n", "ID", "Playlist Name");
            System.out.println("+-------+----------------------+");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");

                System.out.printf("| %-5d | %-20s |%n", id, name);
            }

            System.out.println("+-------+----------------------+");

        } catch (SQLException e) {
            System.err.println("Get All Playlists: " + e.getMessage());
        }
    }

    public void createPlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Playlist name is required.");
            return;
        }

        String query = "INSERT INTO playlists (name) VALUES (?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Playlist \"" + name + "\" created successfully.\n" : "Failed to create playlist.");
            readAllPlaylists();

        } catch (SQLException e) {
            System.err.println("Create Playlist: " + e.getMessage());
        }
    }

    public void updatePlaylist(String name, int id) {
        if (id <= 0) {
            System.out.println("Invalid playlist ID.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Playlist name is required.");
            return;
        }

        String query = "UPDATE playlists SET name = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Playlist updated successfully.\n" : "Failed to update playlist.");
            readAllPlaylists();

        } catch (SQLException e) {
            System.err.println("Update Playlist: " + e.getMessage());
        }
    }

    public void archivePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Invalid playlist ID.");
            return;
        }

        String query = "UPDATE playlists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Playlist " + id + " archived successfully.\n" : "Failed to archive playlist.");
            readAllPlaylists();

        } catch (SQLException e) {
            System.err.println("Archive Playlist: " + e.getMessage());
        }
    }

    public void deletePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Invalid playlist ID.");
            return;
        }

        String query = "DELETE FROM playlists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Playlist " + id + " deleted successfully.\n" : "Failed to delete playlist.");
            readAllPlaylists();

        } catch (SQLException e) {
            System.err.println("Delete Playlist: " + e.getMessage());
        }
    }
}
