package model;

/**
 * Abstract class representing a generic Room in the hotel.
 */
public abstract class Room {
    private String roomId;
    private int numberOfBeds;
    private double size;
    private double price;

    public Room(String roomId, int numberOfBeds, double size, double price) {
        this.roomId = roomId;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    public String getRoomId() { return roomId; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public double getSize() { return size; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s [ID: %s, Beds: %d, Size: %.1f sq.m, Price: $%.2f]",
                this.getClass().getSimpleName(), roomId, numberOfBeds, size, price);
    }
}
