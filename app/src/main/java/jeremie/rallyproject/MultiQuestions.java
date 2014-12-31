package jeremie.rallyproject;

/**
 * Created by Alex on 31/12/2014.
 */
public class MultiQuestions {
    int id;
    String question;
    String answer;
    float latitude;
    float longitude;
    String answer_A;
    String answer_B;
    String answer_C;
    //constructors
    public MultiQuestions(){

    }

    public MultiQuestions(int id,String question, String answer, float latitude, float longitude, String answer_A, String answer_B, String answer_C){
        this.question = question;
        this.answer=answer;
        this.latitude=latitude;
        this.longitude=longitude;
        this.id=id;
        this.answer_A=answer_A;
        this.answer_B=answer_B;
        this.answer_C=answer_C;
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
    public void setAnswer_A(String answer_A){
        this.answer_A=answer_A;
    }
    public void setAnswer_C(String answer_C){
        this.answer_C=answer_C;
    }
    public void setAnswer_B(String answer_B){
        this.answer_B=answer_B;
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
    public String getAnswer_A(){
        return this.answer_A;
    }
    public String getAnswer_B(){
        return this.answer_B;
    }
    public String getAnswer_C(){
        return this.answer_C;
    }
}
