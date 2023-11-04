public class Elevator {


    private int floornum;

    private int destFloor;

    private int startFloor;

    private boolean goingUp;


    private  int capacity;

    public Elevator(int flooring) {
        this.floornum = floornum;
    }


    public boolean getDirection() {
        if(getFloornum() == getStartFloor() && getDestFloor() > getStartFloor()){
            for(int i = getStartFloor(); i < getDestFloor() && i < getFloornum(); i++ ){
                setGoingUp(true);
            }
        }

        return false;
    }


    public int getFloornum() {
        return floornum;
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
}
