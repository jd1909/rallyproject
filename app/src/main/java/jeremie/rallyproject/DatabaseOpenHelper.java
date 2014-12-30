package jeremie.rallyproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Alex on 29/12/2014.
 */
class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Rally";
    private static final int DATABASE_VERSION = 2;

    //Simple Questions
    private static final String TABLE_NAME_1 = "SimpleQuestions";
    private static final String COLUMN_ID_1 = "_id";
    private static final String COLUMN_Question_1 = "Question";
    private static final String COLUMN_Answer_1 = "Answer";
    private static final String COLUMN_Latitude_1 = "Latitude";
    private static final String COLUMN_Longitude_1 = "Longitude";

    //Simple Questions
    private static final String TABLE_NAME_2 = "MultiQuestions";
    private static final String COLUMN_ID_2 = "_id";
    private static final String COLUMN_Question_2 = "Question";
    private static final String COLUMN_Answer_A_2 = "Answer_A";
    private static final String COLUMN_Answer_B_2 = "Answer_B";
    private static final String COLUMN_Answer_C_2 = "Answer_C";
    private static final String COLUMN_Answer_2 = "Answer";
    private static final String COLUMN_Latitude_2 = "Latitude";
    private static final String COLUMN_Longitude_2 = "Longitude";

    private SQLiteDatabase database;

    private final Context myContext;
    // database path
    private static String DATABASE_PATH = "/data/data/jeremie.rallyproject/databases/";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.myContext = context;

    }

    public void create() throws IOException {
        boolean check = checkDataBase();

        SQLiteDatabase db_Read = null;

        // Creates empty database default system path
        db_Read = this.getWritableDatabase();
        db_Read.close();
        try {
            if (!check) {
                copyDataBase();
            }
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void open() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);
    }


    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

<<<<<<< HEAD
=======
    // retrieves a particular question
>>>>>>> origin/master
    public Cursor getSimpleQuestion(long rowId) throws SQLException {
        Cursor mCursor = database.query(true, TABLE_NAME_1, new String[]{
                        COLUMN_ID_1, COLUMN_Question_1, COLUMN_Answer_1, COLUMN_Latitude_1, COLUMN_Longitude_1},
                COLUMN_ID_1 + " = " + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getMultiQuestion(long rowId) throws SQLException {
        Cursor mCursor = database.query(true, TABLE_NAME_2, new String[] {
                COLUMN_ID_2, COLUMN_Question_2, COLUMN_Answer_A_2, COLUMN_Answer_B_2,COLUMN_Answer_C_2, COLUMN_Answer_2, COLUMN_Latitude_2, COLUMN_Longitude_2 },
                COLUMN_ID_2 + " = " + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
