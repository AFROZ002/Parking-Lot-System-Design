package parkinglot.model;
 import parkinglot.enums.VehicleType;


public class Car extends  Vehicle {
    public  Car(String licensePlate){
        super(licensePlate, VehicleType.CAR);

    }

}
