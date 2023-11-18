import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Random;

public class Floor {
    private int floorNum;

    private int currFloor;
    private float probability;

    private Deque<Passenger> upQueue;
    private Deque<Passenger> downQueue;

    public Floor(int floorNum, String structures, float probability, int currFloor) { //floor constructor
        this.floorNum = floorNum; //number of floors
        this.currFloor = currFloor;
        this.probability = probability;
        if (structures.equals("linked")) { //if properties = linked then use Linkedlist
            this.upQueue = new LinkedList<>();
            this.downQueue = new LinkedList<>();
        } else if (structures.equals("array")) { //else use arraylist
            this.upQueue = new ArrayDeque<>();
            this.downQueue = new ArrayDeque<>();
        } else { //this is default to linked list
            this.upQueue = new LinkedList<>();
            this.downQueue = new LinkedList<>();
        }

    }

    public void queuePassenger(int time) {
        Random random = new Random(); //random object
        if (random.nextFloat() <= probability) { //if less than or equal to probability, create passenger
            Passenger passenger = new Passenger(floorNum, currFloor, time); //new passenger has ticked passed through
            if (passenger.getNearestDestination() > 0) { //if passenger wants to go to a floor higher than the floor we are on, then we add them to the queue
                upQueue.add(passenger); //add to upqueue
                System.out.println(passenger + " is added to UpQueue");
            } else if (passenger.getNearestDestination() < 0) {
                downQueue.add(passenger); //else add them to the queue going down
                System.out.println(passenger + " is added to DownQueue");
            }
        }
    }

    public Deque<Passenger> getUpQueue() {
        return upQueue;
    }

    public Deque<Passenger> getDownQueue() {
        return downQueue;
    }

}
