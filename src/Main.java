// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.*;
import java.util.*;
import java.util.Properties;
import java.io.File;
import java.util.ArrayList;
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
 //       Floor floors = new Floor(floor, structure, probability);
        Floor[] allFloors = new Floor[floor];
        Elevator[] elevator2 = new Elevator[floor];

        for(int i  = 0; i < floor; i++) {

            allFloors[i] = new Floor(floor, structure, probability, i);
          //  allFloors[i].queuePassenger(i);

//            ;
//              System.out.println(allFloors[i].getUpQueue());
//            System.out.println(allFloors[i].getDownQueue());

        }
        for(int i = 0; i < elevator; i++){
            elevator2[i] = new Elevator(floor, capacity, structure, allFloors, probability);
        }

//        for(int i = 0; i< floor - 1; i++) {
//            System.out.println("this is UpQueue: " + allFloors[i].getUpQueue());
//            System.out.println("this is DownQueue: " +allFloors[i].getDownQueue());
//        }
        Elevator elevator1 = new Elevator(floor, capacity, structure, allFloors, probability);
//        for(int j = 0; j < ticks; j++) {
//            for(int i  = 0; i < floor; i++) {
//
//                allFloors[i] = new Floor(floor, structure, probability, i);
//                allFloors[i].queuePassenger();
//
//            }
//            for (int i = 0; i < elevator; i++) {
//                elevator2[i] = new Elevator(floor, capacity, structure, allFloors, probability);
//                elevator2[i].addPassengers(floor, allFloors);
//                elevator2[i].dropPassengers();
//                elevator2[i].moveElevator();
////            elevator2[i].addPassengers(allFloors[elevator2[i].getNumFloor()].getUpQueue());
////            System.out.println(elevator2[i].getUpElevator().peek());
//            }
//        }
        // elevator1.setGoingUp(true);
        int sum = 0;
        int size = 0;
        ArrayList<Integer> timerValues = new ArrayList<Integer>();
        for (int i = 0; i < ticks ; i++) {

            for (Floor allFloor : allFloors) {

                allFloor.queuePassenger(i);

//            ;
//              System.out.println(allFloors[i].getUpQueue());
//            System.out.println(allFloors[i].getDownQueue());

            }

        for(int j = 0; j < elevator; j++){
             timerValues.add(elevator2[j].dropPassengers(i));
            elevator2[j].addPassengers(floor, allFloors);
            elevator2[j].moveElevator();
        }

        }

        for (int z = 0; z < timerValues.size(); z++) {
            if(timerValues.get(z) != 0) {
                sum += timerValues.get(z);
                size ++;
            }
        }

        int averageTime =  sum / size;

        System.out.println("This is average time: " + averageTime);



      //  System.out.println( Math.round((double) sum / elevator1.timer().size()));


        // Calculate the average and cast it to an int

       // System.out.println(elevator1.timer(elevator1.dropPassengers()));
       // System.out.println(elevator1.getUpElevator().peek());

        System.out.println("--- Elevator is on floor " + elevator1.getCurrFloor() + " ---");
        //System.out.println("--- Elevator is heading to " + elevator1.getDestFloor() + " ---");
        System.out.println("--- Elevator has arrived at " + elevator1.getCurrFloor() + " ---");
        System.out.println("--- Average length of time between passenger arrival and conveyance to the final destination ");

    }
}