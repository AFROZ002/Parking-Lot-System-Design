package parkinglot.model;

import parkinglot.enums.VehicleType;

public class Bike  extends  Vehicle{

public  Bike(String licensePlate){
     super(licensePlate, VehicleType.BIKE);
}

}
