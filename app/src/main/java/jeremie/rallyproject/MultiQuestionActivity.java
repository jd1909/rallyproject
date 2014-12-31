package jeremie.rallyproject;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MultiQuestionActivity extends ActionBarActivity {

    private GoogleMap map;
    private MultiQuestions MQ1 = new MultiQuestions(1, "In the rue des marchés des herbes there are many bars. Which bar has the largest gin and tonic menu?", "Go Ten"
                                            , (float)49.611111, (float)6.13242,"the Palais", "Go Ten", "Urban");
    private MultiQuestions MQ2 = new MultiQuestions(2, "Who is the current mayor of Luxembourg City?", "Lydie Polfer"
            , (float)49.610283, (float)6.130363,"Paul Helminger", "Lydie Polfer", "Xavier Bettel");
    private MultiQuestions MQ3 = new MultiQuestions(3,"Who is burried in the cathedral?", "John the Blind of Luxembourg",(float)49.609681 ,(float)6.131582,"Grand-Duc Adolphe",
                                            "Grand-Duchesse Marie Adelaide","John the Blind of Luxembourg");
    private TextView txtMultiQuestion;
    private Button Answer_A;
    private Button Answer_B;
    private Button Answer_C;
    private ScoreCounter count;
    private Random rn = new Random();
    public int random;
    public MultiQuestions MQR;
    private List<MultiQuestions> questions = new ArrayList<>();
    static final LatLng LUXEMBOURG = new LatLng(49.6117, 6.1300);
    private Marker myLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_question);
        txtMultiQuestion = (TextView) findViewById(R.id.textView4);
        Answer_A= (Button)findViewById(R.id.button3);
        Answer_B=(Button)findViewById(R.id.button4);
        Answer_C=(Button)findViewById(R.id.button5);
        Answer_A.setText("");
        Answer_B.setText("");
        Answer_C.setText("");
        txtMultiQuestion.setText("");

        Intent intent = getIntent();
        count = new ScoreCounter();
        count.setCount(intent.getIntExtra("Score",-1));

        float lat = MQR.getLatitude();
        float lng = MQR.getLongitude();
        LatLng location = new LatLng(lat, lng);

        // Set up the google map fragment
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Marker luxembourg = map.addMarker(new MarkerOptions().position(LUXEMBOURG)
                .title("Luxembourg"));
        Marker claire = map.addMarker(new MarkerOptions()
                .position(location)
                .title("Quest"));

        // Move the camera instantly to luxembourg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LUXEMBOURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
        this.map.setMyLocationEnabled(true);
        this.myLocation= map.addMarker(new MarkerOptions().position(new LatLng(49.6117, 6.1300)).title("You are here"));
        this.map.setOnMyLocationChangeListener(this.myLocationChangeListener);

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
    public boolean verifyLocation() {
        LatLng quest = new LatLng(MQR.getLatitude(), MQR.getLongitude());
        if (myLocation.getPosition().latitude == MQR.getLatitude()&& myLocation.getPosition().longitude == MQR.getLongitude() )
            return true;

        return false;
    }



    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng newLoc = new LatLng(location.getLatitude(), location.getLongitude());
            myLocation.setPosition(newLoc);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 16.0f));
            if (verifyLocation()){
                questions.add(MQ1);
                questions.add(MQ2);
                questions.add(MQ3);
                random = rn.nextInt(questions.size());
                random = rn.nextInt(questions.size());
                MQR = questions.get(random);
                Answer_A.setEnabled(true);
                Answer_B.setEnabled(true);
                Answer_C.setEnabled(true);
                Answer_A.setText(MQR.getAnswer_A());
                Answer_B.setText(MQR.getAnswer_B());
                Answer_C.setText(MQR.getAnswer_C());
                txtMultiQuestion.setText(MQR.getQuestion());


            }
        }
    };
}
