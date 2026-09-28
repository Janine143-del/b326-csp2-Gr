package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.service.PlaylistService;

import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // READ
    public List<Playlist> handleViewAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public Playlist handleGetPlaylistById(int id) {
        return playlistService.getPlaylistById(id);
    }

    // CREATE
    public boolean handleCreatePlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist.getName());
    }

    // UPDATE
    public boolean handleUpdatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist.getId(), playlist.getName());
    }

    // ARCHIVE
    public boolean handleArchivePlaylist(int id) {
        return playlistService.archivePlaylist(id);
    }

    // DELETE
    public boolean handleDeletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }
}
