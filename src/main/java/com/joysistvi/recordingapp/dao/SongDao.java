package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;

import java.sql.*;

public class SongDao {

    // Composition
    private final DbConnection dbConnection;

    // Constructor Injection
    public SongDao(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // CRUD Operations

    public void readAllSongs() {
        String query = "SELECT * FROM songs WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            System.out.println("+-------+----------------------+------------+");
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Title", "Artist ID");
            System.out.println("+-------+----------------------+------------+");

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                int artistId = result.getInt("artist_id");

                System.out.printf("| %-5d | %-20s | %-10d |%n", id, title, artistId);
            }

            System.out.println("+-------+----------------------+------------+");

        } catch (SQLException e) {
            System.err.println("Get All Songs: " + e.getMessage());
        }
    }

    public void createSong(String title, int artistId) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Song title is required.");
            return;
        }

        if (artistId <= 0) {
            System.out.println("Invalid artist ID.");
            return;
        }

        String query = "INSERT INTO songs (title, artist_id) VALUES (?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, title);
            prep.setInt(2, artistId);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Song \"" + title + "\" added successfully.\n" : "Failed to add song.");
            readAllSongs();

        } catch (SQLException e) {
            System.err.println("Create Song: " + e.getMessage());
        }
    }

    public void updateSong(String title, int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return;
        }

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Song title is required.");
            return;
        }

        String query = "UPDATE songs SET title = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, title);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Song updated successfully.\n" : "Failed to update song.");
            readAllSongs();

        } catch (SQLException e) {
            System.err.println("Update Song: " + e.getMessage());
        }
    }

    public void archiveSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return;
        }

        String query = "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Song " + id + " archived successfully.\n" : "Failed to archive song.");
            readAllSongs();

        } catch (SQLException e) {
            System.err.println("Archive Song: " + e.getMessage());
        }
    }

    public void restoreSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return;
        }

        String query = "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Song " + id + " restored successfully.\n" : "Failed to restore song.");
            readAllSongs();

        } catch (SQLException e) {
            System.err.println("Restore Song: " + e.getMessage());
        }
    }

    public void deleteSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return;
        }

        String query = "DELETE FROM songs WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Song " + id + " deleted successfully.\n" : "Failed to delete song.");
            readAllSongs();

        } catch (SQLException e) {
            System.err.println("Delete Song: " + e.getMessage());
        }
    }

    public void searchSong(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        String query = "SELECT * FROM songs WHERE title LIKE ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword.trim() + "%");
            ResultSet res = prep.executeQuery();

            System.out.println("+-------+----------------------+------------+");
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Title", "Artist ID");
            System.out.println("+-------+----------------------+------------+");

            while (res.next()) {
                System.out.printf("| %-5d | %-20s | %-10d |%n",
                        res.getInt("id"),
                        res.getString("title"),
                        res.getInt("artist_id"));
            }

            System.out.println("+-------+----------------------+------------+");

        } catch (SQLException e) {
            System.err.println("Search Song: " + e.getMessage());
        }
    }
}