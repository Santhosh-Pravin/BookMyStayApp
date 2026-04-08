package core;

import model.Reservation;
import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private final Queue<Reservation> queue;

    public BookingQueue() {
        this.queue = new LinkedList<>();
    }

    public void addBookingRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added to queue: " + reservation.getReservationId());
    }

    public Reservation processNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public int size() {
        return queue.size();
    }
}
