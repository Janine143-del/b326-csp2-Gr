package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;

import java.util.List;

public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;

    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepo.getAllSongs();
    }

    @Override
    public Song getSongById(int id) {
        if (id <= 0) return null;
        return songRepo.getSongById(id);
    }

    @Override
    public List<Song> searchSong(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return List.of();
        return songRepo.searchSong(keyword.trim());
    }

    @Override
    public boolean createSong(String title, int artistId) {
        if (title == null || title.trim().isEmpty() || artistId <= 0) {
            System.out.println("Validation Error: Invalid song title or artist ID.");
            return false;
        }
        Song song = new Song(title.trim(), artistId);
        return songRepo.createSong(song);
    }

    @Override
    public boolean updateSong(int id, String title, int artistId) {
        if (id <= 0 || title == null || title.trim().isEmpty() || artistId <= 0) {
            System.out.println("Validation Error: Invalid update details.");
            return false;
        }
        Song song = new Song(id, title.trim(), artistId);
        return songRepo.updateSong(song);
    }

    @Override
    public boolean archiveSong(int id) {
        if (id <= 0) return false;
        return songRepo.archiveSong(id);
    }

    @Override
    public boolean restoreSong(int id) {
        if (id <= 0) return false;
        return songRepo.restoreSong(id);
    }

    @Override
    public boolean deleteSong(int id) {
        if (id <= 0) return false;
        return songRepo.deleteSong(id);
    }
}
