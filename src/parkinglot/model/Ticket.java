package parkinglot.model;


import parkinglot.enums.TicketStatus;
import java.time.LocalDateTime;
import java.time.Duration;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double totalFee;
    private TicketStatus status;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    public long getParkingDurationInHours() {
        LocalDateTime endTime = (exitTime != null) ? exitTime : LocalDateTime.now();
        Duration duration = Duration.between(entryTime, endTime);
        return Math.max(1, duration.toMinutes() / 60); // At least 1 hour
    }

    // Getters and setters
    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setExitTime() { this.exitTime = LocalDateTime.now(); }
    public double getTotalFee() { return totalFee; }
    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
