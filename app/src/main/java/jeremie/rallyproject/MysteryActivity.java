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
    private ScoreCounter count;
    private TextView txtRiddle = (TextView) findViewById(R.id.textView5);
    private EditText Edit1 = (EditText) findViewById(R.id.editText2);
    private EditText Edit2 = (EditText)findViewById(R.id.editText3);
    private EditText Edit3 = (EditText) findViewById(R.id.editText4);
    private EditText Edit4 = (EditText)findViewById(R.id.editText5);
    private EditText Edit5 = (EditText)findViewById(R.id.editText6);
    private EditText Edit6 = (EditText)findViewById(R.id.editText7);
    private EditText Edit7 = (EditText)findViewById(R.id.editText8);
    private EditText Edit8 = (EditText)findViewById(R.id.editText9);
    private int Score;
    private int tries =3;
    private String si;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);
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
        if (mystery.getAnswer().equals(Edit1.getText().toString()+Edit2.getText().toString()+" "
                +Edit4.getText().toString()+Edit5.getText().toString()+Edit6.getText().toString()
                +Edit7.getText().toString()+Edit8.getText().toString())) {
                    //popup congrats winner with Results and completed mystery
                    Score=Score+2;



        }else{
            tries--;
            if(tries>0){
                si=Integer.toString(tries);
                Toast.makeText(getApplicationContext(),"Wrong Answer, you got "+ si, Toast.LENGTH_LONG).show();

            }else{
                //popup you have finished the rally, but failed the mystery question here are your result


            }
        }
    }
}
