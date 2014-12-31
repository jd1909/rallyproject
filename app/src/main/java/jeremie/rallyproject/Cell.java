package jeremie.rallyproject;

/**
 * Created by Jeremie on 30/12/2014.
 */
public class Cell {
    private int digit;

    public Cell(){
        digit = 0;
    }

    public Cell(int digit){
        this.digit = digit;
    }

    public int getDigit(){
        return digit;
    }

    public boolean setDigit(int newdigit){
        if(newdigit <= 6 && newdigit >= 0){
            digit = newdigit;
            return true;
        }
        return false;
    }

    public String printDigit(){
        if(digit == 0)
            return "_";
        return "" + (char) ('0'+ digit);
    }
}
