package model;

public class Reservation {
    private static int counter = 1;
    private String reservationId;
    private String customerName;
    private String roomTypeRequest;

    public Reservation(String customerName, String roomTypeRequest) {
        this.reservationId = "RES" + (counter++);
        this.customerName = customerName;
        this.roomTypeRequest = roomTypeRequest;
    }

    public String getReservationId() { return reservationId; }
    public String getCustomerName() { return customerName; }
    public String getRoomTypeRequest() { return roomTypeRequest; }
    
    @Override
    public String toString() {
        return String.format("Reservation[%s] - Customer: %s, Room Request: %s", 
                reservationId, customerName, roomTypeRequest);
    }
}
