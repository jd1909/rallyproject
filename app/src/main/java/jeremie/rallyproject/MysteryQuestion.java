package jeremie.rallyproject;

/**
 * Created by Alex on 31/12/2014.
 */
public class MysteryQuestion {
    String riddle;
    String answer;
    //constructor
    public MysteryQuestion(String riddle,String answer) {
        this.riddle=riddle;
        this.answer=answer;
    }
    //setter
    public void setRiddle(String riddle){
        this.riddle=riddle;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
    //getters
    public String getRiddle(){
        return this.riddle;
    }
    public String getAnswer(){
        return this.answer;
    }
}
