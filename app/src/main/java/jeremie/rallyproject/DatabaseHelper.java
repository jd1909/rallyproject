package jeremie.rallyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 30/12/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    //Logcat tag
    private static final String LOG = "DatabaseHelper";
    //Database Version
    private static final int DATABASE_VERSION=1;
    //Database Name
    private static final String DATABASE_NAME="CityRally";
    //Table Names(more to add later on)
    private static final String TABLE_SQ="SQ";
    //Column names for SimpleQuestions
    private static final String COLUMN_ID_SQ= "SQ_id";
    private static final String COLUMN_QUESTION_SQ="SQ_questions";
    private static final String COLUMN_ANSWER_SQ="SQ_answers";
    private static final String COLUMN_LATITUDE_SQ="SQ_latitude";
    private static final String COLUMN_LONGITUDE_SQ="SQ_longitude";

    //TABLE CREATE
    //SQ table creation
    private static final String CREATE_TABLE_SQ = "CREATE TABLE "+ TABLE_SQ + "(" + COLUMN_ID_SQ + " INTEGER PRIMARY KEY,"
            + COLUMN_QUESTION_SQ + " TEXT," + COLUMN_ANSWER_SQ + " TEXT," + COLUMN_LATITUDE_SQ + " TEXT,"
            + COLUMN_LONGITUDE_SQ + " TEXT" + ")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        // creating required tables
        db.execSQL((CREATE_TABLE_SQ));
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQ);

        //create new tables
        onCreate(db);
    }
    //CREATING ROW in SQ_Table
    public long createSimpleQuestions(SimpleQuestions sq){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_SQ, sq.getId());
        values.put(COLUMN_QUESTION_SQ, sq.getQuestion());
        values.put(COLUMN_ANSWER_SQ, sq.getAnswer());
        values.put(COLUMN_LATITUDE_SQ, sq.getLatitude());
        values.put(COLUMN_LONGITUDE_SQ, sq.getLongitude());
        //insert row
        long SQ_id =db.insert(TABLE_SQ, null,values);
        return SQ_id;
    }
    public List<SimpleQuestions> getAllSQ(){
        List<SimpleQuestions> sq = new ArrayList<SimpleQuestions>();
        String selectQuery = "SELECT * FROM " + TABLE_SQ;
        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do {
                SimpleQuestions simplequest = new SimpleQuestions();
                simplequest.setId(c.getInt((c.getColumnIndex(COLUMN_ID_SQ))));
                simplequest.setQuestion(c.getString((c.getColumnIndex(COLUMN_QUESTION_SQ))));
                simplequest.setAnswer(c.getString((c.getColumnIndex(COLUMN_ANSWER_SQ))));
                simplequest.setLatitude(c.getFloat((c.getColumnIndex(COLUMN_LATITUDE_SQ))));
                simplequest.setLongitude(c.getFloat((c.getColumnIndex(COLUMN_LONGITUDE_SQ))));
                //adding simplequestions to the list
                sq.add(simplequest);

            }while (c.moveToNext());
        }
        return sq;
    }
    /*
 * Updating a tag
 */
    public int updateSQ(SimpleQuestions sq) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_SQ, sq.getQuestion());
        values.put(COLUMN_ANSWER_SQ, sq.getAnswer());
        values.put(COLUMN_LATITUDE_SQ, sq.getLatitude());
        values.put(COLUMN_LONGITUDE_SQ, sq.getLongitude());

        // updating row
        return db.update(TABLE_SQ, values, COLUMN_ID_SQ + " = ?",
                new String[] { String.valueOf(sq.getId()) });
    }


}
