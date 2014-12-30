package jeremie.rallyproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 30/12/2014.
 */
public class QuestionDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "questiontable.db";
    private static final int DATABASE_VERSION = 1;

    public QuestionDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database){
        QuestionTable.onCreate(database);
    }

    //increasing database version

    @Override

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        QuestionTable.onUpgrade(database, oldVersion, newVersion);
    }


}
