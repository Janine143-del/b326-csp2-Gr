package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.cliview.ArtistView;
import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.repository.ArtistRepoImpl;
import com.joysistvi.recordingapp.service.ArtistService;
import com.joysistvi.recordingapp.service.ArtistServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DbConnection dbConnection = new DbConnection();
        ArtistRepoImpl artistRepo = new ArtistRepoImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepo);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistView = new ArtistView(artistController, scanner);

        int choice;

        do {
            System.out.println("\n===========================");
            System.out.println("   RECORDING STUDIO APP    ");
            System.out.println("===========================");
            System.out.println("[1] Login as Admin");
            System.out.println("[2] Login as User");
            System.out.println("[0] Exit");
            System.out.print("Enter Your Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-> You successfully here in Admin Dashboard!");
                    // This where I input the ArtistView, SongView, etc.
                }
                case 2 -> {
                    System.out.println("\n-> You successfully here in User Dashboard!");
                    // This where input Playlist views For my user.
                }
                case 0 -> {
                    System.out.println("\nHave nice day! Thank you for using RecordApp!");
                }
                default -> {
                    System.out.println("\n⚠️ Try again, choose another number.");
                }
            }

        } while (choice != 0); // It will loop if 0 will not be chosen

        scanner.close();
    }
}