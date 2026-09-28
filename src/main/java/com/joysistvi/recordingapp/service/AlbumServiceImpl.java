package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.repository.AlbumRepo;

import java.util.List;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepo albumRepo;

    public AlbumServiceImpl(AlbumRepo albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepo.getAllAlbums();
    }

    @Override
    public Album getAlbumById(int id) {
        if (id <= 0) return null;
        return albumRepo.getAlbumById(id);
    }

    @Override
    public boolean createAlbum(String title, int artistId) {
        if (title == null || title.trim().isEmpty() || artistId <= 0) {
            System.out.println("Validation Error: Invalid album title or artist ID.");
            return false;
        }
        Album album = new Album(title.trim(), artistId);
        return albumRepo.createAlbum(album);
    }

    @Override
    public boolean updateAlbum(int id, String title, int artistId) {
        if (id <= 0 || title == null || title.trim().isEmpty() || artistId <= 0) {
            System.out.println("Validation Error: Invalid album details.");
            return false;
        }
        Album album = new Album(id, title.trim(), artistId);
        return albumRepo.updateAlbum(album);
    }

    @Override
    public boolean archiveAlbum(int id) {
        if (id <= 0) return false;
        return albumRepo.archiveAlbum(id);
    }

    @Override
    public boolean deleteAlbum(int id) {
        if (id <= 0) return false;
        return albumRepo.deleteAlbum(id);
    }
}
