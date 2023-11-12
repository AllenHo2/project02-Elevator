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
    public PriorityQueue<Passenger> getDownElevator() {
        return downElevator;
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
        this.currFloor = 0; //elevator starts at ground
        this.goingUp = true;
    }
    public void moveElevator(){
        //Passenger passenger = new Passenger(getNumFloor());
        //addPassengers(passenger);
//        Queue<Passenger> currentElevator;
        Iterator<Passenger> iterator;
        //addPassengers(allFloors);
        if(goingUp){
            if(allFloors[getCurrFloor()].getUpQueue() != null) {
                addPassengers(allFloors[getCurrFloor()].getUpQueue());
                Passenger passenger1 = upElevator.peek();
                iterator = upElevator.iterator();
                //System.out.println(iterator.toString());
                if (passenger1 != null && getCurrFloor() + 5 < passenger1.getDestFloor() && passenger1.getDestFloor() > getCurrFloor() + 5 && getCurrFloor() + 5 <= numFloor) {
                    this.currFloor += 5;
                    System.out.println("Current Floor: " + currFloor);
                } else if (passenger1 != null && (passenger1.getDestFloor() - getCurrFloor()) <= (getNumFloor() - getCurrFloor()) && passenger1.getDestFloor() <= getCurrFloor() + 5 && passenger1.getDestFloor() > getCurrFloor()) {
//                    System.out.println(passenger1.getDestFloor());
//                    System.out.println(getCurrFloor());
//                    System.out.println(passenger1.getDestFloor() - getCurrFloor());
                    this.currFloor += passenger1.getDestFloor() - getCurrFloor();
                    dropPassengers(passenger1);
                    System.out.println("Current Floor: " + currFloor);
                } else if (currFloor == numFloor || currFloor + 5 > numFloor || !iterator.hasNext()) {
                    setGoingUp(false);
                }
            }
        } else if (!goingUp){
            if(allFloors[getCurrFloor()].getDownQueue() != null) {
                addPassengers(allFloors[getCurrFloor()].getDownQueue());
                Passenger passenger2 = downElevator.peek();
                iterator = downElevator.iterator();
                if (passenger2 != null && this.currFloor > passenger2.getDestFloor() && passenger2.getDestFloor() < getCurrFloor() - 5 && getCurrFloor() - 5 >= numFloor) {
                    this.currFloor -= 5;
                    System.out.println("Current Floor: " + currFloor);
                } else if (passenger2 != null && (getCurrFloor() - passenger2.getDestFloor()) >= 0 && passenger2.getDestFloor() >= getCurrFloor() - 5 && passenger2.getDestFloor() < getCurrFloor()) {
                    this.currFloor += passenger2.getDestFloor() - getCurrFloor();
                    dropPassengers(passenger2);
                    System.out.println("Current Floor: " + currFloor);
                } else if (this.currFloor == 0 || currFloor - 5 < 0 || !iterator.hasNext()) {
                    setGoingUp(true);
                }

            } else {
                boolean noMorePassengersAbove = true;
                for (int i = currFloor + 1; i <= numFloor; i++) {
                    if (allFloors[i].getUpQueue() != null && !allFloors[i].getUpQueue().isEmpty()) {
                        noMorePassengersAbove = false;
                        break;
                    }
                }

                if (noMorePassengersAbove) {
                    setGoingUp(true);
                }
            }
//            else {
//                if (allFloors[currFloor].getUpQueue() != null && allFloors[currFloor].getDownQueue() != null) {
//                    return;
//                }
//            }

        }
    }

//    public void addPassengers(Passenger passenger) {
//        // Passenger passenger = new Passenger(getNumFloor());
//        //Floor floor = new Floor(getNumFloor(), getStructure(), allFloors.getProbability());
//        if(upElevator.size() > getCapacity() || downElevator.size() > getCapacity()){
//            System.out.println("Elevator capacity exceeded. Cannot add passenger " + passenger);
//
//        }else if(passenger.getDirection() && upElevator.size() <= getCapacity()){
//            upElevator.add(passenger);
//            System.out.println("Passenger is added to the up elevator " + passenger );
//            capacity--;
//            System.out.println("Capacity: " + capacity );
//
//        } else if (!passenger.getDirection() && downElevator.size()<= getCapacity()){
//            downElevator.add(passenger);
//            System.out.println("Passenger is added to the down elevator " + passenger);
//            capacity--;
//            System.out.println("Capacity: " + capacity );
//        }
//        //return passenger;
//    }


    public void addPassengers(Deque<Passenger> passengers){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            while(upElevator.size() <= capacity && !passengers.isEmpty()){ //while there is space in the elevator
                System.out.println("Passenger is added to the up elevator " + passengers.peek() );
                upElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
                capacity--;
                System.out.println("Capacity: " + capacity );
            }
        } else { //if going down
            while(downElevator.size() <= capacity && !passengers.isEmpty()) { //while there is space in the elevator
                System.out.println("Passenger is added to the down elevator " + passengers.peek() );
                downElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
                capacity--;
                System.out.println("Capacity: " + capacity );
            }
        }
    }

    public void dropPassengers(Passenger passenger){
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
