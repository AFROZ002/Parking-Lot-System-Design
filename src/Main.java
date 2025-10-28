

import parkinglot.model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System Demo ===\n");

        // Get parking lot instance
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Display initial status
        parkingLot.displayParkingStatus();

        // Test Case 1: Park a car
        System.out.println("TEST 1: Parking a car");
        Car car1 = new Car("ABC123"); // Now this works!
        Ticket ticket1 = parkingLot.parkVehicle(car1);

        // Test Case 2: Park a bike
        System.out.println("\nTEST 2: Parking a bike");
        Bike bike1 = new Bike("BIKE001"); // Now this works!
        Ticket ticket2 = parkingLot.parkVehicle(bike1);

        // Display status after parking
        parkingLot.displayParkingStatus();

        // Test Case 3: Exit vehicle
        System.out.println("TEST 3: Exiting car");
        if (ticket1 != null) {
            parkingLot.exitVehicle(ticket1);
        }

        // Display final status
        parkingLot.displayParkingStatus();

        System.out.println("=== Demo Completed ===");
    }
}