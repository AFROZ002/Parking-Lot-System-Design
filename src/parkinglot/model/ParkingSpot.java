package parkinglot.model;


import parkinglot.enums.SpotType;

public class ParkingSpot {
    private String spotId;
    private SpotType spotType;
    private boolean isAvailable;
    private Vehicle parkedVehicle;


    public ParkingSpot(String spotId, SpotType spotType) {

        this.spotId = spotId;
        this.spotType = spotType;
        this.isAvailable = true;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        if (isAvailable && isCompatible(vehicle)) {
            this.parkedVehicle = vehicle;
            this.isAvailable = false;
            return true;


        }
        return false;


    }


    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }


    private boolean isCompatible(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case CAR:
                return spotType == SpotType.COMPACT || spotType == SpotType.LARGE;
            case BIKE:
                return spotType == SpotType.BIKE || spotType == SpotType.COMPACT;
            case TRUCK:
                return spotType == SpotType.BIKE;

            default:
                return false;

        }
    }

    // Getters
    public String getSpotId() { return spotId; }
    public SpotType getSpotType() { return spotType; }
    public boolean isAvailable() { return isAvailable; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}








