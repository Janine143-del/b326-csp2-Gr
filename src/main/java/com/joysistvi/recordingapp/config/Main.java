package com.joysistvi.recordingapp.config;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.dao.ArtistDao;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        ArtistDao artistDao = new ArtistDao(dbConnection);

        // Example to update
        artistDao.updateArtist("Jay R", 12);

        // Mentioned for table
        artistDao.readAllArtists();
    }
}