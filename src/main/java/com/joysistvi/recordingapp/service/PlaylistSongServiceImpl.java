package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.PlaylistSongRepo;

import java.util.List;

public class PlaylistSongServiceImpl implements PlaylistSongService {

    private final PlaylistSongRepo playlistSongRepo;

    public PlaylistSongServiceImpl(PlaylistSongRepo playlistSongRepo) {
        this.playlistSongRepo = playlistSongRepo;
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {
        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Validation Error: Invalid playlist or song ID.");
            return false;
        }
        return playlistSongRepo.addSongToPlaylist(playlistId, songId);
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Validation Error: Invalid playlist or song ID.");
            return false;
        }
        return playlistSongRepo.removeSongFromPlaylist(playlistId, songId);
    }

    @Override
    public List<Song> getSongsInPlaylist(int playlistId) {
        if (playlistId <= 0) return List.of();
        return playlistSongRepo.getSongsInPlaylist(playlistId);
    }
}
