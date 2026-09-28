package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final Scanner scanner;

    public SongView(SongController songController, Scanner scanner) {
        this.songController = songController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();
            choice = promptChoice();

            switch (choice) {
                case 1 -> viewAllSongs();
                case 2 -> searchSong();
                case 3 -> addSong();
                case 4 -> updateSong();
                case 5 -> archiveSong();
                case 6 -> restoreSong();
                case 7 -> deleteSong();

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
        System.out.println("\n----- Song Management -----");
        System.out.println("1. View All Songs");
        System.out.println("2. Search Song");
        System.out.println("3. Add Song");
        System.out.println("4. Update Song");
        System.out.println("5. Archive Song");
        System.out.println("6. Restore Song");
        System.out.println("7. Delete Song");
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

    private void viewAllSongs() {
        System.out.println("\n----- View All Songs -----");
        List<Song> songs = songController.handleViewAllSongs();
        printSongs(songs);
    }

    private void searchSong() {
        System.out.println("\n----- Search Songs -----");
        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine();
        List<Song> songs = songController.handleSearchSong(keyword);
        printSongs(songs);
    }

    private void addSong() {
        System.out.println("\n----- Add Song -----");
        System.out.print("Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Artist ID: ");
        int artistId = readInt();

        Song song = new Song(title, artistId);
        boolean isSuccess = songController.handleCreateSong(song);
        System.out.println(isSuccess ? "Song added successfully." : "Failed to add song.");

        if (isSuccess) {
            System.out.println();
            viewAllSongs();
        }
    }

    private void updateSong() {
        System.out.println("\n----- Update Song -----");
        viewAllSongs();

        System.out.print("Song ID to update: ");
        int id = readInt();

        Song current = songController.handleGetSongById(id);
        if (current == null) {
            System.out.println("No song found with ID " + id + ".");
            return;
        }

        System.out.print("New Title [" + current.getTitle() + "] (press Enter to keep current): ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            title = current.getTitle();
        }

        System.out.print("New Artist ID [" + current.getArtistId() + "] (press -1 to keep current): ");
        int artistId = readInt();
        if (artistId == -1) {
            artistId = current.getArtistId();
        }

        Song song = new Song(id, title, artistId);
        boolean isSuccess = songController.handleUpdateSong(song);
        System.out.println(isSuccess ? "Song updated successfully." : "Failed to update song.");

        if (isSuccess) {
            System.out.println();
            viewAllSongs();
        }
    }

    private void archiveSong() {
        System.out.println("\n----- Archive Song -----");
        System.out.print("Song ID to archive: ");
        int id = readInt();
        boolean isSuccess = songController.handleArchiveSong(id);
        System.out.println(isSuccess ? "Song archived successfully." : "Failed to archive song.");
    }

    private void restoreSong() {
        System.out.println("\n----- Restore Song -----");
        System.out.print("Song ID to restore: ");
        int id = readInt();
        boolean isSuccess = songController.handleRestoreSong(id);
        System.out.println(isSuccess ? "Song restored successfully." : "Failed to restore song.");
    }

    private void deleteSong() {
        System.out.println("\n----- Delete Song -----");
        System.out.print("Song ID to delete: ");
        int id = readInt();
        boolean isSuccess = songController.handleDeleteSong(id);
        System.out.println(isSuccess ? "Song deleted successfully." : "Failed to delete song.");
    }

    public void printSongs(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
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
