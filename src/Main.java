// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.*;
import java.util.*;
import java.util.Properties;
import java.io.File;
public class Main {
    public static void main(String[] args) throws Exception {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);


        String structure = p.getProperty("structures");
        int floor = Integer.parseInt(p.getProperty("floors"));
        double probability = Float.parseFloat(p.getProperty("passengers"));
        int elevator = Integer.parseInt(p.getProperty("elevators"));
        int capacity = Integer.parseInt(p.getProperty("elevatorCapacity"));
        int ticks = Integer.parseInt(p.getProperty("duration"));
        int counter = 0;
//        System.out.println(floor);
//        System.out.println(probability);
//        System.out.println(elevator);
//        System.out.println(capacity);
//        System.out.println(ticks);
        Floor floors = new Floor(floor, structure);
        Passenger passengers = new Passenger(floor);

       // passengers.levels(floors.floorLevel(p.getProperty("floors"));

        for (int i = 0; i < ticks; i++){
            counter++;
        }
        Elevator elevator1 = new Elevator(floor);
        System.out.println("--- Elevator is on floor " + elevator1.currFloor() + " ---");
        System.out.println("--- Elevator is heading to " + elevator1.getDestFloor() + " ---");
        System.out.println("--- Elevator has arrived at " + elevator1.currFloor() + " ---");

      //   System.out.println(floors.floorLevel(p.getProperty("floors")));
      //   String structures = p.getProperty("structures");
//         System.out.println(p.getProperty("structures")); //Elevator class
//         System.out.println(p.getProperty("floors")); //Floor Class
//         System.out.println(p.getProperty("passengers")); //Passengers Class
//         System.out.println(p.getProperty("elevators")); //Elevator Class
//         System.out.println(p.getProperty("elevatorCapacity")); //Elevator Class
//         System.out.println(p.getProperty("duration")); //Analysis Class
    }
}