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
            if (goingUp) { //if we go up
                    Passenger passenger = upElevator.peek(); //looks at the passenger at the top
//                if(allFloors[currFloor] != null) {
//                    addPassengers(allFloors[currFloor].getUpQueue());
//                }
                    if (passenger != null) {
//                        for(int i = 0; i < allFloors[currFloor].getUpQueue().size(); i++) {
//                         //   dropPassengers(passenger);
//                        }
                        System.out.println("Capacity in the elevator: " + upElevator.size());
                        System.out.println("This is priority passenger:" + passenger + "and this is his starting location: " + passenger.getStartFloor());
                        System.out.println("This is priority passenger destination:" + passenger.getDestFloor());
                        System.out.println("Current Floor for debugging:" + currFloor);
                       // iterator = upElevator.iterator(); //next passenger of the elevator
                        //System.out.println(iterator.toString());
                     if ((passenger.getDestFloor() - getCurrFloor()) <= ((numFloor - 1) - currFloor) && passenger.getDestFloor() <= currFloor + 5 && passenger.getDestFloor() > getCurrFloor() && currFloor + 5 <= numFloor - 1) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
                         for (int i = currFloor + 1; i <= currFloor + 5; i++) {
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
                         } if(!floorInBetween) { //if there is no floor in between
                             this.currFloor += passenger.getDestFloor() - getCurrFloor();
                             //  dropPassengers(passenger);
                             //  counterCapacity++;
                             System.out.println("Current Floor: " + currFloor);
                         }

                        }
//                     else if (passenger.getDestFloor() == currFloor){
//                         //   dropPassengers(passenger);
//                            counterCapacity ++;
//                            System.out.println("Current Floor: " + currFloor);
//                        }
                     else if (getCurrFloor() + 5 <= passenger.getDestFloor() && getCurrFloor() + 5 <= numFloor - 1) { //if the destination of the passenger is more than 5, and it is not past the roof
                         for (int i = currFloor + 1; i <= currFloor + 5; i++) {
                             if (!allFloors[i].getUpQueue().isEmpty()) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                 if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                                     System.out.println("There is a passenger on floor banana: " + i + " so we'll skip to it");
                                     this.currFloor = i; //go to that floor
                                    // addPassengers(allFloors[currFloor].getUpQueue());
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
                        for(int i = currFloor + 1; i < numFloor - 1; i++){
                            if(!allFloors[i].getUpQueue().isEmpty() && allFloors[i] != null){ //if the rest of the floors have an upQueue
                                Passenger nextPassenger = allFloors[i].getUpQueue().peek();
                                if (nextPassenger != null && nextPassenger.getStartFloor() <= numFloor - 1 && nextPassenger.getStartFloor() <= currFloor + 5) { //then if that Upqeue
                                    System.out.println("There is a passenger on floor apple: " + i + " so we'll skip to it");
                                    this.currFloor = i;
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true);
                                    break;
                                } else if(!floorInBetween) {
                                    currFloor += 5;
                                    System.out.println("Current Floor: " + currFloor);
                                }
                            } else {
                                if(allFloors[currFloor].getUpQueue().isEmpty() && goingUp) {
                                    if (currFloor == numFloor || currFloor >= findLastNonEmptyFloor()) {
                                        setGoingUp(false);
                                    } else if (currFloor == 0 || currFloor <= findLastNonEmptyFloor()) {
                                        setGoingUp(true);
                                    }
                                } else if (allFloors[currFloor].getDownQueue().isEmpty() && !goingUp){
                                    if (currFloor == 0 || currFloor <= findLastNonEmptyFloor()) {
                                        setGoingUp(true);
                                    } else if (currFloor == numFloor || currFloor >= findLastNonEmptyFloor()) {
                                        setGoingUp(false);
                                    }
                                }
                            }
                        }
                    }
                    if(allFloors[currFloor]== null && !passengersInBetween(upElevator)){
                        setGoingUp(false);
                    }



            }
            else {
                Passenger passenger = downElevator.peek(); //looks at the passenger at the top
               // addPassengers(allFloors[currFloor].getDownQueue());
                if (passenger != null) {
                    for(int i = 0; i < allFloors[currFloor].getDownQueue().size(); i++) {
                    //    dropPassengers(passenger);
                    }
                    System.out.println("This is priority passenger destination:" + passenger.getDestFloor());
                    System.out.println("This is also priority passenger:" + passenger);
                    //iterator = downElevator.iterator(); //next passenger of the elevator
                    //System.out.println(iterator.toString());
                    if ((getCurrFloor() - passenger.getDestFloor()) <= 0 && passenger.getDestFloor() >= currFloor - 5 && passenger.getDestFloor() < getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
                        this.currFloor -= getCurrFloor() - passenger.getDestFloor();
                      //  dropPassengers(passenger);
                        counterCapacity++;
                        System.out.println("Current Floor: " + currFloor);
                    } else if (passenger.getDestFloor() == currFloor){
                     //   dropPassengers(passenger);
                        counterCapacity ++;
                        System.out.println("Current Floor: " + currFloor);
                    } else if (getCurrFloor() - 5 >= passenger.getDestFloor() && getCurrFloor() - 5 >= 0) { //if the destination of the passenger is more than 5, and it is not past the roof
                        for (int i = currFloor - 1; i >= currFloor - 5; i--) {
                            if (!allFloors[i].getDownQueue().isEmpty()) { //if the next five floors are not empty of upQueue
                                // passenger = iterator.next();
                                if (passenger.getDestFloor() >= i) { //if the current passenger's destination floor not on i
                                    System.out.println("There is a passenger on floor: " + i + " so we'll skip to it");
                                    this.currFloor = i; //go to that floor
                              //      addPassengers(allFloors[currFloor].getDownQueue());
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true); //set in between flag to true
                                    break;
                                } else {
                                    setFloorInBetween(false); //then set no floor in between
                                }
                            }
                        } if(!floorInBetween) { //if there is no floor in between
                            this.currFloor -= 5; //add 5 floors
                            System.out.println("Current Floor: " + currFloor);
                        }
                    } else if(currFloor <=  5 &&  findFirstNonEmptyFloor() -  currFloor  <= currFloor + 5){
                        findFirstNonEmptyFloor();
                        System.out.println("Current Floor: " + currFloor);
                        currFloor += findFirstNonEmptyFloor();
                        System.out.println("Current Floor: " + currFloor);
                    } else {
                        for(int i = currFloor - 1; i >= 0; i--){
                            if(!allFloors[i].getDownQueue().isEmpty()){ //if the rest of the floors have an upQueue
                                Passenger nextPassenger = allFloors[i].getDownQueue().peek();
                                if (nextPassenger != null && nextPassenger.getStartFloor() >= 0 && nextPassenger.getStartFloor() >= currFloor - 5) { //then if that Upqeue
                                    System.out.println("There is a passenger on floor: " + i + " so we'll skip to it");
                                    this.currFloor = i;
                                    System.out.println("Current Floor: " + currFloor);
                                    setFloorInBetween(true);
                                    break;
                                } else if(!floorInBetween) {
                                    currFloor -= 5;
                                    System.out.println("Current Floor: " + currFloor);
                                }
                            } else {
                                if(allFloors[currFloor].getDownQueue().isEmpty() && goingUp) {
                                    if (currFloor == numFloor || currFloor >= findLastNonEmptyFloor()) {
                                        setGoingUp(false);
                                    } else if (currFloor == 0 || currFloor <= findLastNonEmptyFloor()) {
                                        setGoingUp(true);
                                    }
                                } else if (allFloors[currFloor].getDownQueue().isEmpty() && !goingUp){
                                    if (currFloor == 0 || currFloor <= findLastNonEmptyFloor()) {
                                        setGoingUp(true);
                                    } else if (currFloor == numFloor || currFloor >= findLastNonEmptyFloor()) {
                                        setGoingUp(false);
                                    }
                                }
                            }
                        }
                    }
                }


//                        if (getCurrFloor() - 5 > passenger.getDestFloor() && getCurrFloor() - 5 >= 0) { //if the destination of the passenger is more than 5, and it is not past the roof
//                            for (int i = currFloor - 1; i >= currFloor - 5; i--) {
//                                if (!allFloors[i].getDownQueue().isEmpty()) {
//                                    //passenger = iterator.next();
//                                    if (passenger.getDestFloor() != i) {
//                                        System.out.println("There is a passenger on floor: " + i + " so we'll skip to it");
//                                        this.currFloor = i;
//                                        System.out.println("Current Floor: " + currFloor);
//                                        setFloorinBetween(true);
//                                        break;
//                                    } else {
//                                        setFloorinBetween(false);
//                                    }
//                                }
//                            }
//                            if(!floorinBetween) {
//                                this.currFloor -= 5; //add 5 floors
//                                System.out.println("Current Floor: " + currFloor);
//                            }
//                        } else if (( getCurrFloor() - passenger.getDestFloor()) >= 0 && passenger.getDestFloor() >= getCurrFloor() - 5 && passenger.getDestFloor() < getCurrFloor()) { //if passenger's destination is in between 5 floors, then jump to that floor and drop them off
////                    System.out.println(passenger.getDestFloor());
////                    System.out.println(getCurrFloor());
////                    System.out.println(passenger.getDestFloor() - getCurrFloor());
//                            this.currFloor += getCurrFloor() - passenger.getDestFloor();
//                            dropPassengers(passenger);
//                            counterCapacity++;
//                            System.out.println("Current Floor: " + currFloor);
//                        } else if (passenger.getDestFloor() == currFloor){
//                            dropPassengers(passenger);
//                            counterCapacity++;
//                            System.out.println("Current Floor: " + currFloor);
//                        }
//                } else{
//                        if(currFloor >= currFloor - 5 &&  findFirstNonEmptyFloor() - currFloor  >= currFloor - 5){
//                            System.out.println("Current Floor: " + currFloor);
//                            currFloor -= findFirstNonEmptyFloor();
//                            System.out.println("Current Floor: " + currFloor);
//                        }
//                        else{
//                            System.out.println("Current Floor: " + currFloor);
//                            currFloor -= 5;
//                            System.out.println("Current Floor: " + currFloor);
//                        }
                    //}

            }
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
            for (int i = 0; i < numFloor - 1; i++) {
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
    public void addPassengers(int floorNum, Floor floor){ //loads an entire queue of passengers from a single floor into the elevator(appropriate heap)
        if(goingUp){ //if going up
            Passenger passenger = floor.getUpQueue().peek();
            if(passenger != null) {
                while (upElevator.size() <= capacity && !floor.getUpQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the up elevator " + passenger + "With a start floor of: " + passenger.getStartFloor() + " and has a destination of " + passenger.getDestFloor() + " ");
                    upElevator.add(floor.getUpQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    //  counterCapacity--;
                    System.out.println("Floor where passenger is added to the up elevator: " + currFloor);
                    // System.out.println("Capacity: " + counterCapacity );
                }
            }
        } else { //if going down
            Passenger passenger = floor.getDownQueue().peek();
            if(passenger != null) {
                while (downElevator.size() < capacity && !floor.getDownQueue().isEmpty() && passenger.getStartFloor() == currFloor) { //while there is space in the elevator
                    System.out.print("Passenger is added to the down elevator " + floor.getDownQueue().peek() + " ");
                    downElevator.add(floor.getDownQueue().poll()); //poll all the passengers from the floor into the elevator going down
                    // counterCapacity--;
                    System.out.println("Floor where passenger is added to the down elevator: " + currFloor);
                    //  System.out.println("Capacity: " + counterCapacity );
                }
            }
        }
    }

    public void dropPassengers() {
        // Floor floor = new Floor(getNumFloor(), getStructure(), allFloors.getProbability());
        if (goingUp) {
            Passenger passenger = upElevator.peek();
            if (passenger != null) {
                while (!upElevator.isEmpty() && currFloor == passenger.getDestFloor()) {
                        //  if (upElevator.contains(passenger)) { // Check if the passenger is in the upElevator
                        System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + upElevator.poll());
                        // capacity++;
                        // System.out.println("Capacity: " + capacity);
                        // }
                }

            }
        } else {
            Passenger passenger = downElevator.peek();
            if(passenger != null){
                while(!downElevator.isEmpty() && currFloor == passenger.getDestFloor()){
                // if (downElevator.contains(passenger)) { // Check if the passenger is in the downElevator
//        } else{
                System.out.println("Passenger with starting floor: " + passenger.getStartFloor() + " & Destination floor: " + passenger.getDestFloor() + " has been polled " + downElevator.poll());
                // capacity++;
                //   System.out.println("Capacity: " + capacity);
                // }
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
