import java.util.Random;

public class Passenger implements Comparable<Passenger> {

    private int startFloor; // starting floor
    private int destFloor; //destination floor
    private boolean goingUp; //if the elevator is going up or not
    private int numFloors; // number of floors
    private int nearestDestination;
    private int timeCounter;

    public Passenger(int numFloor, int startFloor, int time) { //constructs Passenger with argument amount of  floors
        this.numFloors = numFloor;//sets the number of floors to the amount passed into the argument (default 32)
        this.startFloor = startFloor; //starting floor from Floors class
        this.timeCounter = time; //time counter
        do {
            this.destFloor = getDestinationFloor(numFloor); //destination floor generates but has to be different than startFloor
        } while (destFloor == startFloor);
        goingUp = destFloor > startFloor; //goingUp is true if destFloor is higher than startFloor
        this.nearestDestination = destFloor - startFloor;
        this.timeCounter = time; //create time, when passengers are dropped off, then pass the time to elevator and have main return the time
       System.out.println("Passenger is on: " + startFloor + "; Passenger wants to go to: " + destFloor + "; Passenger is " + nearestDestination + " away");
    }

   @Override
    public int compareTo(Passenger other) { //compares a passenger to another
       return Integer.compare(this.destFloor, other.destFloor);
    }

    public int getDestinationFloor(int numFloors) { //gets random destination for the passenger
        Random random = new Random();
        int destinationFloor = random.nextInt(numFloors) + 1;
        while (destinationFloor > numFloors - 1) {
            destinationFloor = random.nextInt(numFloors) + 1;
        }
        return destinationFloor;
    }

    public int getStartFloor() {
        return startFloor;
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
