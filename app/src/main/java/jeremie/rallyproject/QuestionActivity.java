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
import android.widget.PopupWindow;
import android.database.Cursor;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.io.IOException;


public class QuestionActivity extends ActionBarActivity {
    private GoogleMap map;
    static final LatLng LUXEMBOURG = new LatLng(49.6117,6.1300);
    static final LatLng CLAIREFONTAINE = new LatLng(49.6098,6.1325);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        // Set up the database
        DatabaseOpenHelper myDbHelper = new DatabaseOpenHelper(this);
        try {
            // check if database exists in app path, if not copy it from assets
            myDbHelper.create();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        myDbHelper.close();

        try {
            // open the database
            myDbHelper.open();
            myDbHelper.getWritableDatabase();
        } catch (SQLException sqle) {
            throw sqle;
        }


        Cursor questionCursor = myDbHelper.getSimpleQuestion(1);
        String questionString = questionCursor.getString(1);
        System.out.printf(questionString);
        String answer = questionCursor.getString(2);
        System.out.printf(answer);
        float lat = questionCursor.getFloat(3);
        float lng = questionCursor.getFloat(4);
        LatLng location = new LatLng(lat,lng);

        /*Cursor c = myDbHelper.getUser(1);
        Toast.makeText(this,
                "id: " + c.getInt(o) + "\n name: " + c.getString(1)
                        + "\n initials: " + c.getString(2) + "\n address: "
                        + c.getString(3), Toast.LENGTH_LONG).show();
        c.close();*/

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
