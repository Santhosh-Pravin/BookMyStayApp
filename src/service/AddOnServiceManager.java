package service;

import model.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnServiceManager {
    // Map of ReservationId -> List of Services
    private final Map<String, List<Service>> addOnServices;

    public AddOnServiceManager() {
        this.addOnServices = new HashMap<>();
    }

    public void addServiceToReservation(String reservationId, Service service) {
        addOnServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Service '" + service.getName() + "' added to Reservation " + reservationId);
    }

    public List<Service> getServicesForReservation(String reservationId) {
        return addOnServices.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateAddOnCost(String reservationId) {
        List<Service> services = getServicesForReservation(reservationId);
        double totalCost = 0;
        for (Service s : services) {
            totalCost += s.getPrice();
        }
        return totalCost;
    }
}
