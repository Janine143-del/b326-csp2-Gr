package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;

import java.sql.*;

public class AlbumDao {

    private final DbConnection dbConnection;

    public AlbumDao(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void readAllAlbums() {
        String query = "SELECT * FROM albums WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            System.out.println("+-------+----------------------+------------+");
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Album Title", "Artist ID");
            System.out.println("+-------+----------------------+------------+");

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                int artistId = result.getInt("artist_id");

                System.out.printf("| %-5d | %-20s | %-10d |%n", id, title, artistId);
            }

            System.out.println("+-------+----------------------+------------+");

        } catch (SQLException e) {
            System.err.println("Get All Albums: " + e.getMessage());
        }
    }

    public void createAlbum(String title, int artistId) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Album title is required.");
            return;
        }

        if (artistId <= 0) {
            System.out.println("Invalid artist ID.");
            return;
        }

        String query = "INSERT INTO albums (title, artist_id) VALUES (?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, title);
            prep.setInt(2, artistId);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Album \"" + title + "\" added successfully.\n" : "Failed to add album.");
            readAllAlbums();

        } catch (SQLException e) {
            System.err.println("Create Album: " + e.getMessage());
        }
    }

    public void updateAlbum(String title, int id) {
        if (id <= 0) {
            System.out.println("Invalid album ID.");
            return;
        }

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Album title is required.");
            return;
        }

        String query = "UPDATE albums SET title = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, title);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Album updated successfully.\n" : "Failed to update album.");
            readAllAlbums();

        } catch (SQLException e) {
            System.err.println("Update Album: " + e.getMessage());
        }
    }

    public void archiveAlbum(int id) {
        if (id <= 0) {
            System.out.println("Invalid album ID.");
            return;
        }

        String query = "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Album " + id + " archived successfully.\n" : "Failed to archive album.");
            readAllAlbums();

        } catch (SQLException e) {
            System.err.println("Archive Album: " + e.getMessage());
        }
    }

    public void deleteAlbum(int id) {
        if (id <= 0) {
            System.out.println("Invalid album ID.");
            return;
        }

        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Album " + id + " deleted successfully.\n" : "Failed to delete album.");
            readAllAlbums();

        } catch (SQLException e) {
            System.err.println("Delete Album: " + e.getMessage());
        }
    }
}
