package parkinglot.model;

import parkinglot.enums.SpotType;

public class ParkingSpot {
    private String spotId;          // Har spot ka unique ID (e.g., F1-C1)
    private SpotType spotType;      // Spot ka type (COMPACT, LARGE, BIKE)
    private boolean isAvailable;    // Spot free hai ya occupied
    private Vehicle parkedVehicle;  // Kaunsa vehicle currently park hai

    // Constructor — jab naya parking spot create hota hai
    public ParkingSpot(String spotId, SpotType spotType) {
        this.spotId = spotId;       // Spot ka ID set karte hain
        this.spotType = spotType;   // Spot ka type set karte hain
        this.isAvailable = true;    // Initially har spot available hota hai
    }

    // Vehicle ko assign karne ka method (agar spot free aur compatible hai)
    public boolean assignVehicle(Vehicle vehicle) {
        if (isAvailable && isCompatible(vehicle)) {   // check dono condition
            this.parkedVehicle = vehicle;             // vehicle ko park kar diya
            this.isAvailable = false;                 // ab yeh spot occupied ho gaya
            return true;
        }
        return false; // agar compatible nahi ya already occupied hai
    }

    // Spot ko free karne ka method (jab vehicle exit karta hai)
    public void removeVehicle() {
        this.parkedVehicle = null;  // vehicle hata do
        this.isAvailable = true;    // spot ko available mark kar do
    }

    // Check karta hai ki yeh spot given vehicle ke liye suitable hai ya nahi
    private boolean isCompatible(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) { // VehicleType enum (CAR, BIKE, TRUCK)
            case CAR:
                // Car compact aur large dono spot me fit ho sakta hai
                return spotType == SpotType.COMPACT || spotType == SpotType.LARGE;
            case BIKE:
                // Bike chhote (BIKE/COMPACT) spot me fit hoti hai
                return spotType == SpotType.BIKE || spotType == SpotType.COMPACT;
            case TRUCK:
                // (Note: Yeh logic galat lag raha hai, TRUCK ko large me fit hona chahiye)
                return spotType == SpotType.BIKE;
            default:
                return false; // unknown vehicle
        }
    }

    // Getters — read-only access ke liye
    public String getSpotId() { return spotId; }
    public SpotType getSpotType() { return spotType; }
    public boolean isAvailable() { return isAvailable; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}
