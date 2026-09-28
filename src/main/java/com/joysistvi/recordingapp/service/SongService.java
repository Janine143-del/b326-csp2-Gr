package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
    Song getSongById(int id);
    List<Song> searchSong(String keyword);
    boolean createSong(String title, int artistId);
    boolean updateSong(int id, String title, int artistId);
    boolean archiveSong(int id);
    boolean restoreSong(int id);
    boolean deleteSong(int id);
}
