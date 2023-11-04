import java.lang.Math;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Passenger implements Comparable<Passenger> {
    private int startFloor;
    //private int destFloor;
    private boolean goingUp;

    private int numFloors;

    public Passenger(int numFloor) { //constructs Passenger with argument 32 floors
        setNumFloors(numFloor);
        Random rand = new Random(); //random object
        startFloor = rand.nextInt(numFloor) + 1; //start floor is random
        do {
            getDestinationFloor(numFloor); //destination floor generates but has to be different than startFloor
        } while (getDestinationFloor(numFloor) == startFloor);
        goingUp = getDestinationFloor(numFloor) > startFloor; //goingUp is true if destFloor is higher than startFloor
    }


    public int compareTo(Passenger other) { //compares a passenger to another
        if (goingUp) {
            return Integer.compare(getDestinationFloor(numFloors), other.getDestinationFloor(numFloors));
        } else {
            return Integer.compare(other.getDestinationFloor(numFloors), numFloors);
        }
    }

    public int getStartFloor() {
        return startFloor;
    }

//    public int getDestFloor() {
//        return destFloor;
//    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public int getDestinationFloor(int numFloors) {
        Random random = new Random();
        int destinationFloor = random.nextInt(numFloors) + 1;
        while (destinationFloor > numFloors) {
            destinationFloor = random.nextInt(numFloors) + 1;
        }
        return destinationFloor;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(int numFloors) {
        this.numFloors = numFloors;
    }


    // public static void main(String[] args) {
    //     int numFloors = 10;
    //     PriorityQueue<Passenger> minHeap = new PriorityQueue<>();
    //     for (int i = 0; i < 10; i++) {
    //         Passenger p = new Passenger(numFloors);
    //         minHeap.offer(p);
    //     }
    //     while (!minHeap.isEmpty()) {
    //         Passenger p = minHeap.poll();
    //         System.out.println("Passenger wants to go from floor " + p.getStartFloor() +
    //                 " to floor " + p.getDestFloor() + " (going up? " + p.isGoingUp() + ")");
    //     }
    // }
}
//public class Passenger {
//    Queue<String> queue = new LinkedList<>();
//    private int passengers;
//
//    public int getPassengers() {
//        return passengers;
//    }
//
//    public void setPassengers(int passengers) {
//        this.passengers = passengers;
//    }
//
//    public int levels(int level){
//        Passenger passenger = new Passenger();
//        Floor floors = new Floor();
//        Random rand = new Random();
//        //floors = 2 + rand.nextInt(31);
//        passenger.setPassengers(1);
//        return passengers;
//    }
//}