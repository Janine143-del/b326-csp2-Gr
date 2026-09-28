package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumRepo {
    List<Album> getAllAlbums();
    Album getAlbumById(int id);
    boolean createAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean archiveAlbum(int id);
    boolean deleteAlbum(int id);
}
