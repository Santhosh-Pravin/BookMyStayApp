package main;

import model.SingleRoom;
import model.DoubleRoom;
import model.SuiteRoom;

/**
 * The Main class serves as the entry point for the Hotel Booking Management System.
 *
 * @author Santhosh Pravin
 * @version 1.0
 */
public class Main {

    /**
     * The main method where application execution begins.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Print the welcome message and version
        System.out.println("Hotel Booking System v1.0");
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("-------------------------------------------------");
        
        // UC2: Introducing object modeling through abstract classes and inheritance
        SingleRoom single = new SingleRoom("S101");
        DoubleRoom doubleRoom = new DoubleRoom("D201");
        SuiteRoom suite = new SuiteRoom("SU301");
        
        // Static availability representation (UC2 only)
        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;
        
        System.out.println("Available Rooms:");
        System.out.println(single + " - Available: " + singleAvailability);
        System.out.println(doubleRoom + " - Available: " + doubleAvailability);
        System.out.println(suite + " - Available: " + suiteAvailability);
    }
}
