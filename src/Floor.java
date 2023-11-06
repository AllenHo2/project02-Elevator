import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayDeque;
public class Floor {
    private int floorNum;
    private int capacity;
    private Queue<Passenger> upQueue;
    private Queue<Passenger> downQueue;

    private int currFloor;

    public Floor(int floorNum, String structures) {
        this.floorNum = floorNum; //number of floors
        //  private int numFloors;
        if(structures.equals("linked")) {
            this.upQueue = new PriorityQueue<>();
            this.downQueue = new PriorityQueue<>();
        } else if (structures.equals("array")){
            this.upQueue = new ArrayDeque<>();
            this.downQueue = new ArrayDeque<>();
        } else {
            this.upQueue = new PriorityQueue<>();
            this.downQueue = new PriorityQueue<>();
        }
    }

    public void addPassenger(Passenger passenger) {
        if (passenger.getDestinationFloor(floorNum) > floorNum && upQueue.size() != getCapacity()) { //if passenger wants to go to a floor higher than the floor we are on, then we add them to the queue
            upQueue.add(passenger);
        } else if (passenger.getDestinationFloor(floorNum) < floorNum && downQueue.size() != getCapacity()){
            downQueue.add(passenger); //else add them to the queue going down
        }
    }


    public void dropPassengers(Elevator elevator) {
        if (elevator.getDirection() == elevator.isGoingUp()) { //if the elevator is going up
            while (!upQueue.isEmpty() && upQueue.peek().getDestinationFloor(floorNum) == getCurrFloor()) { //if UpQ is empty and the passenger has destination floor = currentfloor then drop them off
                elevator.addPassenger(upQueue.poll());
            }
        } else if (elevator.getDirection() != elevator.isGoingUp()) {
            while (!downQueue.isEmpty() && downQueue.peek().getDestinationFloor(floorNum) == getCurrFloor()) {
                elevator.addPassenger(downQueue.poll());
            }
        }
    }


    public Passenger getRandomPassenger() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return getNextUpPassenger();
        } else {
            return getNextDownPassenger();
        }
    }

//    public int getNumFloors() {
//        return numFloors;
//    }

//    public void setNumFloors(int numFloors) {
//        this.numFloors = numFloors;
//    }
    public boolean hasUpPassengers() {
        return !upQueue.isEmpty();
    }

    public boolean hasDownPassengers() {
        return !downQueue.isEmpty();
    }

    public Passenger getNextUpPassenger() {
        return upQueue.peek();
    }

    public Passenger getNextDownPassenger() {
        return downQueue.peek();
    }

    public Passenger removeNextUpPassenger() {
        return upQueue.poll();
    }

    public Passenger removeNextDownPassenger() {
        return downQueue.poll();
    }


    public int getFloorNum() {
        return floorNum;
    }
    public Queue<Passenger> getUpQueue() {
        return upQueue;
    }

    public void setUpQueue(Queue<Passenger> upQueue) {
        this.upQueue = upQueue;
    }

    public Queue<Passenger> getDownQueue() {
        return downQueue;
    }

    public void setDownQueue(Queue<Passenger> downQueue) {
        this.downQueue = downQueue;
    }

    public int getCapacity() {
        return capacity;
    }
    public int getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(int currFloor) {
        this.currFloor = currFloor;
    }



}
    /**randomly generate a floor within 2 & 32, then pass it over to the passengers class
     *Once the passenger class gets a random floor from this Floor object, assign that passenger the number
     * Try in main, to set floor as an argument in the Passenger class's parameter to use the propertu file
     *
     * 2 queues ==> one for down passengers and one for up passengers
     * One if they dropped off everyone & find the closest next passenger and go in that direction
     * method for dropping passengers
     * method for adding passengers
     *
     * When finding a floor, then you have to find out if its going up or down
     *
     * Create a random integer and go to that floor and have it keep getting on random floors
     *
     *
     * Go up until all passengers that want to go up have been dropped off
     *
     * Can let on more than one passenger
     * @param level
     * @return
     */

//    private int floorNums;
//
//    public int getFloorNums() {
//        return floorNums;
//    }
//
//    public void setFloorNums(int floorNums) {
//        this.floorNums = floorNums;
//    }
//
//    public Floor(){
//       int levels = 32;
//       Passenger passenger = new Passenger();
//       int counter = 0;
//        for(int i = 0; i < levels; i++){
//            counter ++;
//        }
//
//    }
//
//    public void dropPassengers(){
//        if()
//    }
//    public void addPassengers(){
//
//    }
//}
