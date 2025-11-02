package parkinglot.model;

import parkinglot.enums.TicketStatus;
import java.time.LocalDateTime;
import java.time.Duration;

public class Ticket {
    private String ticketId;             // Unique ticket ID (e.g., TKT12345)
    private Vehicle vehicle;             // Vehicle object (Car/Bike etc.)
    private ParkingSpot parkingSpot;     // Jis spot pe park kiya gaya
    private LocalDateTime entryTime;     // Entry time jab vehicle park hua
    private LocalDateTime exitTime;      // Exit time jab vehicle gaya
    private double totalFee;             // Total charge
    private TicketStatus status;         // ACTIVE / PAID etc.

    // Constructor — jab vehicle park hota hai, tab ticket generate hota hai
    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;            // Ticket ka ID set
        this.vehicle = vehicle;              // Vehicle object set
        this.parkingSpot = parkingSpot;      // Spot object set
        this.entryTime = LocalDateTime.now();// Current time as entry
        this.status = TicketStatus.ACTIVE;   // Status ko ACTIVE mark karte hain
    }

    // Duration nikalta hai (kitne hours vehicle park raha)
    public long getParkingDurationInHours() {
        LocalDateTime endTime = (exitTime != null) ? exitTime : LocalDateTime.now();
        Duration duration = Duration.between(entryTime, endTime); // entry aur exit ke beech difference
        return Math.max(1, duration.toMinutes() / 60); // minimum 1 hour charge
    }

    // Getters & Setters
    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
    public LocalDateTime getEntryTime() { return entryTime; }

    public void setExitTime() { this.exitTime = LocalDateTime.now(); } // exit time ab set karte hain
    public double getTotalFee() { return totalFee; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
