package jeremie.rallyproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class QuestionActivity extends ActionBarActivity {
    private GoogleMap map;
    static final LatLng LUXEMBOURG = new LatLng(49.6117,6.1300);
    static final LatLng CLAIREFONTAINE = new LatLng(49.6098,6.1325);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Marker luxembourg = map.addMarker(new MarkerOptions().position(LUXEMBOURG)
                .title("Luxembourg"));
        Marker claire = map.addMarker(new MarkerOptions()
                .position(CLAIREFONTAINE)
                .title("Clairefontaine")
                .snippet("How much does the five course menu cost?"));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LUXEMBOURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
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
