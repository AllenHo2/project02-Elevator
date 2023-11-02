import java.lang.Math;
public class Floor {
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

    private int floorNums;

    public int getFloorNums() {
        return floorNums;
    }

    public void setFloorNums(int floorNums) {
        this.floorNums = floorNums;
    }

    public Floor(){
       int levels = 32;
       Passenger passenger = new Passenger();
       int counter = 0;
        for(int i = 0; i < levels; i++){
            counter ++;
        }
       // return counter;
    }

    public void dropPassengers(){
        if()
    }
    public void addPassengers(){

    }
}
