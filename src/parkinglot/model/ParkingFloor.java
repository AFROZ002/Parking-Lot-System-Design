package parkinglot.model;


import parkinglot.enums.SpotType;
import parkinglot.enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private  int floorNumber;
    private List<ParkingSpot> spots;
    private Map<String , ParkingSpot>  occupiedSpots;


    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
        this.occupiedSpots = new HashMap<>();
        initializeSpots();
    }


    private  void initializeSpots(){
        for(int i=1;i<=5;++i){
            spots.add(new ParkingSpot("F"+floorNumber+"-C"+i, SpotType.COMPACT));



        }

        for(int i=1;i<=3;++i){
            spots.add(new ParkingSpot("F"+floorNumber+"-C"+i,SpotType.LARGE));
        }

        for (int i = 1; i <= 2; i++) {
            spots.add(new ParkingSpot("F" + floorNumber + "-B" + i, SpotType.BIKE));
        }
    }
    public  ParkingSpot  findAvailableSpot(VehicleType vehicleType){
        for(ParkingSpot spot: spots){
            if(spot.isAvailable()){
                Vehicle testVehicle;
                switch (vehicleType){
                    case CAR:testVehicle=new Car("Test");break;
                    case  BIKE:testVehicle=new Bike("Test");break;
                    case TRUCK:testVehicle=new Car("Test"); break;
                    default:continue;
                }
                if(spot.assignVehicle(testVehicle)){
                    spot.removeVehicle();
                    return spot;
                }
            }
        }
        return  null;
    }

    public  boolean  assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot){

        if(spot.assignVehicle(vehicle)){
            occupiedSpots.put(vehicle.getLicensePlate(),spot);
            return true;
        }
        return false;
    }

    public  void  freeSpot(String licensePlate){
        ParkingSpot spot=occupiedSpots.remove(licensePlate);
        if(spot!=null){
            spot.removeVehicle();
        }

    }

    public int getFloorNumber() { return floorNumber; }
    public List<ParkingSpot> getSpots() { return spots; }






}
