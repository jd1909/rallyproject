package jeremie.rallyproject;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
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


public class QuestionActivity extends ActionBarActivity {
    private GoogleMap map;
    static final LatLng LUXEMBOURG = new LatLng(49.6117,6.1300);
    static final LatLng CLAIREFONTAINE = new LatLng(49.6098,6.1325);
    SimpleQuestions SQ1 = new SimpleQuestions(1, " How much does the five course menu cost in the famous restaurant Clairefontaine?",
            "95 euros", (float)49.60985, (float)6.132561);
    SimpleQuestions SQ2 = new SimpleQuestions(2, "Its Saturday morning 7 am, you are hungry, where can you go for a burger?",
            "Saumur Crystal Club", (float)49.604201, (float)6.129737);
    TextView txtQuestion = (TextView) findViewById(R.id.textView3);
    EditText edtAnswer = (EditText) findViewById(R.id.editText);
    ScoreCounter count = new ScoreCounter();
    int input;
    @Override




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);





        float lat = SQ1.getLatitude();
        float lng = SQ1.getLongitude();


        txtQuestion.setText(SQ1.getQuestion());

        LatLng location = new LatLng(lat,lng);


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

        final Button btnOpenPopup = (Button)findViewById(R.id.button2);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
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

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();

                    }});

                popupWindow.showAsDropDown(findViewById(R.id.textView2), 50, -30);

            }});
    }
    public void Answer(View view){
        if(SQ1.getAnswer().equals(edtAnswer.getText().toString()))
        {
                count.increment();

        }else{
                count.decrease();
        }
        Intent intent = new Intent(this, MultiQuestionActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score",score);
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


}
