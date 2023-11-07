import java.lang.Math;
import java.util.Random;
import java.util.Properties;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Passenger implements Comparable<Passenger> {
    private int startFloor; // starting floor
    private int destFloor; //destination floor
    private boolean goingUp; //if the elevator is going up or not
    private int numFloors; // number of floors

    public Passenger(int numFloor) { //constructs Passenger with argument 32 floors
        this.numFloors = numFloor;//sets the number of floors to the amount passed into the argument (default 32)
        Random rand = new Random(); //random object
        startFloor = rand.nextInt(numFloor) + 1; //start floor is random
        do {
            destFloor = getDestinationFloor(numFloor); //destination floor generates but has to be different than startFloor
        } while (destFloor == startFloor);
        goingUp = destFloor > startFloor; //goingUp is true if destFloor is higher than startFloor
        System.out.println("Passenger is on: " + startFloor + "; Passenger wants to go to: " + destFloor);
    }


    public int compareTo(Passenger other) { //compares a passenger to another
            return Integer.compare(this.getDestFloor(), other.getDestFloor());
    }

    public int getDestinationFloor(int numFloors) {
        Random random = new Random();
        int destinationFloor = random.nextInt(numFloors) + 1;
        while (destinationFloor > numFloors) {
            destinationFloor = random.nextInt(numFloors) + 1;
        }
        return destinationFloor;
    }

    public boolean getDirection() {
        if(getDestFloor() > getStartFloor()){
                setGoingUp(true);
                System.out.println("Passenger is Going Up");
                return isGoingUp();
        }
        System.out.println("Passenger is Going Down");
        return isGoingUp();
    }

    public int getStartFloor() {
        return startFloor;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }


    public int getNumFloors() {
        return numFloors;
    }

//    public void setNumFloors(int numFloors) {
//        this.numFloors = numFloors;
//    }

    public int getDestFloor() {
        return destFloor;
    }

//    public void setDestFloor(int destFloor) {
//        this.destFloor = destFloor;
//    }


}
