import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Structures {
    ArrayList<String> queue1;
    Queue<String> queue2;

    public Structures(String structureType){
        if(structureType.equals("arraylist")){
            queue1 = new ArrayList<String>();
        } else if(structureType.equals("linked")){
            queue2 = new LinkedList<String>();
        } else {
            throw new IllegalArgumentException("Invalid structure type");
        }
    }
}
