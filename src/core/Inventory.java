package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

/**
 * Centrally manages room inventory using a HashMap.
 */
public class Inventory {
    private final Map<String, Integer> roomAvailability;

    public Inventory() {
        this.roomAvailability = new HashMap<>();
        // Initialize with default availability
        this.roomAvailability.put("SingleRoom", 10);
        this.roomAvailability.put("DoubleRoom", 5);
        this.roomAvailability.put("SuiteRoom", 2);
    }

    /**
     * @return an unmodifiable view of current availability.
     */
    public Map<String, Integer> getAvailableRooms() {
        return Collections.unmodifiableMap(roomAvailability);
    }

    /**
     * Retrieves availability for a specific room type.
     */
    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability safely.
     */
    public void updateAvailability(String roomType, int delta) {
        if (!roomAvailability.containsKey(roomType)) {
            throw new IllegalArgumentException("Unknown room type: " + roomType);
        }
        int current = roomAvailability.get(roomType);
        int newAvailability = current + delta;
        
        if (newAvailability < 0) {
            throw new IllegalStateException("Not enough availability for " + roomType);
        }
        
        roomAvailability.put(roomType, newAvailability);
    }
}
