package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(int id);
    boolean createAlbum(String title, int artistId);
    boolean updateAlbum(int id, String title, int artistId);
    boolean archiveAlbum(int id);
    boolean deleteAlbum(int id);
}

