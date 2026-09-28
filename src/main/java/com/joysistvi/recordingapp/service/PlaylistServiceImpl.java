package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.repository.PlaylistRepo;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepo playlistRepo;

    public PlaylistServiceImpl(PlaylistRepo playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepo.getAllPlaylists();
    }

    @Override
    public Playlist getPlaylistById(int id) {
        if (id <= 0) return null;
        return playlistRepo.getPlaylistById(id);
    }

    @Override
    public boolean createPlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Playlist name cannot be empty.");
            return false;
        }
        Playlist playlist = new Playlist(name.trim());
        return playlistRepo.createPlaylist(playlist);
    }

    @Override
    public boolean updatePlaylist(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Invalid playlist ID or name.");
            return false;
        }
        Playlist playlist = new Playlist(id, name.trim());
        return playlistRepo.updatePlaylist(playlist);
    }

    @Override
    public boolean archivePlaylist(int id) {
        if (id <= 0) return false;
        return playlistRepo.archivePlaylist(id);
    }

    @Override
    public boolean deletePlaylist(int id) {
        if (id <= 0) return false;
        return playlistRepo.deletePlaylist(id);
    }
}
