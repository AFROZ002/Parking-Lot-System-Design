package parkinglot.model;

import parkinglot.enums.SpotType;
import parkinglot.enums.VehicleType;
import java.util.*;

public class ParkingFloor {
    private int floorNumber;                        // Floor number (1, 2, etc.)
    private List<ParkingSpot> spots;                // Is floor ke sabhi spots
    private Map<String, ParkingSpot> occupiedSpots; // LicensePlate -> Spot mapping

    // Constructor — jab naya floor create hota hai
    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();     // floor ke sab spots store karne ke liye
        this.occupiedSpots = new HashMap<>(); // currently occupied spots
        initializeSpots(); // floor me spot create karne ka method call
    }

    // Floor ke andar alag-alag type ke parking spots banata hai
    private void initializeSpots() {
        // 5 Compact spots create karte hain
        for (int i = 1; i <= 5; ++i) {
            spots.add(new ParkingSpot("F" + floorNumber + "-C" + i, SpotType.COMPACT));
        }

        // 3 Large spots create karte hain
        for (int i = 1; i <= 3; ++i) {
            spots.add(new ParkingSpot("F" + floorNumber + "-L" + i, SpotType.LARGE));
        }

        // 2 Bike spots create karte hain
        for (int i = 1; i <= 2; i++) {
            spots.add(new ParkingSpot("F" + floorNumber + "-B" + i, SpotType.BIKE));
        }
    }

    // Available spot dhoondta hai given vehicle type ke liye
    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : spots) {         // har spot check karte hain
            if (spot.isAvailable()) {            // agar spot free hai to
                Vehicle testVehicle;             // temporary vehicle banate hain compatibility check ke liye
                switch (vehicleType) {
                    case CAR: testVehicle = new Car("Test"); break;
                    case BIKE: testVehicle = new Bike("Test"); break;
                    // case TRUCK: testVehicle = new Truck("Test"); break;
                    default: continue;
                }
                // agar yeh testVehicle spot me fit hota hai
                if (spot.assignVehicle(testVehicle)) {
                    spot.removeVehicle();        // test ke baad vehicle hata do
                    return spot;                 // aur yeh spot return kar do
                }
            }
        }
        return null; // agar koi spot nahi mila
    }

    // Vehicle ko actual spot pe assign karta hai (ParkingLot se call hota hai)
    public boolean assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        if (spot.assignVehicle(vehicle)) { // agar assignVehicle() success hua (ParkingSpot.java me defined)
            occupiedSpots.put(vehicle.getLicensePlate(), spot); // map me entry daal do
            return true;
        }
        return false; // nahi to false
    }

    // Vehicle exit hone par uska spot free karne ka method
    public void freeSpot(String licensePlate) {
        ParkingSpot spot = occupiedSpots.remove(licensePlate); // map se remove karo
        if (spot != null) {
            spot.removeVehicle(); // ParkingSpot.java ka method call karke free karo
        }
    }

    // Getters
    public int getFloorNumber() { return floorNumber; }
    public List<ParkingSpot> getSpots() { return spots; }
}
