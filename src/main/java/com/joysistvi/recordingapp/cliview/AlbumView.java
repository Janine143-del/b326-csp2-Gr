package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

import java.util.List;
import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final Scanner scanner;

    public AlbumView(AlbumController albumController, Scanner scanner) {
        this.albumController = albumController;
        this.scanner = scanner;
    }

    public void run() {
        int choice;

        do {
            printMenu();
            choice = promptChoice();

            switch (choice) {
                case 1 -> viewAllAlbums();
                case 2 -> addAlbum();
                case 3 -> updateAlbum();
                case 4 -> archiveAlbum();
                case 5 -> deleteAlbum();

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
        System.out.println("\n----- Album Management -----");
        System.out.println("1. View All Albums");
        System.out.println("2. Add Album");
        System.out.println("3. Update Album");
        System.out.println("4. Archive Album");
        System.out.println("5. Delete Album");
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

    private void viewAllAlbums() {
        System.out.println("\n----- View All Albums -----");
        List<Album> albums = albumController.handleViewAllAlbums();
        printAlbums(albums);
    }

    private void addAlbum() {
        System.out.println("\n----- Add Album -----");
        System.out.print("Album Title: ");
        String title = scanner.nextLine();
        System.out.print("Artist ID: ");
        int artistId = readInt();

        Album album = new Album(title, artistId);
        boolean isSuccess = albumController.handleCreateAlbum(album);
        System.out.println(isSuccess ? "Album added successfully." : "Failed to add album.");

        if (isSuccess) {
            System.out.println();
            viewAllAlbums();
        }
    }

    private void updateAlbum() {
        System.out.println("\n----- Update Album -----");
        viewAllAlbums();

        System.out.print("Album ID to update: ");
        int id = readInt();

        Album current = albumController.handleGetAlbumById(id);
        if (current == null) {
            System.out.println("No album found with ID " + id + ".");
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

        Album album = new Album(id, title, artistId);
        boolean isSuccess = albumController.handleUpdateAlbum(album);
        System.out.println(isSuccess ? "Album updated successfully." : "Failed to update album.");

        if (isSuccess) {
            System.out.println();
            viewAllAlbums();
        }
    }

    private void archiveAlbum() {
        System.out.println("\n----- Archive Album -----");
        System.out.print("Album ID to archive: ");
        int id = readInt();
        boolean isSuccess = albumController.handleArchiveAlbum(id);
        System.out.println(isSuccess ? "Album archived successfully." : "Failed to archive album.");
    }

    private void deleteAlbum() {
        System.out.println("\n----- Delete Album -----");
        System.out.print("Album ID to delete: ");
        int id = readInt();
        boolean isSuccess = albumController.handleDeleteAlbum(id);
        System.out.println(isSuccess ? "Album deleted successfully." : "Failed to delete album.");
    }

    public void printAlbums(List<Album> albums) {
        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(25) + "+" + "-".repeat(12) + "+";

        System.out.println(border);
        System.out.printf("| %-4s | %-23s | %-10s |%n", "ID", "Title", "Artist ID");
        System.out.println(border);

        for (Album album : albums) {
            System.out.printf("| %-4s | %-23s | %-10s |%n", album.getId(), album.getTitle(), album.getArtistId());
        }

        System.out.println(border);
    }
}
