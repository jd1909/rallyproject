package jeremie.rallyproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MultiQuestionActivity extends ActionBarActivity {


    private MultiQuestions MQ1 = new MultiQuestions(1, "In the rue des marchés des herbes there are many bars. Which bar has the largest gin and tonic menu?", "Go Ten"
                                            , (float)49.611111, (float)6.13242,"the Palais", "Go Ten", "Urban");
    private MultiQuestions MQ2 = new MultiQuestions(2, "Who is the current mayor of Luxembourg City?", "Lydie Polfer"
            , (float)49.610283, (float)6.130363,"Paul Helminger", "Lydie Polfer", "Xavier Bettel");
    private MultiQuestions MQ3 = new MultiQuestions(3,"Who is burried in the cathedral?", "John the Blind of Luxembourg",(float)49.609681 ,(float)6.131582,"Grand-Duc Adolphe",
                                            "Grand-Duchesse Marie Adelaide","John the Blind of Luxembourg");
    private TextView txtMultiQuestion = (TextView) findViewById(R.id.textView4);
    private Button Answer_A= (Button)findViewById(R.id.button3);
    private Button Answer_B=(Button)findViewById(R.id.button4);
    private Button Answer_C=(Button)findViewById(R.id.button5);
    private int Score;
    private ScoreCounter count;
    private Random rn = new Random();
    public int random;
    public MultiQuestions MQR;
    private List<MultiQuestions> questions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_question);
        //add multiquestion objects to list.
        questions.add(MQ1);
        questions.add(MQ2);
        questions.add(MQ3);
        random = rn.nextInt(questions.size()) +1;
        MQR = questions.get(random);
        Intent intent = getIntent();
        Score= intent.getIntExtra("Score",-1);
        count.setCount(Score);
        txtMultiQuestion.setText(MQR.getQuestion());
        Answer_A.setText(MQR.getAnswer_A());
        Answer_B.setText(MQR.getAnswer_B());
        Answer_C.setText(MQR.getAnswer_C());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Answer_A(View view){
        if(MQR.getAnswer().equals(Answer_A.getText().toString())){
            count.increment();
        }
        Intent intent = new Intent(this, SudokuActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score",score);
        startActivity(intent);
    }
    public void Answer_B(View view){
        if(MQR.getAnswer().equals(Answer_B.getText().toString())){
            count.increment();
        }
        Intent intent = new Intent(this, SudokuActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score",score);
        startActivity(intent);
    }
    public void Answer_C(View view){
        if(MQR.getAnswer().equals(Answer_C.getText().toString())){
            count.increment();
        }
        Intent intent = new Intent(this, SudokuActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score",score);
        startActivity(intent);
    }
}
