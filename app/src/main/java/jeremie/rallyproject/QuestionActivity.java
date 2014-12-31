package jeremie.rallyproject;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.database.Cursor;
import android.widget.TextView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuestionActivity extends ActionBarActivity {
    private GoogleMap map;
    static final LatLng LUXEMBOURG = new LatLng(49.6117, 6.1300);
    static final LatLng CLAIREFONTAINE = new LatLng(49.6098, 6.1325);
    private SimpleQuestions SQ1 = new SimpleQuestions(1, " How much does the five course menu cost in the famous restaurant Clairefontaine?",
            "95 euros", (float) 49.60985, (float) 6.132561);
    private SimpleQuestions SQ2 = new SimpleQuestions(2, "Its Saturday morning 7 am, you are hungry, where can you go for a burger?",
            "Saumur Crystal Club", (float) 49.604201, (float) 6.129737);
    private TextView txtQuestion;
    private EditText edtAnswer;
    private Button verify;
    private Button answer;
    private ScoreCounter count = new ScoreCounter();
    private List<SimpleQuestions> questions = new ArrayList<>();
    private Marker myLocation;
    private boolean verified;


    //   Random rand = new Random();
    //   int Random = rand.nextInt(1) +1;
    private Random rn = new Random();
    public int random;
    public SimpleQuestions SQR;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //add simplequestion objects to list.

        questions.add(SQ1);
        questions.add(SQ2);
        random = rn.nextInt(questions.size());
        SQR = questions.get(random);




        float lat = SQR.getLatitude();
        float lng = SQR.getLongitude();
        answer = (Button)findViewById(R.id.button2);

        txtQuestion = (TextView) findViewById(R.id.textView3);
        edtAnswer = (EditText) findViewById(R.id.editText);
        txtQuestion.setText("");
        answer.setEnabled(false);

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


/**
 final Button btnOpenPopup = (Button)findViewById(R.id.button2);
 btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

@Override public void onClick(View arg0) {
LayoutInflater layoutInflater
= (LayoutInflater)getBaseContext()
.getSystemService(LAYOUT_INFLATER_SERVICE);
View popupView = layoutInflater.inflate(R.layout.activity_popup, null);
final PopupWindow popupWindow = new PopupWindow(
popupView,
ViewGroup.LayoutParams.WRAP_CONTENT,
ViewGroup.LayoutParams.WRAP_CONTENT);

Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
btnDismiss.setOnClickListener(new Button.OnClickListener(){

@Override public void onClick(View v) {
// TODO Auto-generated method stub
popupWindow.dismiss();

}});

popupWindow.showAsDropDown(findViewById(R.id.textView2), 50, -30);

}});
 */
    }

    public void Answer(View view) {
        if (SQR.getAnswer().equals(edtAnswer.getText().toString())) {
            count.increment();

        }
        Intent intent = new Intent(this, MultiQuestionActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score", score);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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



    public boolean verifyLocation() {
        LatLng quest = new LatLng(SQR.getLatitude(), SQR.getLongitude());
        if (myLocation.getPosition().latitude == SQR.getLatitude()&& myLocation.getPosition().longitude == SQR.getLongitude() )
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
                questions.add(SQ1);
                questions.add(SQ2);
                random = rn.nextInt(questions.size());
                SQR = questions.get(random);
                verify.setEnabled(false);
                answer.setEnabled(true);
                txtQuestion.setText(SQR.getQuestion());


            }
        }
    };



}
