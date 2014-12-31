package jeremie.rallyproject;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class SudokuMapActivity extends ActionBarActivity {
    private GoogleMap map;
    private Marker myLocation;
    private ScoreCounter count = new ScoreCounter();
    private boolean verified = false;
    private LatLng location = new LatLng(49.6117, 6.1300);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_map);

        Intent intent = getIntent();
        count = new ScoreCounter();
        count.setCount(intent.getIntExtra("Score",-1));

        // Set up the google map fragment
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map3))
                .getMap();
        Marker quest = map.addMarker(new MarkerOptions()
                .position(location)
                .title("Quest"));

        // Move the camera instantly to luxembourg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
        this.map.setMyLocationEnabled(true);
        this.myLocation= map.addMarker(new MarkerOptions().position(new LatLng(49.6117, 6.1300)).title("You are here"));
        this.map.setOnMyLocationChangeListener(this.myLocationChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sudoku_map, menu);
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

    public void proceed(View view) {
        Intent intent = new Intent(this,SudokuActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score", score);
        startActivity(intent);
    }

    private boolean verifyLocation() {
        //enable this when app is to be used for real
        //if (myLocation.getPosition().latitude == SQR.getLatitude()&& myLocation.getPosition().longitude == SQR.getLongitude() )
        //enable for test purposes
        if ((myLocation.getPosition().latitude == myLocation.getPosition().latitude) && (myLocation.getPosition().longitude == myLocation.getPosition().longitude ))
            return true;

        return false;
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng newLoc = new LatLng(location.getLatitude(), location.getLongitude());
            myLocation.setPosition(newLoc);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 16.0f));
            if (!verified && verifyLocation()){
                verified = true;
            }
        }
    };
}
