package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.service.AlbumService;

import java.util.List;

public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // READ
    public List<Album> handleViewAllAlbums() {
        return albumService.getAllAlbums();
    }

    public Album handleGetAlbumById(int id) {
        return albumService.getAlbumById(id);
    }

    // CREATE
    public boolean handleCreateAlbum(Album album) {
        return albumService.createAlbum(album.getTitle(), album.getArtistId());
    }

    // UPDATE
    public boolean handleUpdateAlbum(Album album) {
        return albumService.updateAlbum(album.getId(), album.getTitle(), album.getArtistId());
    }

    // ARCHIVE
    public boolean handleArchiveAlbum(int id) {
        return albumService.archiveAlbum(id);
    }

    // DELETE
    public boolean handleDeleteAlbum(int id) {
        return albumService.deleteAlbum(id);
    }
}
