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

        Floor floors = new Floor();
        Passenger passengers = new Passenger();
        passengers.levels(floors.floorLevel(p.getProperty("floors"));
        System.out.println(floors.floorLevel(p.getProperty("floors")));
        String structures = p.getProperty("structures");
        System.out.println(p.getProperty("structures")); //Elevator class
      //  System.out.println(p.getProperty("floors")); //Floor Class
        System.out.println(p.getProperty("passengers")); //Passengers Class
        System.out.println(p.getProperty("elevators")); //Elevator Class
        System.out.println(p.getProperty("elevatorCapacity")); //Elevator Class
        System.out.println(p.getProperty("duration")); //Analysis Class
    }
}