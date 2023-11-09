// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.*;
import java.util.*;
import java.util.Properties;
import java.io.File;
public class Main {
    public static void main(String[] args) throws Exception {
        int floor = 0;
        float probability = 0;
        int elevator = 0;
        int capacity = 0;
        int ticks = 0;
        int counter = 0;
        String structure = "";
    if(args.length == 0) {
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);


        structure = p.getProperty("structures");
        floor = Integer.parseInt(p.getProperty("floors"));
        probability = Float.parseFloat(p.getProperty("passengers"));
        elevator = Integer.parseInt(p.getProperty("elevators"));
        capacity = Integer.parseInt(p.getProperty("elevatorCapacity"));
        ticks = Integer.parseInt(p.getProperty("duration"));
    } else if (args.length == 1){
        FileReader reader = new FileReader(args[0]);
        Properties p = new Properties();
        p.load(reader);


        structure = p.getProperty("structures");
        floor = Integer.parseInt(p.getProperty("floors"));
        probability = Float.parseFloat(p.getProperty("passengers"));
        elevator = Integer.parseInt(p.getProperty("elevators"));
        capacity = Integer.parseInt(p.getProperty("elevatorCapacity"));
        ticks = Integer.parseInt(p.getProperty("duration"));
    }
        //System.out.println(floor);
        Floor floors = new Floor(floor, structure);
        Floor[] allFloors = new Floor[floor];
        Elevator elevator1 = new Elevator(floor, capacity, elevator, structure, allFloors);
      //  Passenger passengers = new Passenger(floor);
      //  System.out.println(floors.queuePassenger(passengers));
       // System.out.println(passengers.getDirection());
       // passengers.levels(floors.floorLevel(p.getProperty("floors"));

        for (int i = 0; i < 5; i++){
            elevator1.moveElevator();
            //elevator1.addPassengers();
           // counter++;
        }

     //   System.out.println(elevator1.addPassengers());
        System.out.println("--- Elevator is on floor " + elevator1.getCurrFloor() + " ---");
        System.out.println("--- Elevator is heading to " + elevator1.getDestFloor() + " ---");
        System.out.println("--- Elevator has arrived at " + elevator1.getCurrFloor() + " ---");
        System.out.println("--- Average length of time between passenger arrival and conveyance to the final destination ");

    }
}