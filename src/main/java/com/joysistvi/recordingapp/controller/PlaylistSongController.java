package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.PlaylistSongService;

import java.util.List;

public class PlaylistSongController {

    private final PlaylistSongService playlistSongService;

    public PlaylistSongController(PlaylistSongService playlistSongService) {
        this.playlistSongService = playlistSongService;
    }

    public boolean handleAddSongToPlaylist(int playlistId, int songId) {
        return playlistSongService.addSongToPlaylist(playlistId, songId);
    }

    public boolean handleRemoveSongFromPlaylist(int playlistId, int songId) {
        return playlistSongService.removeSongFromPlaylist(playlistId, songId);
    }

    public List<Song> handleGetSongsInPlaylist(int playlistId) {
        return playlistSongService.getSongsInPlaylist(playlistId);
    }
}
