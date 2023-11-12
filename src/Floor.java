import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Random;

public class Floor {
    private int floorNum;

    //private int capacity;

    private float probability;

    private Deque<Passenger> upQueue;
    private Deque<Passenger> downQueue;

    // private int currFloor; //minimum of 5 and desired floors
    public Floor(int floorNum, String structures, float probability) {
        this.floorNum = floorNum; //number of floors
        this.probability = probability;
        Random random = new Random();
        //  private int numFloors;
        if (structures.equals("linked")) {
            this.upQueue = new LinkedList<>();
            this.downQueue = new LinkedList<>();
        } else if (structures.equals("array")) {
            this.upQueue = new ArrayDeque<>();
            this.downQueue = new ArrayDeque<>();
        } else {
            this.upQueue = new LinkedList<>();
            this.downQueue = new LinkedList<>();
        }

    }

    public void queuePassenger() {
        Random random = new Random();
        if (random.nextFloat() <= probability) {
            Passenger passenger = new Passenger(floorNum);
            if (passenger.getNearestDestination() > 0) { //if passenger wants to go to a floor higher than the floor we are on, then we add them to the queue
                upQueue.add(passenger);
                System.out.println(passenger + " is added to UpQueue");
//                 System.out.println("Capacity is " + capacity);
            } else if (passenger.getNearestDestination() < 0) {
                downQueue.add(passenger); //else add them to the queue going down
                System.out.println(passenger + " is added to DownQueue");
//                 System.out.println("Capacity is " + capacity);
            }
        }
    }

    public Deque<Passenger> getUpQueue() {
        return upQueue;
    }

    public Deque<Passenger> getDownQueue() {
        return downQueue;
    }

    public float getProbability() {
        return probability;
    }
}
