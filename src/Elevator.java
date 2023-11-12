import java.util.*;

public class Elevator {

    private int numFloor; //number of floors

    private int destFloor; //destination floor

    private int startFloor; //start floor

    private boolean goingUp; //is going up
    private boolean floorinBetween;
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

    private int counterCapacity;

    private int firstNonEmptyFloor;

    private int lastNonEmptyFloor;

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
        this.floorinBetween = false;
        this.counterCapacity = elevatorCapacity;
        this.firstNonEmptyFloor = 0;
        this.lastNonEmptyFloor = 0;
    }
    public void moveElevator(){
        Iterator<Passenger> iterator;

        if (currFloor == numFloor || currFloor >= findLastNonEmptyFloor()) {
            setGoingUp(false);
        } else if (currFloor == 0 || currFloor <= findLastNonEmptyFloor()) {
            setGoingUp(true);
        }

        for (Floor allFloor : allFloors) {
            addPassengers(allFloor.getUpQueue()); //add all the passengers from the current floor into the heap
            addPassengers(allFloor.getDownQueue()); //add all the passengers from the current floor into the heap
        }

            if (goingUp) { //if we go up
                    Passenger passenger = upElevator.peek(); //looks at the passenger at the top

                    if (passenger != null) {
                        System.out.println("This is passenger:" + passenger.getDestFloor());
                        System.out.println("This is also passenger:" + passenger);
                        iterator = upElevator.iterator(); //next passenger of the elevator
                        //System.out.println(iterator.toString());
                     if ((passenger.getDestFloor() - getCurrFloor()) <= (getNumFloor() - getCurrFloor()) && passenger.getDestFloor() <= getCurrFloor() + 5 && passenger.getDestFloor() > getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
//                    System.out.println(passenger.getDestFloor());
//                    System.out.println(getCurrFloor());
//                    System.out.println(passenger.getDestFloor() - getCurrFloor());
                            this.currFloor += passenger.getDestFloor() - getCurrFloor();
                            dropPassengers(passenger);
                            counterCapacity++;
                            System.out.println("Current Floor: " + currFloor);
                        } else if (passenger.getDestFloor() == currFloor){
                            dropPassengers(passenger);
                            counterCapacity ++;
                            System.out.println("Current Floor: " + currFloor);
                        } else if (getCurrFloor() + 5 < passenger.getDestFloor() && getCurrFloor() + 5 <= numFloor) { //if the destination of the passenger is more than 5, and it is not past the roof
                         for (int i = currFloor + 1; i <= 5; i++) {
                             if (iterator.hasNext()) {
                                 passenger = iterator.next();
                                 if (passenger.getDestFloor() != i) {
                                     this.currFloor = i;
                                     System.out.println("Current Floor: " + currFloor);
                                     setFloorinBetween(true);
                                     break;
                                 }
                             }
                         }
                         if(!floorinBetween) {
                             this.currFloor += 5; //add 5 floors
                             System.out.println("Current Floor: " + currFloor);
                         }
                        }
                    } else{
                        if(currFloor <= currFloor + 5 && currFloor - findFirstNonEmptyFloor()  <= currFloor + 5){
                            findFirstNonEmptyFloor();
                            System.out.println("Current Floor: " + currFloor);
                            currFloor += findFirstNonEmptyFloor();
                            System.out.println("Current Floor: " + currFloor);
                        }
                        else{
                            System.out.println("Current Floor: " + currFloor);
                            currFloor += 5;
                            System.out.println("Current Floor: " + currFloor);
                        }
                     }
            } else {
                    Passenger passenger = downElevator.peek(); //looks at the passenger at the top
                    if (passenger != null) {
                        iterator = downElevator.iterator(); //next passenger of the elevator
                        if (getCurrFloor() - 5 > passenger.getDestFloor() && getCurrFloor() - 5 >= 0) { //if the destination of the passenger is more than 5, and it is not past the roof
                            for (int i = currFloor - 1; i >= -5; i--) {
                                if (iterator.hasNext()) {
                                    passenger = iterator.next();
                                    if (passenger.getDestFloor() != i) {
                                        this.currFloor = i;
                                        System.out.println("Current Floor: " + currFloor);
                                        setFloorinBetween(true);
                                        break;
                                    }
                                }
                            }
                            if(!floorinBetween) {
                                this.currFloor -= 5; //add 5 floors
                                System.out.println("Current Floor: " + currFloor);
                            }
                        } else if (( getCurrFloor() - passenger.getDestFloor()) >= 0 && passenger.getDestFloor() >= getCurrFloor() - 5 && passenger.getDestFloor() < getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
//                    System.out.println(passenger.getDestFloor());
//                    System.out.println(getCurrFloor());
//                    System.out.println(passenger.getDestFloor() - getCurrFloor());
                            this.currFloor += getCurrFloor() - passenger.getDestFloor();
                            dropPassengers(passenger);
                            counterCapacity++;
                            System.out.println("Current Floor: " + currFloor);
                        } else if (passenger.getDestFloor() == currFloor){
                            dropPassengers(passenger);
                            counterCapacity++;
                            System.out.println("Current Floor: " + currFloor);
                        }
                } else{
                        if(currFloor >= currFloor - 5 &&  findFirstNonEmptyFloor() - currFloor  >= currFloor - 5){
                            System.out.println("Current Floor: " + currFloor);
                            currFloor += findFirstNonEmptyFloor();
                            System.out.println("Current Floor: " + currFloor);
                        }
                        else{
                            System.out.println("Current Floor: " + currFloor);
                            currFloor += 5;
                            System.out.println("Current Floor: " + currFloor);
                        }
                    }

            }
    }




    public int findFirstNonEmptyFloor(){
        if(goingUp){
            for (int i = 0; i < numFloor; i++) {
                if (!allFloors[i].getUpQueue().isEmpty()) {
                    firstNonEmptyFloor = i;
                    break;
                }
            }
        } else {
            for (int i = numFloor; i > 0 ; i--) {
                if (!allFloors[i].getDownQueue().isEmpty()) {
                    firstNonEmptyFloor = i;
                    break;
                }
            }
        }
        return firstNonEmptyFloor;
    }

    public int findLastNonEmptyFloor(){
        if(goingUp) {
            for (int i = 0; i < numFloor; i++) {
                if (!allFloors[i].getUpQueue().isEmpty()) {
                    lastNonEmptyFloor = i;
                }
            }
        } else {
            for (int i = numFloor; i > 0; i--) {
                if (!allFloors[i].getUpQueue().isEmpty()) {
                    lastNonEmptyFloor = i;
                }
            }
        }
        return lastNonEmptyFloor;
    }

    public void addPassengers(Deque<Passenger> passengers){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            while(upElevator.size() < capacity && !passengers.isEmpty() && passengers.peek().getDestFloor() > currFloor){ //while there is space in the elevator
                System.out.println("Passenger is added to the up elevator " + passengers.peek() );
                upElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
                counterCapacity--;
                System.out.println("Current Floor: " + currFloor);
               // System.out.println("Capacity: " + counterCapacity );
            }
        } else { //if going down
            while(downElevator.size() < capacity && !passengers.isEmpty()) { //while there is space in the elevator
                System.out.println("Passenger is added to the down elevator " + passengers.peek() );
                downElevator.add(passengers.poll()); //poll all the passengers from the floor into the elevator going down
                counterCapacity--;
                System.out.println("Current Floor: " + currFloor);
              //  System.out.println("Capacity: " + counterCapacity );
            }
        }
    }

    public void dropPassengers(Passenger passenger){
       // Floor floor = new Floor(getNumFloor(), getStructure(), allFloors.getProbability());
        if(currFloor == passenger.getDestFloor() && goingUp){
            if (upElevator.contains(passenger)) { // Check if the passenger is in the upElevator
                System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + upElevator.poll());
               // capacity++;
                System.out.println("Capacity: " + capacity);
            }
        } else if (currFloor == passenger.getDestFloor() && !goingUp) {
            if (downElevator.contains(passenger)) { // Check if the passenger is in the downElevator
                System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + downElevator.poll());
                // capacity++;
                System.out.println("Capacity: " + capacity);
            }
        }
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
    public String getStructure() {
        return structure;
    }

    public int getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(int currFloor) {
        this.currFloor = currFloor;
    }
    public void setFloorinBetween(boolean floorinBetween) {
        this.floorinBetween = floorinBetween;
    }
}
