package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;
import java.util.List;

public interface PlaylistRepo {
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistById(int id);
    boolean createPlaylist(Playlist playlist);
    boolean updatePlaylist(Playlist playlist);
    boolean archivePlaylist(int id);
    boolean deletePlaylist(int id);
}
