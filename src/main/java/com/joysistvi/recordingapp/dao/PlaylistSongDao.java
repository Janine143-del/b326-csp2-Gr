package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;

import java.sql.*;

public class PlaylistSongDao {

    private final DbConnection dbConnection;

    public PlaylistSongDao(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Add specific song on playlist
    public void addSongToPlaylist(int playlistId, int songId) {
        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Invalid playlist ID or song ID.");
            return;
        }

        String query = "INSERT INTO playlist_songs (playlist_id, song_id) VALUES (?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            prep.setInt(2, songId);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Song successfully added to playlist.\n" : "Failed to add song to playlist.");

        } catch (SQLException e) {
            System.err.println("Add Song To Playlist: " + e.getMessage());
        }
    }

    // Tingnan ang mga kanta na nasa loob ng isang playlist
    public void readSongsInPlaylist(int playlistId) {
        if (playlistId <= 0) {
            System.out.println("Invalid playlist ID.");
            return;
        }

        String query = "SELECT s.id, s.title FROM songs s " +
                "JOIN playlist_songs ps ON s.id = ps.song_id " +
                "WHERE ps.playlist_id = ? AND s.is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            ResultSet res = prep.executeQuery();

            System.out.println("+-------+----------------------+");
            System.out.printf("| %-5s | %-20s |%n", "ID", "Song Title");
            System.out.println("+-------+----------------------+");

            while (res.next()) {
                System.out.printf("| %-5d | %-20s |%n", res.getInt("id"), res.getString("title"));
            }

            System.out.println("+-------+----------------------+");

        } catch (SQLException e) {
            System.err.println("Read Songs In Playlist: " + e.getMessage());
        }
    }

    // Remove one song on playlist
    public void removeSongFromPlaylist(int playlistId, int songId) {
        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Invalid playlist ID or song ID.");
            return;
        }

        String query = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, playlistId);
            prep.setInt(2, songId);

            int rows = prep.executeUpdate();
            System.out.println(rows > 0 ? "Song successfully removed from playlist.\n" : "Failed to remove song from playlist.");

        } catch (SQLException e) {
            System.err.println("Remove Song From Playlist: " + e.getMessage());
        }
    }
}