import java.util.*;

public class Elevator {

    private int numFloor; //number of floors

    private int destFloor; //destination floor

    private int startFloor; //start floor

    private boolean goingUp; //is going up
    private  int capacity; //capacity of elevator
    private int amount;

    public PriorityQueue<Passenger> getUpElevator() {
        return upElevator;
    }

    private PriorityQueue<Passenger> upElevator;
    private PriorityQueue<Passenger> downElevator;

    private Floor[] allFloors;
    private int tick;


    private int currFloor;

    private String structure;

    private float probability;

    public Elevator(int floorNum, int elevatorCapacity, int amountOfElevators, String structures, Floor[] allFloors, float probability) {
        this.numFloor = floorNum;
        this.probability = probability;
        this.capacity = elevatorCapacity;
        this.amount = amountOfElevators;
        this.allFloors =  allFloors;
        this.upElevator = new PriorityQueue<Passenger>();
        this.downElevator = new PriorityQueue<Passenger>(Collections.reverseOrder());
        this.structure = structures;
        this.currFloor = 1; //elevator starts at ground

    }
    public void moveElevator(){
        Passenger passenger = new Passenger(getNumFloor());
        addPassengers(passenger);
        if(goingUp){
            if(currFloor < passenger.getDestFloor() && passenger.getDestFloor() > 5 ){
                currFloor += 5;
                System.out.println("Current Floor: " + currFloor);
            } else {
                //currFloor += upElevator.peek() - currFloor;
                System.out.println("Current Floor: " + currFloor);
            }

            if (currFloor == passenger.getDestFloor()){
                dropPassengers(passenger);
            }
        }
    }

    public void addPassengers(Passenger passenger) {
        // Passenger passenger = new Passenger(getNumFloor());
        //Floor floor = new Floor(getNumFloor(), getStructure(), allFloors.getProbability());
        if(upElevator.size() > getCapacity() || downElevator.size() > getCapacity()){
            System.out.println("Elevator capacity exceeded. Cannot add passenger " + passenger);

        }else if(passenger.getDirection() && upElevator.size() <= getCapacity()){
            upElevator.add(passenger);
            System.out.println("Passenger is added to the up elevator " + passenger );
            capacity--;
            System.out.println("Capacity: " + capacity );

        } else if (!passenger.getDirection() && downElevator.size()<= getCapacity()){
            downElevator.add(passenger);
            System.out.println("Passenger is added to the down elevator " + passenger);
            capacity--;
            System.out.println("Capacity: " + capacity );
        }
        //return passenger;
    }


    public void load(Deque<Passenger> passengers){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            while(upElevator.size() <= capacity){ //while there is space in the elevator
                upElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
            }
        } else { //if going down
            while(downElevator.size() <= capacity) { //while there is space in the elevator
                downElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
            }
        }
    }

    public Passenger dropPassengers(Passenger passenger){
       // Floor floor = new Floor(getNumFloor(), getStructure(), allFloors.getProbability());
        if(currFloor == passenger.getDestFloor() && passenger.getDirection()){
            System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + upElevator.poll());
            capacity++;
            System.out.println("Capacity: " + capacity );
        } else if (currFloor == passenger.getDestFloor() && !passenger.getDirection()){
            System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + downElevator.poll());
            capacity++;
            System.out.println("Capacity: " + capacity );
        }
        return passenger;
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
