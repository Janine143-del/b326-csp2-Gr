package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepoImpl implements PlaylistRepo {

    private final DbConnection dbConnection;

    public PlaylistRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlists WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            while (result.next()) {
                // Ayusin ang pagkakasunod: ID muna (int) tapos Name (String)
                playlists.add(new Playlist(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Get All Playlists Error: " + e.getMessage());
        }

        return playlists;
    }

    @Override
    public Playlist getPlaylistById(int id) {
        String query = "SELECT * FROM playlists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            try (ResultSet res = prep.executeQuery()) {
                if (res.next()) {
                    return new Playlist(
                            res.getInt("id"),
                            res.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Get Playlist By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {
        String query = "INSERT INTO playlists (name) VALUES (?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Create Playlist Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        String query = "UPDATE playlists SET name = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());
            prep.setInt(2, playlist.getId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Update Playlist Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean archivePlaylist(int id) {
        String query = "UPDATE playlists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Archive Playlist Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePlaylist(int id) {
        String query = "DELETE FROM playlists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Delete Playlist Error: " + e.getMessage());
        }
        return false;
    }
}
