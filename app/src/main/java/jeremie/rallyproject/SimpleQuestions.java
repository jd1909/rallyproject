package jeremie.rallyproject;

/**
 * Created by Alex on 30/12/2014.
 */
public class SimpleQuestions {
    int id;
    String question;
    String answer;
    float latitude;
    float longitude;
    //constructors
    public SimpleQuestions(){

    }

    public SimpleQuestions(String question, String answer, float latitude, float longitude){
        this.question = question;
        this.answer=answer;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public SimpleQuestions(int id,String question, String answer, float latitude, float longitude){
        this.question = question;
        this.answer=answer;
        this.latitude=latitude;
        this.longitude=longitude;
        this.id=id;
    }
    //setters
    public void setId(int id){
        this.id=id;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
    public void setLatitude(float latitude){
        this.latitude=latitude;
    }
    public void setLongitude(float longitude){
        this.longitude=longitude;
    }
    //getters
    public long getId(){
        return this.id;
    }
    public String getQuestion(){
        return this.question;
    }
    public String getAnswer(){
        return this.answer;
    }
    public float getLatitude(){
        return this.latitude;
    }
    public float getLongitude(){
        return this.longitude;
    }

}
