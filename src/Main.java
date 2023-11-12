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
        Floor floors = new Floor(floor, structure, probability);
        Floor[] allFloors = new Floor[floor];
        Elevator[] elevator2 = new Elevator[floor];
        for(int i  = 0; i < floor; i++){
            allFloors[i] = new Floor(floor, structure, probability);
            allFloors[i].queuePassenger();

;
 //           System.out.println(allFloors[i].getUpQueue());
//            System.out.println(allFloors[i].getDownQueue());

        }

//        for(int i = 0; i< floor - 1; i++) {
//            System.out.println("this is UpQueue: " + allFloors[i].getUpQueue());
//            System.out.println("this is DownQueue: " +allFloors[i].getDownQueue());
//        }
        Elevator elevator1 = new Elevator(floor, capacity, elevator, structure, allFloors, probability);
//        for(int i  = 0; i < elevator; i++){
//            elevator2[i] = new Elevator(floor, capacity, elevator, structure, allFloors, probability);
////            elevator2[i].addPassengers(allFloors[elevator2[i].getNumFloor()].getUpQueue());
////            System.out.println(elevator2[i].getUpElevator().peek());
//        }
        // elevator1.setGoingUp(true);
        int lastNonEmptyFloor = 0;
        int firstNonEmptyFloor = 0;

        for (int i = 0; i < floor; i++) {
            if (!allFloors[i].getUpQueue().isEmpty()) {
                lastNonEmptyFloor = i;
            }
        }
        for (int i = 0; i < floor; i++) {
            if (!allFloors[i].getUpQueue().isEmpty()) {
                firstNonEmptyFloor = i;
                break;
            }
        }
        for (int i = 0; i < ticks ; i++){
            elevator1.moveElevator(firstNonEmptyFloor, lastNonEmptyFloor);

        }

       // System.out.println(elevator1.getUpElevator().peek());

        System.out.println("--- Elevator is on floor " + elevator1.getCurrFloor() + " ---");
        //System.out.println("--- Elevator is heading to " + elevator1.getDestFloor() + " ---");
        System.out.println("--- Elevator has arrived at " + elevator1.getCurrFloor() + " ---");
        System.out.println("--- Average length of time between passenger arrival and conveyance to the final destination ");

    }
}