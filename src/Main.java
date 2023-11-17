// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.*;
import java.util.*;
import java.util.Properties;
import java.io.File;
import java.util.ArrayList;
public class Main {


    public static void main(String[] args) throws IOException {
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
    } else if(args.length == 1){
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);

        structure = p.getProperty("structures");
        floor = Integer.parseInt(p.getProperty("floors"));
        probability = Float.parseFloat(p.getProperty("passengers"));
        elevator = Integer.parseInt(p.getProperty("elevators"));
        capacity = Integer.parseInt(p.getProperty("elevatorCapacity"));
        ticks = Integer.parseInt(p.getProperty("duration"));
        try {
            FileReader readerNew = new FileReader(args[0]);
            Properties pNew = new Properties();
            pNew.load(readerNew);

            if ( pNew.getProperty("structures") != null && pNew.getProperty("structures").equals("linked") || pNew.getProperty("structures").equals("array") && !pNew.getProperty("structures").isEmpty()){
                structure = pNew.getProperty("structures");
            }
            if(!pNew.getProperty("floors").isEmpty() && Integer.parseInt(pNew.getProperty("floors")) >= 2 &&  pNew.getProperty("floors") != null) {
                floor = Integer.parseInt(pNew.getProperty("floors"));
            }
            if(!pNew.getProperty("passengers").isEmpty() && Float.parseFloat(pNew.getProperty("passengers"))  < 1 && Float.parseFloat(pNew.getProperty("passengers"))  > 0 &&  pNew.getProperty("passengers") != null) {
                probability = Float.parseFloat(pNew.getProperty("passengers"));
            }
            if(!pNew.getProperty("elevators").isEmpty() && Integer.parseInt(pNew.getProperty("elevators")) >= 1 &&  pNew.getProperty("elevators") != null ) {
                elevator = Integer.parseInt(pNew.getProperty("elevators"));
            }
            if(!pNew.getProperty("elevatorCapacity").isEmpty() && Integer.parseInt(pNew.getProperty("elevatorCapacity")) >= 1 &&  pNew.getProperty("elevatorCapacity") != null) {
                capacity = Integer.parseInt(pNew.getProperty("elevatorCapacity"));
            }
            if(!pNew.getProperty("duration").isEmpty() && Integer.parseInt(pNew.getProperty("duration")) >= 1 &&  pNew.getProperty("duration") != null) {
                ticks = Integer.parseInt(pNew.getProperty("duration"));
            }

        } catch (FileNotFoundException e){
            System.out.println("File Error");
        }
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

        int sum = 0;
        int max = 0;
        int min = ticks + 1;
        int size = 0;
        ArrayList<Integer> timerValues = new ArrayList<Integer>();
        for (int i = 0; i < ticks ; i++) {
            for (Floor allFloor : allFloors) {
                allFloor.queuePassenger(i);
            }

            for(int j = 0; j < elevator; j++){
                timerValues.add(elevator2[j].dropPassengers(i));
                elevator2[j].addPassengers(floor, allFloors);
                elevator2[j].moveElevator();
            }


        }
        for (int i = 0; i < timerValues.size(); i++) {
            if(timerValues.get(i) != 0) {
                sum += timerValues.get(i);
                size ++;
            }
        }

        for (int i = 0; i < timerValues.size(); i++){
            if(timerValues.get(i) != 0 && max <= timerValues.get(i)){
                max = timerValues.get(i);
            }
        }

        for (int i = 0; i < timerValues.size(); i++){
            if(timerValues.get(i) != 0 && min > timerValues.get(i)){
                min = timerValues.get(i);
            }
        }

        int averageTime =  sum / size;

        System.out.println("--- This is average time: " + averageTime + " ticks ---");
        System.out.println("--- This is longest time: " + max + " ticks ---");
        System.out.println("--- This is shortest time: " + min + " ticks ---");



      //  System.out.println( Math.round((double) sum / elevator1.timer().size()));


        // Calculate the average and cast it to an int

       // System.out.println(elevator1.timer(elevator1.dropPassengers()));
       // System.out.println(elevator1.getUpElevator().peek());

//        System.out.println("--- Elevator is on floor " + elevator1.getCurrFloor() + " ---");
//        //System.out.println("--- Elevator is heading to " + elevator1.getDestFloor() + " ---");
//        System.out.println("--- Elevator has arrived at " + elevator1.getCurrFloor() + " ---");
//        System.out.println("--- Average length of time between passenger arrival and conveyance to the final destination ");

    }
}