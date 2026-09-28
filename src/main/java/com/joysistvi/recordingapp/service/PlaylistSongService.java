package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import java.util.List;

public interface PlaylistSongService {
    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);
    List<Song> getSongsInPlaylist(int playlistId);
}
