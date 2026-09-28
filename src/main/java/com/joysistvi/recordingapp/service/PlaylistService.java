package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistById(int id);
    boolean createPlaylist(String name);
    boolean updatePlaylist(int id, String name);
    boolean archivePlaylist(int id);
    boolean deletePlaylist(int id);
}
