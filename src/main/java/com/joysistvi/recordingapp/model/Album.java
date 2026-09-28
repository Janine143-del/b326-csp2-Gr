package com.joysistvi.recordingapp.model;

public class Album {
    private int id;
    private String title;
    private int artistId;

    // Constructor para sa pagkuha mula sa database (may ID)
    public Album(int id, String title, int artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    // Constructor para sa paggawa ng bagong album (wala pang ID / auto-increment)
    public Album(String title, int artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
