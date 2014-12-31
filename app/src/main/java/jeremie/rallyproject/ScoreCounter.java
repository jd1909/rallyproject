package jeremie.rallyproject;

/**
 * Created by Alex on 31/12/2014.
 */
public class ScoreCounter{
   // private final String name; //Counter
    private int count = 0;
    public void setCount(int count){
        this.count= count;
    }
    public void increment(){
        count++;
    }
    public void decrease(){
        count--;
    }
    public int totalScore(){
        return this.count;
    }
    public ScoreCounter(){

    }
}
