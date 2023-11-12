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
    public void moveElevator(int FirstNonEmptyFloor, int lastNonEmptyFloor){
        //Passenger passenger = new Passenger(getNumFloor());
        //addPassengers(passenger);
   //     Deque<Passenger> allFloors[getCurrFloor(;.getUpQueue(
        Iterator<Passenger> iterator;

        if (currFloor == numFloor || currFloor >= lastNonEmptyFloor) {
            setGoingUp(false);
        } else if (currFloor == 0) {
            setGoingUp(true);
        }

        //addPassengers(allFloors);
            if (goingUp) { //if we go up
                if (allFloors[getCurrFloor()].getUpQueue() != null) { // if the current floor is not empty
                    addPassengers(allFloors[getCurrFloor()].getUpQueue()); //add all the passengers from the current floor into the heap
                    Passenger passenger1 = upElevator.peek(); //looks at the passenger at the top
                    if (passenger1 != null) {
                        iterator = upElevator.iterator(); //next passenger of the elevator
                        //System.out.println(iterator.toString());
                        for (int i = currFloor + 1; i <= 5; i++) { //in next floor, as long as i is less than passengers destination floor i ++
                            if (!allFloors[i].getUpQueue().isEmpty()) { //if there is a queue in between the floors not empty
                                this.currFloor += i - getCurrFloor(); //go to current floor
                                addPassengers(allFloors[getCurrFloor()].getUpQueue()); //put all the passengers from floor into heap
                                System.out.println("Current Floor: " + currFloor);
                                break; //then break out
                            }
                        }
                        if (getCurrFloor() + 5 < passenger1.getDestFloor() && getCurrFloor() + 5 <= numFloor) { //if the destination of the passenger is more than 5, and it is not past the roof
                            this.currFloor += 5; //add 5 floors
                            System.out.println("Current Floor: " + currFloor);
                        } else if ((passenger1.getDestFloor() - getCurrFloor()) <= (getNumFloor() - getCurrFloor()) && passenger1.getDestFloor() <= getCurrFloor() + 5 && passenger1.getDestFloor() > getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
//                    System.out.println(passenger1.getDestFloor());
//                    System.out.println(getCurrFloor());
//                    System.out.println(passenger1.getDestFloor() - getCurrFloor());
                            this.currFloor += passenger1.getDestFloor() - getCurrFloor();
                            dropPassengers(passenger1);
                            System.out.println("Current Floor: " + currFloor);
                        } else if (passenger1.getDestFloor() == currFloor){
                            dropPassengers(passenger1);
                            System.out.println("Current Floor: " + currFloor);
                        }
                    } else {
                        if(currFloor <= currFloor + 5 && currFloor - FirstNonEmptyFloor  <= currFloor + 5){
                            System.out.println("Current Floor: " + currFloor);
                            currFloor += FirstNonEmptyFloor;
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












            else {
//                for (int i = numFloor; i > 0; i--) {
//                    if (!allFloors[i].getDownQueue().isEmpty()) {
//                        lastNonEmptyFloor = i;
//                    }
//                }
//                for (int i = numFloor; i > 0; i--) {
//                    if (!allFloors[i].getDownQueue().isEmpty()) {
//                        firstNonEmptyFloor = i;
//                        break;
//                    }
//                }
                if (allFloors[getCurrFloor()].getDownQueue() != null) { // if the current floor is not empty
                    addPassengers(allFloors[getCurrFloor()].getDownQueue()); //add all the passengers from the current floor into the heap
                    Passenger passenger1 = downElevator.peek(); //looks at the passenger at the top
                    if (passenger1 != null) {
                        iterator = downElevator.iterator(); //next passenger of the elevator
                        //System.out.println(iterator.toString());
                        for (int i = currFloor - 1; i <= 5; i++) { //in next floor, as long as i is less than passengers destination floor i ++
                            if (!allFloors[i].getUpQueue().isEmpty()) { //if there is a queue in between the floors not empty
                                this.currFloor += getCurrFloor() - i; //go to current floor
                                addPassengers(allFloors[getCurrFloor()].getUpQueue()); //put all the passengers from floor into heap
                                System.out.println("Current Floor: " + currFloor);
                                break; //then break out
                            }
                        }
                        if (getCurrFloor() - 5 > passenger1.getDestFloor() && getCurrFloor() - 5 >= 0) { //if the destination of the passenger is more than 5, and it is not past the roof
                            this.currFloor -= 5; //add 5 floors
                            System.out.println("Current Floor: " + currFloor);
                        } else if (( getCurrFloor() - passenger1.getDestFloor()) >= 0 && passenger1.getDestFloor() >= getCurrFloor() - 5 && passenger1.getDestFloor() < getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
//                    System.out.println(passenger1.getDestFloor());
//                    System.out.println(getCurrFloor());
//                    System.out.println(passenger1.getDestFloor() - getCurrFloor());
                            this.currFloor += getCurrFloor() - passenger1.getDestFloor();
                            dropPassengers(passenger1);
                            System.out.println("Current Floor: " + currFloor);
                        } else if (passenger1.getDestFloor() == currFloor){
                            dropPassengers(passenger1);
                            System.out.println("Current Floor: " + currFloor);
                        }
//                        else {
//                            if(currFloor >= currFloor - 5 && currFloor - FirstNonEmptyFloor  <= currFloor + 5){
//                                System.out.println("Current Floor: " + currFloor);
//                                currFloor += FirstNonEmptyFloor;
//                                System.out.println("Current Floor: " + currFloor);
//                            }
//                            else{
//                                System.out.println("Current Floor: " + currFloor);
//                                currFloor += 5;
//                                System.out.println("Current Floor: " + currFloor);
//                            }
//                        }
                    }
                }
//                if (allFloors[getCurrFloor()].getDownQueue() != null) {
//                    addPassengers(allFloors[getCurrFloor()].getDownQueue());
//                    Passenger passenger2 = downElevator.peek();
//                    iterator = downElevator.iterator();
//                    if (passenger2 != null && this.currFloor > passenger2.getDestFloor() && passenger2.getDestFloor() < getCurrFloor() - 5 && getCurrFloor() - 5 >= numFloor) {
//                        this.currFloor -= 5;
//                        System.out.println("Current Floor: " + currFloor);
//                    } else if (passenger2 != null && (getCurrFloor() - passenger2.getDestFloor()) >= 0 && passenger2.getDestFloor() >= getCurrFloor() - 5 && passenger2.getDestFloor() < getCurrFloor()) {
//                        this.currFloor += passenger2.getDestFloor() - getCurrFloor();
//                        dropPassengers(passenger2);
//                        System.out.println("Current Floor: " + currFloor);
//                    } else if (this.currFloor == 0 || currFloor - 5 < 0 || !iterator.hasNext()) {
//                        setGoingUp(true);
//                    }
//
//                }
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
