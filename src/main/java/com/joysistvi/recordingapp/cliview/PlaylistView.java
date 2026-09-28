package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.model.Playlist;

import java.util.List;
import java.util.Scanner;

public class PlaylistView {

    private final PlaylistController playlistController;
    private final Scanner scanner;

    public PlaylistView(PlaylistController playlistController, Scanner scanner) {
        this.playlistController = playlistController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();
            choice = promptChoice();

            switch (choice) {
                case 1 -> viewAllPlaylists();
                case 2 -> createPlaylist();
                case 3 -> updatePlaylist();
                case 4 -> archivePlaylist();
                case 5 -> deletePlaylist();

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
        System.out.println("\n----- Playlist Management -----");
        System.out.println("1. View All Playlists");
        System.out.println("2. Create Playlist");
        System.out.println("3. Update Playlist");
        System.out.println("4. Archive Playlist");
        System.out.println("5. Delete Playlist");
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

    private void viewAllPlaylists() {
        System.out.println("\n----- View All Playlists -----");
        List<Playlist> playlists = playlistController.handleViewAllPlaylists();
        printPlaylists(playlists);
    }

    private void createPlaylist() {
        System.out.println("\n----- Create Playlist -----");
        System.out.print("Playlist Name: ");
        String name = scanner.nextLine();

        Playlist playlist = new Playlist(name);
        boolean isSuccess = playlistController.handleCreatePlaylist(playlist);
        System.out.println(isSuccess ? "Playlist created successfully." : "Failed to create playlist.");

        if (isSuccess) {
            System.out.println();
            viewAllPlaylists();
        }
    }

    private void updatePlaylist() {
        System.out.println("\n----- Update Playlist -----");
        viewAllPlaylists();

        System.out.print("Playlist ID to update: ");
        int id = readInt();

        Playlist current = playlistController.handleGetPlaylistById(id);
        if (current == null) {
            System.out.println("No playlist found with ID " + id + ".");
            return;
        }

        System.out.print("New Name [" + current.getName() + "] (press Enter to keep current): ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            name = current.getName();
        }

        Playlist playlist = new Playlist(id, name);
        boolean isSuccess = playlistController.handleUpdatePlaylist(playlist);
        System.out.println(isSuccess ? "Playlist updated successfully." : "Failed to update playlist.");

        if (isSuccess) {
            System.out.println();
            viewAllPlaylists();
        }
    }

    private void archivePlaylist() {
        System.out.println("\n----- Archive Playlist -----");
        System.out.print("Playlist ID to archive: ");
        int id = readInt();
        boolean isSuccess = playlistController.handleArchivePlaylist(id);
        System.out.println(isSuccess ? "Playlist archived successfully." : "Failed to archive playlist.");
    }

    private void deletePlaylist() {
        System.out.println("\n----- Delete Playlist -----");
        System.out.print("Playlist ID to delete: ");
        int id = readInt();
        boolean isSuccess = playlistController.handleDeletePlaylist(id);
        System.out.println(isSuccess ? "Playlist deleted successfully." : "Failed to delete playlist.");
    }

    public void printPlaylists(List<Playlist> playlists) {
        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(25) + "+";

        System.out.println(border);
        System.out.printf("| %-4s | %-23s |%n", "ID", "Name");
        System.out.println(border);

        for (Playlist playlist : playlists) {
            System.out.printf("| %-4s | %-23s |%n", playlist.getId(), playlist.getName());
        }

        System.out.println(border);
    }
}
