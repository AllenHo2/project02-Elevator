import java.util.PriorityQueue;

public class Elevator {

    private int numFloor; //number of floors

    private int destFloor; //destination floor

    private int startFloor; //start floor

    private boolean goingUp; //is going up
    private  int capacity; //capacity of elevator
    private int amount;

    private PriorityQueue<Passenger> inElevator;

    private int tick;

    public Elevator(int floorNum, int elevatorCapacity, int amountOfElevators) {
        this.numFloor = floorNum;
        this.capacity = elevatorCapacity;
        this.amount = amountOfElevators;
        this.inElevator = new PriorityQueue<Passenger>();

    }


    public int loadPassengers(){
        return 2;
    }
    public int currFloor(){

        return 0;
    }
    public boolean getDirection() {
        if(getNumFloor() == getStartFloor() && getDestFloor() > getStartFloor()){
            for(int i = getStartFloor(); i < getDestFloor() && i < getNumFloor(); i++ ){
                setGoingUp(true);
            }
        }

        return false;
    }



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
}
