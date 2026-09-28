package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    // READ
    public List<Song> handleViewAllSongs() {
        return songService.getAllSongs();
    }

    public Song handleGetSongById(int id) {
        return songService.getSongById(id);
    }

    public List<Song> handleSearchSong(String keyword) {
        return songService.searchSong(keyword);
    }

    // CREATE
    public boolean handleCreateSong(Song song) {
        return songService.createSong(song.getTitle(), song.getArtistId());
    }

    // UPDATE
    public boolean handleUpdateSong(Song song) {
        return songService.updateSong(song.getId(), song.getTitle(), song.getArtistId());
    }

    // ARCHIVE / RESTORE
    public boolean handleArchiveSong(int id) {
        return songService.archiveSong(id);
    }

    public boolean handleRestoreSong(int id) {
        return songService.restoreSong(id);
    }

    // DELETE
    public boolean handleDeleteSong(int id) {
        return songService.deleteSong(id);
    }
}
