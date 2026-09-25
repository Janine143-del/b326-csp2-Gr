package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ArtistRepo {
    List<Artist> getAllArtists();
    Artist readArtistById(int id);
    List<Artist> searchArtist(String keyword);
    boolean createArtist(String name);
    boolean updateArtist(String name, int id);
    boolean archiveArtist(int id);
    boolean restoreArtist(int id);
    boolean deleteArtist(int id);
    List<Artist> readAllArchivedArtists();

    default Artist getArtistById(int id) {
        String query = "SELECT * FROM artists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();

            if (res.next()) {
                return new Artist(res.getInt("id"), res.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("Get Artist By Id: " + e.getMessage());
        }

        return null;
    }
}
