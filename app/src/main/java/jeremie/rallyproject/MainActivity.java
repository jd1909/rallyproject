package jeremie.rallyproject;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseOpenHelper myDbHelper = new DatabaseOpenHelper(this);

        try {
            // check if database exists in app path, if not copy it from assets
            myDbHelper.create();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            // open the database
            myDbHelper.open();
            myDbHelper.getWritableDatabase();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void startRally(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
