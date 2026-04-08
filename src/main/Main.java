package main;

import model.Room;
import model.SingleRoom;
import model.DoubleRoom;
import model.SuiteRoom;
import core.Inventory;
import service.SearchService;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * The Main class serves as the entry point for the Hotel Booking Management System.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hotel Booking System v1.0 initialized.");
        
        // Setup actual rooms matching default inventory counts (10, 5, 2)
        List<Room> roomCatalog = new ArrayList<>();
        for(int i = 1; i <= 10; i++) roomCatalog.add(new SingleRoom("S1" + String.format("%02d", i)));
        for(int i = 1; i <= 5; i++) roomCatalog.add(new DoubleRoom("D2" + String.format("%02d", i)));
        for(int i = 1; i <= 2; i++) roomCatalog.add(new SuiteRoom("SU3" + String.format("%02d", i)));
        
        Inventory inventory = new Inventory();
        SearchService searchService = new SearchService(inventory, roomCatalog);
        core.BookingQueue bookingQueue = new core.BookingQueue();
        service.BookingService bookingService = new service.BookingService(bookingQueue, inventory, roomCatalog);
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hotel Booking Menu ---");
            System.out.println("1. Search Rooms");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking");
            System.out.println("4. Add Services (Pending)");
            System.out.println("5. View Booking History (Pending)");
            System.out.println("6. Cancel Booking (Pending)");
            System.out.println("7. Generate Report (Pending)");
            System.out.println("8. Save & Exit");
            System.out.print("Enter choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            
            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Rooms Breakdown ---");
                    // Show distinct counts by getting unique types that have availability > 0
                    List<Room> availableRooms = searchService.searchAvailableRooms();
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available.");
                    } else {
                        System.out.println("SingleRoom -> Available: " + inventory.getAvailability("SingleRoom"));
                        System.out.println("DoubleRoom -> Available: " + inventory.getAvailability("DoubleRoom"));
                        System.out.println("SuiteRoom -> Available: " + inventory.getAvailability("SuiteRoom"));
                    }
                    break;
                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Room Type (SingleRoom/DoubleRoom/SuiteRoom): ");
                    String type = scanner.nextLine();
                    model.Reservation res = new model.Reservation(name, type);
                    bookingQueue.addBookingRequest(res);
                    System.out.println("Queue size is now: " + bookingQueue.size());
                    break;
                case 3:
                    System.out.println("\n--- Processing Next Booking ---");
                    bookingService.processNextBooking();
                    break;
                case 4: case 5: case 6: case 7:
                    System.out.println("Feature coming in a later UC.");
                    break;
                case 8:
                    System.out.println("Exiting System...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);
        
        scanner.close();
    }
}
