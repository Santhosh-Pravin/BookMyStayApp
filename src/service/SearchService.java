package service;

import core.Inventory;
import model.Room;
import java.util.List;
import java.util.ArrayList;

public class SearchService {
    private final Inventory inventory;
    private final List<Room> allRooms;

    public SearchService(Inventory inventory, List<Room> allRooms) {
        this.inventory = inventory;
        this.allRooms = allRooms;
    }

    /**
     * Finds and returns all rooms that have available inventory > 0.
     * This is a read-only operation.
     */
    public List<Room> searchAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : allRooms) {
            String roomType = room.getClass().getSimpleName();
            if (inventory.getAvailability(roomType) > 0) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}
