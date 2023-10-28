import java.lang.Math;
import java.util.Random;
public class Passenger {
    private static int passengers;

    public static int levels(int level){
        Floor floors = new Floor();
        Random rand = new Random();
        floors.floorLevel("32") = 2 + rand.nextInt(31);
        passengers = 1;
        return passengers;
    }
}
