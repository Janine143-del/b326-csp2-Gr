package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDao extends DbConnection {

    private final DbConnection dbConnection;

    public ArtistDao(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createArtist(String name) {
        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Artist successfully added: " + name);

        } catch (SQLException e) {
            System.err.println("Create Artist Error: " + e.getMessage());
        }
    }

    public void readAllArtists() {
        String query = "SELECT * FROM artists";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            // Format designs
            System.out.println("+-------+----------------------+");
            System.out.printf("| %-5s | %-20s |%n", "ID", "Name");
            System.out.println("+-------+----------------------+");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");

                System.out.printf("| %-5d | %-20s |%n", id, name);
            }

            System.out.println("+-------+----------------------+");

        } catch (SQLException e) {
            System.err.println("Get All Artists Error: " + e.getMessage());
        }
    }

    public void updateArtist(String name, int id) {
        if (id <= 0) {
            System.out.println("Invalid artist ID.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Artist name is required");
            return;
        }

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Artist " + name + " updated successfully"
                    : "Failed to update artist");

        } catch (SQLException e) {
            System.out.println("Update Artist: " + e.getMessage());
        }
    }
}