import java.util.*;

public class Elevator {

    private int numFloor; //number of floors

    private int destFloor; //destination floor

    private int startFloor; //start floor

    private boolean goingUp; //is going up
    private boolean floorInBetween;
    private  int capacity; //capacity of elevator

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

    public Elevator(int floorNum, int elevatorCapacity, String structures, Floor[] allFloors, float probability) {
        this.numFloor = floorNum;
        this.probability = probability;
        this.capacity = elevatorCapacity;
        this.allFloors =  allFloors;
        this.upElevator = new PriorityQueue<Passenger>();
        this.downElevator = new PriorityQueue<Passenger>(Collections.reverseOrder());
        this.structure = structures;
        this.currFloor = 0; //elevator starts at ground
        this.goingUp = true;
        this.floorInBetween = false;
        this.counterCapacity = elevatorCapacity;
        this.firstNonEmptyFloor = 0;
        this.lastNonEmptyFloor = 0;
    }
    public void moveElevator(){
        //  Iterator<Passenger> iterator;

//        for (Floor allFloor : allFloors) {
//            addPassengers(allFloor.getUpQueue()); //add all the passengers from the current floor into the heap
//            addPassengers(allFloor.getDownQueue()); //add all the passengers from the current floor into the heap
//        }
// choose floor to go to , check 1 floor up or one floor down, if there is no floor up, then check down, go down to see which passenger hasnt been assigned to another elevator
        if(currFloor == 0){
            setGoingUp(true);
        } else if (currFloor == numFloor -1){
            setGoingUp(false);
        }
        if (goingUp) { //if we go up
            Passenger passenger = upElevator.peek(); //looks at the passenger at the top
            if (passenger != null) {
                System.out.println("Capacity in the elevator: " + upElevator.size());
                System.out.println("This is priority passenger:" + passenger + "and this is his starting location: " + passenger.getStartFloor());
                System.out.println("This is priority passenger destination:" + passenger.getDestFloor());
                System.out.println("Current Floor for debugging:" + currFloor);
                if ((passenger.getDestFloor() - getCurrFloor()) <= ((numFloor - 1) - currFloor) && passenger.getDestFloor() <= currFloor + 5 && passenger.getDestFloor() > getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
                    if(numFloor - passenger.getDestFloor() >= numFloor - currFloor) {
                        for (int i = currFloor + 1; i <= currFloor + 5; i++) {
                            if (!allFloors[i].getUpQueue().isEmpty() && allFloors[i] != null) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                                    System.out.println("There is a passenger on floor orange: " + i + " so we'll skip to it");
                                    this.currFloor = i; //go to that floor
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true); //set in between flag to true
                                    break;
                                }
                            } else {
                                setFloorInBetween(false); //then set no floor in between
                            }
                        }

                    } else if (numFloor - passenger.getDestFloor() <= numFloor - currFloor){
                        for (int i = currFloor + 1; i < numFloor - 1; i++) {
                            if (!allFloors[i].getUpQueue().isEmpty() && allFloors[i] != null) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                                    System.out.println("There is a passenger on floor orange: " + i + " so we'll skip to it");
                                    this.currFloor = i; //go to that floor
                                    // addPassengers(allFloors[currFloor].getUpQueue());
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true); //set in between flag to true
                                    break;
                                }
                            } else {
                                setFloorInBetween(false); //then set no floor in between
                            }
                        }
                    } if(!floorInBetween) { //if there is no floor in between
                        this.currFloor += passenger.getDestFloor() - getCurrFloor();
                        System.out.println("Current Floor: " + currFloor);
                    }

                }
                else if (getCurrFloor() + 5 <= passenger.getDestFloor() && getCurrFloor() + 5 <= numFloor - 1) { //if the destination of the passenger is more than 5, and it is not past the roof
                    for (int i = currFloor + 1; i <= currFloor + 5; i++) {
                        if (!allFloors[i].getUpQueue().isEmpty()) { //if the next five floors are not empty of upQueue
                            if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                                System.out.println("There is a passenger on floor banana: " + i + " so we'll skip to it");
                                this.currFloor = i; //go to that floor
                                System.out.println("Current Floor: " + currFloor);
                                setFloorInBetween(true); //set in between flag to true
                                break;
                            }
                        } else {
                            setFloorInBetween(false); //then set no floor in between
                        }
                    } if(!floorInBetween) { //if there is no floor in between
                        this.currFloor += 5; //add 5 floors
                        System.out.println("Current Floor: " + currFloor);
                    }
                }
            } else if(currFloor <=  5 &&  findFirstNonEmptyFloor() -  currFloor  <= currFloor + 5){
                findFirstNonEmptyFloor();
                System.out.println("Current Floor: " + currFloor);
                currFloor += findFirstNonEmptyFloor();
                System.out.println("Current Floor: " + currFloor);
            } else {
                for(int i = currFloor + 1; i < numFloor; i++){
                    if(!allFloors[i].getUpQueue().isEmpty() && allFloors[i] != null){ //if the rest of the floors have an upQueue
                        Passenger nextPassenger = allFloors[i].getUpQueue().peek();
                        if (nextPassenger != null && nextPassenger.getStartFloor() <= numFloor - 1 && nextPassenger.getStartFloor() <= currFloor + 5) { //then if that Upqeue
                            System.out.println("There is a passenger on floor apple: " + i + " so we'll skip to it");
                            this.currFloor = i;
                            System.out.println("Current Floor: " + currFloor);
                            setFloorInBetween(true);
                            break;
                        } else if (i == numFloor - 1 && !floorInBetween){
                            currFloor += 5;
                            System.out.println("Current Floor: " + currFloor);
                        }
                    } else if (findFirstNonEmptyFloor() > 5 && currFloor < 5 && !floorInBetween){  //if first non empty floor isnt within 5, plus 5
                        currFloor += 5;
                        break;
                    } else if ((i == numFloor - 1) && currFloor >= numFloor - 6 && currFloor <= numFloor - 1){ //if within the bounderies, then jump to max floor
                        currFloor = numFloor - 1;
                        System.out.println("Current Floor: " + currFloor);
                        break;
                    } else if(i == numFloor - 1) { //if no floor in between, jump to towards highest floor
                        currFloor += 5;
                        System.out.println("Current Floor: " + currFloor);
                        break;
                    }
                }
            }
//                    if(allFloors[currFloor].getUpQueue() == null && !passengersInBetween(upElevator) || currFloor == numFloor - 1 || !passengersAbove(upElevator)){
//                        setGoingUp(false);
//                    }


        } else {
            Passenger passenger = downElevator.peek(); //looks at the passenger at the top
            if (passenger != null) {
                System.out.println("Capacity in the elevator: " + upElevator.size());
                System.out.println("This is priority passenger:" + passenger + "and this is his starting location: " + passenger.getStartFloor());
                System.out.println("This is priority passenger destination:" + passenger.getDestFloor());
                System.out.println("Current Floor for debugging:" + currFloor);
                if ((getCurrFloor() - passenger.getDestFloor()) >= 0 && passenger.getDestFloor() >= 0 && passenger.getDestFloor() < getCurrFloor() && passenger.getDestFloor() >= currFloor - 5) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
                    if(passenger.getDestFloor() >= 5) { //if not over the roof
                        for (int i = currFloor - 1; i >= currFloor - 5; i--) {
                            if (!allFloors[i].getDownQueue().isEmpty() && allFloors[i] != null) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                if (passenger.getDestFloor() <= i) { //if the current passenger's destination floor not on i
                                    System.out.println("There is a passenger on floor orange: " + i + " so we'll skip to it");
                                    this.currFloor = i; //go to that floor
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true); //set in between flag to true
                                    break;
                                }
                            } else {
                                setFloorInBetween(false); //then set no floor in between
                            }
                        }

                    } else if (passenger.getDestFloor() <= 5){
                        for (int i = currFloor - 1; i > 0; i--) {
                            if (!allFloors[i].getDownQueue().isEmpty() && allFloors[i] != null) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                if (passenger.getDestFloor() <= i) { //if the current passenger's destination floor not on i
                                    System.out.println("There is a passenger on floor orange: " + i + " so we'll skip to it");
                                    this.currFloor = i; //go to that floor
                                    // addPassengers(allFloors[currFloor].getUpQueue());
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true); //set in between flag to true
                                    break;
                                }
                            } else {
                                setFloorInBetween(false); //then set no floor in between
                            }
                        }
                    } if(!floorInBetween) { //if there is no floor in between
                        this.currFloor -= getCurrFloor() - passenger.getDestFloor() ;
                        System.out.println("Current Floor: " + currFloor);
                    }

                }
                else if (getCurrFloor() - 5 >= passenger.getDestFloor() && getCurrFloor() - 5 >= 0) { //if the destination of the passenger is more than 5, and it is not past the roof
                    for (int i = currFloor - 1; i >= currFloor - 5; i--) {
                        if (!allFloors[i].getDownQueue().isEmpty()) { //if the next five floors are not empty of upQueue
                            if (passenger.getDestFloor() <= i) { //if the current passenger's destination floor not on i
                                System.out.println("There is a passenger on floor banana: " + i + " so we'll skip to it");
                                this.currFloor = i; //go to that floor
                                System.out.println("Current Floor: " + currFloor);
                                setFloorInBetween(true); //set in between flag to true
                                break;
                            }
                        } else {
                            setFloorInBetween(false); //then set no floor in between
                        }
                    } if(!floorInBetween) { //if there is no floor in between
                        this.currFloor -= 5; //add 5 floors
                        System.out.println("Current Floor: " + currFloor);
                    }
                }
                findFirstNonEmptyFloor();
            } else if(currFloor >=  numFloor - 5 &&  currFloor - firstNonEmptyFloor >= currFloor - 5){
                System.out.println("Current Floor: " + currFloor);
                currFloor = findFirstNonEmptyFloor();
                System.out.println("Current Floor: " + currFloor);
            } else {
                for(int i = currFloor - 1; i >= 0; i--){
                    if(!allFloors[i].getUpQueue().isEmpty() && allFloors[i] != null){ //if the rest of the floors have an downQueue
                        Passenger nextPassenger = allFloors[i].getDownQueue().peek();
                        if (nextPassenger != null && nextPassenger.getStartFloor() <= numFloor - 1 && nextPassenger.getStartFloor() <= currFloor + 5) { //then if that downQeue
                            System.out.println("There is a passenger on floor apple: " + i + " so we'll skip to it");
                            this.currFloor = i;
                            System.out.println("Current Floor: " + currFloor);
                            setFloorInBetween(true);
                            break;
                        }

//                        if (floorInBetween && nextPassenger.getStartFloor() > 5){
//                            currFloor -= 5;
//                            System.out.println("Current Floor: " + currFloor);
//                        }
                    } else if(!floorInBetween && findFirstNonEmptyFloor() < numFloor - 5 && currFloor >= numFloor - 5) {
                        currFloor -= 5;
                        System.out.println("Current Floor: " + currFloor);
                    } else if (floorInBetween && (i ==0)){
                        currFloor -= 5;
                        System.out.println("Current Floor: " + currFloor);
                    }
                    else if (currFloor >= 0 && currFloor <= 5){
                        currFloor = 0;
                        System.out.println("Current Floor: " + currFloor);
                    }
                }
            }
//            if(allFloors[currFloor]== null && !passengersInBetween(downElevator)){
//                setGoingUp(true);
//            }
        }
    }



    public boolean passengersAbove(PriorityQueue<Passenger> elevator){
        Passenger passenger = elevator.peek();
        if(passenger != null){
            for(int i = currFloor; i < numFloor; i++){
                if(allFloors[i].getUpQueue().isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean passengersInBetween(PriorityQueue<Passenger> elevator) {
        Passenger passenger = elevator.peek();
        if(passenger != null){
            if (getCurrFloor() + 5 < passenger.getDestFloor() && getCurrFloor() + 5 <= numFloor - 1) { //if the destination of the passenger is more than 5, and it is not past the roof
                for (int i = currFloor + 1; i <= currFloor + 5; i++) {
                    if (!allFloors[i].getUpQueue().isEmpty()) { //if the next five floors are not empty of upQueue
                        // passenger = iterator.next();
                        if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                            System.out.println("There is a passenger on floor: " + i + " so we'll skip to it");
                            this.currFloor = i; //go to that floor
                            // addPassengers(allFloors[currFloor].getUpQueue());
                            System.out.println("Current Floor: " + currFloor);
                            setFloorInBetween(true); //set in between flag to true
                            break;
                        } else {
                            setFloorInBetween(false); //then set no floor in between
                        }
                    }
                }
            }
            if (!floorInBetween) { //if there is no floor in between
                this.currFloor += 5; //add 5 floors
                System.out.println("Current Floor: " + currFloor);
            }
        }
        return floorInBetween;
    }



    public int findFirstNonEmptyFloor(){
        if(goingUp){
            for (int i = 0; i < numFloor - 1; i++) {
                if (!allFloors[i].getUpQueue().isEmpty()) {
                    firstNonEmptyFloor = i;
                    break;
                }
            }
        } else {
            for (int i = numFloor - 1; i > 0 ; i--) {
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
            for (int i = numFloor - 1; i > 0; i--) {
                if (!allFloors[i].getUpQueue().isEmpty()) {
                    lastNonEmptyFloor = i;
                }
            }
        }
        return lastNonEmptyFloor;
    }
    //numerical value of floor that it is at
    public void addPassengers(int floorNum, Floor[] floor){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            Passenger passenger = floor[currFloor].getUpQueue().peek();
            if(passenger != null) {
                while (upElevator.size() <= capacity && !floor[currFloor].getUpQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the up elevator " + passenger + "With a start floor of: " + passenger.getStartFloor() + " and has a destination of " + passenger.getDestFloor() + " ");
                    upElevator.add(floor[currFloor].getUpQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    //  counterCapacity--;
                    System.out.println("Floor where passenger is added to the up elevator: " + currFloor);
                    // System.out.println("Capacity: " + counterCapacity );
                }
            }
        } else { //if going down
            Passenger passenger = floor[currFloor].getDownQueue().peek();
            if(passenger != null) {
                while (downElevator.size() < capacity && !floor[currFloor].getDownQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the down elevator " + floor[currFloor].getDownQueue().peek() + " ");
                    downElevator.add(floor[currFloor].getDownQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    // counterCapacity--;
                    System.out.println("Floor where passenger is added to the down elevator: " + currFloor);
                    //  System.out.println("Capacity: " + counterCapacity );
                }
            }
        }
    }

    public void dropPassengers() {
        if (goingUp) {
            if (upElevator != null) {
                while (!upElevator.isEmpty() && currFloor == upElevator.peek().getDestFloor()) {
                    System.out.println("Passenger with starting floor: " + upElevator.peek().getStartFloor() + " & Destination floor: " + upElevator.peek().getDestFloor() + " has been polled " + upElevator.poll());
                }

            }
        } else {
            if(downElevator != null){
                while(!downElevator.isEmpty() && currFloor == downElevator.peek().getDestFloor()){
                    System.out.println("Passenger with starting floor: " + downElevator.peek().getStartFloor() + " & Destination floor: " + downElevator.peek().getDestFloor() + " has been polled " + downElevator.poll());
                }
            }
        }
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public int getCurrFloor() {
        return currFloor;
    }
    public void setFloorInBetween(boolean floorInBetween) {
        this.floorInBetween = floorInBetween;
    }
}