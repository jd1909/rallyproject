package jeremie.rallyproject;

/**
 * Created by Alex on 31/12/2014.
 */
public class ScoreCounter{
   // private final String name; //Counter
    private int count = 0;
    public void increment(){
        count++;
    }
    public void decrease(){
        count--;
    }
    public int totalScore(){
        return count;
    }
    public ScoreCounter(){

    }
}