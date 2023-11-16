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
    private int nearestDestination;
    private int timeCounter;

    public Passenger(int numFloor, int startFloor) { //constructs Passenger with argument 32 floors
        this.numFloors = numFloor;//sets the number of floors to the amount passed into the argument (default 32)
        this.startFloor = startFloor;
       // Random rand = new Random(); //random object
        //this.startFloor = rand.nextInt(numFloor) + 1; //start floor is random
        do {
            this.destFloor = getDestinationFloor(numFloor); //destination floor generates but has to be different than startFloor
        } while (destFloor == startFloor);
        goingUp = destFloor > startFloor; //goingUp is true if destFloor is higher than startFloor
        this.nearestDestination = destFloor - startFloor;
        this.timeCounter = 0;
       System.out.println("Passenger is on: " + startFloor + "; Passenger wants to go to: " + destFloor + "; Passenger is " + nearestDestination + " away");
    }
//create time, when passengers are dropped off, then pass the time to elevator and have main return the time

   @Override
    public int compareTo(Passenger other) { //compares a passenger to another
       return Integer.compare(this.destFloor, other.destFloor);
    }

    public int getDestinationFloor(int numFloors) {
        Random random = new Random();
        int destinationFloor = random.nextInt(numFloors) + 1;
        while (destinationFloor > numFloors - 1) {
            destinationFloor = random.nextInt(numFloors) + 1;
        }
        return destinationFloor;
    }

    public boolean getDirection() {
        if(getDestFloor() > getStartFloor()){
                setGoingUp(true);
                System.out.println("Passenger is Going Up");
                return isGoingUp();
        } else {
            System.out.println("Passenger is Going Down");
            return isGoingUp();
        }
    }

    public int getStartFloor() {
        return startFloor;
    }
    public boolean isGoingUp() {
        return goingUp;
    }
    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }
    public int getDestFloor() {
        return destFloor;
    }
    public int getNearestDestination() {
        return nearestDestination;
    }
    public int getTimeCounter() {
        return timeCounter;
    }


}
