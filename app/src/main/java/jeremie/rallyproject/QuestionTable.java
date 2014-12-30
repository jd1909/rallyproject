package jeremie.rallyproject;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Alex on 30/12/2014.
 */
public class QuestionTable {
    //Database table
    public static final String TABLE_QUESTION = "simplequestion";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION="question";
    public static final String COLUMN_ANSWER="answer";
    public static final String COLUMN_LAT="latitude";
    public static final String COLUMN_LONG="longitude";

    //Database creation SQL statement

    private static final String DATABASE_CREATE ="create table"
        + TABLE_QUESTION
        + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_QUESTION + " text not null, "
        + COLUMN_ANSWER + " text not null, "
        + COLUMN_LAT + " text not null, "
        + COLUMN_LONG + " text not null, "
        + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(QuestionTable.class.getName(),"Upgrading database from version "
            + oldVersion + " to " +newVersion
            + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        onCreate(database);
    }


}
