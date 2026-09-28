package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.cliview.*;
import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.*;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnection = new DbConnection();

        // --- 1. Repositories Wiring ---
        ArtistRepo artistRepo = new ArtistRepoImpl(dbConnection);
        SongRepo songRepo = new SongRepoImpl(dbConnection);
        AlbumRepo albumRepo = new AlbumRepoImpl(dbConnection);
        PlaylistRepo playlistRepo = new PlaylistRepoImpl(dbConnection);
        PlaylistSongRepo playlistSongRepo = new PlaylistSongRepoImpl(dbConnection);

        // --- 2. Services Wiring ---
        ArtistService artistService = new ArtistServiceImpl(artistRepo);
        SongService songService = new SongServiceImpl(songRepo);
        AlbumService albumService = new AlbumServiceImpl(albumRepo);
        PlaylistService playlistService = new PlaylistServiceImpl(playlistRepo);
        PlaylistSongService playlistSongService = new PlaylistSongServiceImpl(playlistSongRepo);

        // --- 3. Controllers Wiring ---
        ArtistController artistController = new ArtistController(artistService);
        SongController songController = new SongController(songService);
        AlbumController albumController = new AlbumController(albumService);
        PlaylistController playlistController = new PlaylistController(playlistService);
        PlaylistSongController playlistSongController = new PlaylistSongController(playlistSongService);

        // --- 4. Views Wiring ---
        ArtistView artistView = new ArtistView(artistController, scanner);
        SongView songView = new SongView(songController, scanner);
        AlbumView albumView = new AlbumView(albumController, scanner);
        PlaylistView playlistView = new PlaylistView(playlistController, scanner);
        PlaylistSongView playlistSongView = new PlaylistSongView(playlistSongController, scanner);

        // --- 5. Main Menu Loop ---
        int choice;
        do {
            System.out.println("\n========================================");
            System.out.println("      RECORDING STUDIO APPLICATION      ");
            System.out.println("========================================");
            System.out.println("1. Manage Artists");
            System.out.println("2. Manage Songs");
            System.out.println("3. Manage Albums");
            System.out.println("4. Manage Playlists");
            System.out.println("5. Manage Playlist Songs");
            System.out.println("0. Exit Application");
            System.out.print("Select menu: ");

            choice = readInt(scanner);

            switch (choice) {
                case 1 -> artistView.run();
                case 2 -> songView.run();
                case 3 -> albumView.run();
                case 4 -> playlistView.run();
                case 5 -> playlistSongView.run();
                case 0 -> System.out.println("Exiting application. Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
