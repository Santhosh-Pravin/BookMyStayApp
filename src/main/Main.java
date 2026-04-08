package main;

import model.SingleRoom;
import model.DoubleRoom;
import model.SuiteRoom;
import core.Inventory;

/**
 * The Main class serves as the entry point for the Hotel Booking Management System.
 *
 * @author Santhosh Pravin
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hotel Booking System v1.0");
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("-------------------------------------------------");
        
        SingleRoom single = new SingleRoom("S101");
        DoubleRoom doubleRoom = new DoubleRoom("D201");
        SuiteRoom suite = new SuiteRoom("SU301");
        
        // UC3: Centralized inventory management using HashMap
        Inventory inventory = new Inventory();
        
        System.out.println("Initial Room Availability (Managed by core.Inventory):");
        System.out.println(single + " - Available: " + inventory.getAvailability("SingleRoom"));
        System.out.println(doubleRoom + " - Available: " + inventory.getAvailability("DoubleRoom"));
        System.out.println(suite + " - Available: " + inventory.getAvailability("SuiteRoom"));
        
        System.out.println("\nTesting Availability Update (Booking 1 SingleRoom):");
        inventory.updateAvailability("SingleRoom", -1);
        System.out.println(single + " - Available: " + inventory.getAvailability("SingleRoom"));
    }
}
