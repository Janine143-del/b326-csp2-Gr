package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.PlaylistSongController;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;
import java.util.Scanner;

public class PlaylistSongView {

    private final PlaylistSongController playlistSongController;
    private final Scanner scanner;

    public PlaylistSongView(PlaylistSongController playlistSongController, Scanner scanner) {
        this.playlistSongController = playlistSongController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();
            choice = promptChoice();

            switch (choice) {
                case 1 -> addSongToPlaylist();
                case 2 -> removeSongFromPlaylist();
                case 3 -> viewSongsInPlaylist();

                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n----- Playlist Song Management -----");
        System.out.println("1. Add Song to Playlist");
        System.out.println("2. Remove Song from Playlist");
        System.out.println("3. View Songs in Playlist");
        System.out.println("0. Back");
    }

    public int promptChoice() {
        System.out.print("Choice: ");
        return readInt();
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (RuntimeException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private void addSongToPlaylist() {
        System.out.println("\n----- Add Song to Playlist -----");
        System.out.print("Playlist ID: ");
        int playlistId = readInt();
        System.out.print("Song ID: ");
        int songId = readInt();

        boolean isSuccess = playlistSongController.handleAddSongToPlaylist(playlistId, songId);
        System.out.println(isSuccess ? "Song added to playlist successfully." : "Failed to add song.");
    }

    private void removeSongFromPlaylist() {
        System.out.println("\n----- Remove Song from Playlist -----");
        System.out.print("Playlist ID: ");
        int playlistId = readInt();
        System.out.print("Song ID to remove: ");
        int songId = readInt();

        boolean isSuccess = playlistSongController.handleRemoveSongFromPlaylist(playlistId, songId);
        System.out.println(isSuccess ? "Song removed from playlist successfully." : "Failed to remove song.");
    }

    private void viewSongsInPlaylist() {
        System.out.println("\n----- View Songs in Playlist -----");
        System.out.print("Playlist ID: ");
        int playlistId = readInt();

        List<Song> songs = playlistSongController.handleGetSongsInPlaylist(playlistId);
        printSongs(songs);
    }

    private void printSongs(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs found in this playlist.");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(25) + "+" + "-".repeat(12) + "+";
        System.out.println(border);
        System.out.printf("| %-4s | %-23s | %-10s |%n", "ID", "Title", "Artist ID");
        System.out.println(border);

        for (Song song : songs) {
            System.out.printf("| %-4s | %-23s | %-10s |%n", song.getId(), song.getTitle(), song.getArtistId());
        }

        System.out.println(border);
    }
}
