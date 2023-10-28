import java.lang.Math;
public class Floor {
    /**randomly generate a floor within 2 & 32, then pass it over to the passengers class
     *Once the passenger class gets a random floor from this Floor object, assign that passenger the number
     * Try in main, to set floor as an argument in the Passenger class's parameter to use the propertu file
     * @param level
     * @return
     */
    public static int floorLevel(String level){
       int levels = Integer.parseInt(level);
       int counter = 0;
        for(int i = 0; i < levels; i++){
            counter ++;
        }
        return counter;
    }
}
