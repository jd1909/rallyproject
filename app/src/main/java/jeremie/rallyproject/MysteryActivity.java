package jeremie.rallyproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MysteryActivity extends ActionBarActivity {
    MysteryQuestion mystery = new MysteryQuestion("Take off my skin. I won't cry, but you will! What am I?","An Onion");
    ScoreCounter count;
    TextView txtRiddle = (TextView) findViewById(R.id.textView5);
    EditText Edit1 = (EditText) findViewById(R.id.editText2);
    EditText Edit2 = (EditText)findViewById(R.id.editText3);
    EditText Edit3 = (EditText) findViewById(R.id.editText4);
    EditText Edit4 = (EditText)findViewById(R.id.editText5);
    EditText Edit5 = (EditText)findViewById(R.id.editText6);
    EditText Edit6 = (EditText)findViewById(R.id.editText7);
    EditText Edit7 = (EditText)findViewById(R.id.editText8);
    EditText Edit8 = (EditText)findViewById(R.id.editText9);
    int Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);
        txtRiddle.setText(mystery.getRiddle());
        Edit3.setText("_");
        Intent intent = getIntent();
        Score = intent.getIntExtra("Score", -1);
        count.setCount(Score);
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
}
