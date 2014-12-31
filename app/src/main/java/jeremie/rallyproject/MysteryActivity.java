package jeremie.rallyproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MysteryActivity extends ActionBarActivity {
    private MysteryQuestion mystery = new MysteryQuestion("Take off my skin. I won't cry, but you will! What am I?","An Onion");
    private ScoreCounter count = new ScoreCounter();
    private TextView txtRiddle;
    private EditText Edit1;
    private EditText Edit2;
    private EditText Edit3;
    private EditText Edit4;
    private EditText Edit5;
    private EditText Edit6;
    private EditText Edit7;
    private EditText Edit8;
    private int Score;
    private int tries =3;
    private String si;
    private String totalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);

        txtRiddle = (TextView) findViewById(R.id.textView5);
        Edit1 = (EditText) findViewById(R.id.editText2);
        Edit2 = (EditText)findViewById(R.id.editText3);
        Edit3 = (EditText) findViewById(R.id.editText4);
        Edit4 = (EditText)findViewById(R.id.editText5);
        Edit5 = (EditText)findViewById(R.id.editText6);
        Edit6 = (EditText)findViewById(R.id.editText7);
        Edit7 = (EditText)findViewById(R.id.editText8);
        Edit8 = (EditText)findViewById(R.id.editText9);
        txtRiddle.setText(mystery.getRiddle());
        Edit3.setText("_");
        Intent intent = getIntent();
        Score = intent.getIntExtra("Score", -1);

        count.setCount(Score);
        switch (Score){
            case 3:
                Edit1.setText("A");
                Edit1.setEnabled(false);
                Edit2.setText("");
                Edit4.setText("");
                Edit5.setText("n");
                Edit5.setEnabled(false);
                Edit6.setText("");
                Edit7.setText("o");
                Edit7.setEnabled(false);
                Edit8.setText("");
                break;
            case 2:
                Edit1.setText("A");
                Edit1.setEnabled(false);
                Edit2.setText("");
                Edit4.setText("");
                Edit5.setText("");
                Edit6.setText("");
                Edit7.setText("o");
                Edit7.setEnabled(false);
                Edit8.setText("");
                break;
            case 1:
                Edit1.setText("");
                Edit2.setText("");
                Edit4.setText("");
                Edit5.setText("");
                Edit6.setText("");
                Edit7.setText("o");
                Edit7.setEnabled(false);
                Edit8.setText("");
                break;
            default:
                Edit1.setText("");
                Edit2.setText("");
                Edit4.setText("");
                Edit5.setText("");
                Edit6.setText("");
                Edit7.setText("");
                Edit8.setText("");
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mystery, menu);
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
    public void Finish(View view){
        //correct mystery
        if (mystery.getAnswer().equals(Edit1.getText().toString()+Edit2.getText().toString()+" "
                +Edit4.getText().toString()+Edit5.getText().toString()+Edit6.getText().toString()
                +Edit7.getText().toString()+Edit8.getText().toString())) {

                    Score=Score+2;
                    totalScore=Integer.toString(Score);
                    for(int i=0;i<3;i++){
                        Toast.makeText(getApplicationContext(),"You solved the mystery and have a final score of "+ totalScore+".", Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);


        }else{
            tries--;
            //enough tries left then
            if(tries>0){
                si=Integer.toString(tries);
                Toast.makeText(getApplicationContext(),"Wrong Answer, you got "+ si +" more tries.", Toast.LENGTH_LONG).show();
            //else finish Rally
            }else{

                totalScore=Integer.toString(Score);
                for(int i=0;i<3;i++) {
                    Toast.makeText(getApplicationContext(), "The rally is finished, you have a total score of " + totalScore + ".", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
        }
    }
}
