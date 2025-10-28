package parkinglot.model;

import parkinglot.enums.VehicleType;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> floors;
    private Map<String, Ticket> activeTickets;

    private ParkingLot() {
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        initializeParkingLot();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    private void initializeParkingLot() {
        // Create 2 floors
        floors.add(new ParkingFloor(1));
        floors.add(new ParkingFloor(2));
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        // Find available spot
        ParkingSpot availableSpot = findAvailableSpot(vehicle.getVehicleType());
        if (availableSpot == null) {
            System.out.println("No available spot for vehicle: " + vehicle.getLicensePlate());
            return null;
        }

        // Assign vehicle to spot
        if (availableSpot.assignVehicle(vehicle)) {
            // Generate ticket
            String ticketId = "TKT" + System.currentTimeMillis();
            Ticket ticket = new Ticket(ticketId, vehicle, availableSpot);
            activeTickets.put(ticketId, ticket);

            System.out.println("Vehicle " + vehicle.getLicensePlate() +
                    " parked at spot: " + availableSpot.getSpotId() +
                    " with ticket: " + ticket.getTicketId());
            return ticket;
        }

        return null;
    }

    public boolean exitVehicle(Ticket ticket) {
        if (ticket == null || !activeTickets.containsKey(ticket.getTicketId())) {
            System.out.println("Invalid ticket!");
            return false;
        }

        // Calculate fee
        double fee = calculateFee(ticket);

        // Process payment (simulated)
        System.out.println("Processing payment of $" + fee);
        boolean paymentSuccess = true; // Simulated success

        if (paymentSuccess) {
            // Free the parking spot
            ticket.getParkingSpot().removeVehicle();

            // Update ticket
            ticket.setExitTime();
            ticket.setTotalFee(fee);
            ticket.setStatus(parkinglot.enums.TicketStatus.PAID);
            activeTickets.remove(ticket.getTicketId());

            System.out.println("Vehicle " + ticket.getVehicle().getLicensePlate() +
                    " exited. Total fee: $" + fee);
            return true;
        } else {
            System.out.println("Payment failed for ticket: " + ticket.getTicketId());
            return false;
        }
    }

    private double calculateFee(Ticket ticket) {
        long hours = ticket.getParkingDurationInHours();
        if (hours <= 1) {
            return 5.0; // Flat rate for first hour
        } else {
            return 5.0 + (hours - 1) * 10.0; // $10 per additional hour
        }
    }

    private ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicleType);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }

    public void displayParkingStatus() {
        System.out.println("\n=== Parking Lot Status ===");
        for (ParkingFloor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            for (ParkingSpot spot : floor.getSpots()) {
                String status = spot.isAvailable() ? "Available" :
                        "Occupied by " + spot.getParkedVehicle().getLicensePlate();
                System.out.println("  " + spot.getSpotId() + " (" + spot.getSpotType() + "): " + status);
            }
        }
        System.out.println("Active tickets: " + activeTickets.size());
        System.out.println("==========================\n");
    }
}