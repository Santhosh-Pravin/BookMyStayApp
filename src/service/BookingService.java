package service;

import core.BookingQueue;
import core.Inventory;
import model.Reservation;
import model.Room;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookingService {
    private final BookingQueue bookingQueue;
    private final Inventory inventory;
    private final List<Room> allRooms;
    
    // Track allocated rooms per type
    private final Map<String, Set<String>> allocatedRooms;
    // Prevent duplicate room IDs across the entire hotel
    private final Set<String> globalAllocatedIds;

    public BookingService(BookingQueue bookingQueue, Inventory inventory, List<Room> allRooms) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allRooms = allRooms;
        this.allocatedRooms = new HashMap<>();
        this.globalAllocatedIds = new HashSet<>();
    }

    public void processNextBooking() {
        if (bookingQueue.isEmpty()) {
            System.out.println("Processing skipped: No pending bookings in the queue.");
            return;
        }

        Reservation req = bookingQueue.processNextRequest();
        String roomType = req.getRoomTypeRequest();

        if (inventory.getAvailability(roomType) > 0) {
            String allocatedRoomId = findAvailableRoomId(roomType);
            if (allocatedRoomId != null) {
                // Update tracking
                globalAllocatedIds.add(allocatedRoomId);
                allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(allocatedRoomId);
                
                // Update inventory immediately
                inventory.updateAvailability(roomType, -1);
                
                System.out.println("Booking Confirmed! [Reservation: " + req.getReservationId() + 
                                   "] Customer: " + req.getCustomerName() + 
                                   ", Room Type: " + roomType + ", Room ID: " + allocatedRoomId);
            } else {
                System.out.println("Booking Failed: No specific room IDs available for " + roomType);
            }
        } else {
            System.out.println("Booking Failed: " + roomType + " is out of stock for Reservation " + req.getReservationId());
        }
    }

    private String findAvailableRoomId(String roomType) {
        for (Room r : allRooms) {
            if (r.getClass().getSimpleName().equals(roomType)) {
                if (!globalAllocatedIds.contains(r.getRoomId())) {
                    return r.getRoomId();
                }
            }
        }
        return null; // Should not happen if inventory count > 0 and catalog is setup right
    }
}
