import java.lang.Math;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * Put them into a min heap
 *
 * Passengers should know where they're starting & destination floor
 * Boolean for is going up (true if up; false if down)
 *
 * If up; put all passengers into a min heap (peek to figure out minimum floor that passengers want to go to ) __>
 * next floor that you want to go to
 *
 * Make passengers comparable by comparing them to the floor that they want to go to
 */
public class Passenger {
    Queue<String> queue = new LinkedList<>();
    private int passengers;

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int levels(int level){
        Passenger passenger = new Passenger();
        Floor floors = new Floor();
        Random rand = new Random();
        //floors = 2 + rand.nextInt(31);
        passenger.setPassengers(1);
        return passengers;
    }
}
