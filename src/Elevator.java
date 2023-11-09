import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
public class Elevator {

    private int numFloor; //number of floors

    private int destFloor; //destination floor

    private int startFloor; //start floor

    private boolean goingUp; //is going up
    private  int capacity; //capacity of elevator
    private int amount;

    private PriorityQueue<Passenger> upElevator;
    private PriorityQueue<Passenger> downElevator;

    private Floor[] allFloors;
    private int tick;


    private int currFloor;

    private String structure;
    public Elevator(int floorNum, int elevatorCapacity, int amountOfElevators, String structures, Floor[] allFloors) {
        this.numFloor = floorNum;
        this.capacity = elevatorCapacity;
        this.amount = amountOfElevators;
        this.allFloors = allFloors;
        this.upElevator = new PriorityQueue<Passenger>();
        this.downElevator = new PriorityQueue<Passenger>(Collections.reverseOrder());
        this.structure = structures;
        this.currFloor = 1; //elevator starts at ground

    }
    public void moveElevator(){
        Passenger passenger = new Passenger(getNumFloor());
        addPassengers(passenger);
        if(passenger.getDirection()){
            if(currFloor < passenger.getDestFloor() && currFloor <= 5){
                currFloor++;
            }
        }
    }

    public Passenger addPassengers(Passenger passenger){
       // Passenger passenger = new Passenger(getNumFloor());
        Floor floor = new Floor(getNumFloor(), getStructure());
        if(passenger.getDirection() && upElevator.size() <= getCapacity()){
            upElevator.add(floor.queuePassenger(passenger));
            System.out.println("Passenger is added to the up elevator" );
            capacity--;
            System.out.println("Capacity: " + capacity );

        } else if (!passenger.getDirection() && downElevator.size()<= getCapacity()){
            downElevator.add(floor.queuePassenger(passenger));
            System.out.println("Passenger is added to the down elevator" );
            capacity--;
            System.out.println("Capacity: " + capacity );
        }
        return passenger;
    }

    public void dropPassengers(){
        Passenger passenger = new Passenger(getNumFloor());
        Floor floor = new Floor(getNumFloor(), getStructure());
        if(currFloor == passenger.getDestFloor() && passenger.getDirection()){
            upElevator.poll();
            capacity++;
            System.out.println("Capacity: " + capacity );
        } else if (currFloor == passenger.getDestFloor() && !passenger.getDirection()){
            downElevator.poll();
            capacity++;
            System.out.println("Capacity: " + capacity );
        }
    }

//
//    public boolean getDirection() {
//        if(getNumFloor() == getStartFloor() && getDestFloor() > getStartFloor()){
//            for(int i = getStartFloor(); i < getDestFloor() && i < getNumFloor(); i++ ){
//                setGoingUp(true);
//            }
//        }
//
//        return false;
//    }



    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public int getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(int destFloor) {
        this.destFloor = destFloor;
    }


    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public void addPassenger(Passenger poll) {

    }
    public int getCapacity() {
        return capacity;
    }

    public int getAmount() {
        return amount;
    }
    public String getStructure() {
        return structure;
    }

    public int getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(int currFloor) {
        this.currFloor = currFloor;
    }
}
