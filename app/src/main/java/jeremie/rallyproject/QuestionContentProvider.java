package jeremie.rallyproject;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Alex on 30/12/2014.
 */
public class QuestionContentProvider extends ContentProvider {
    // database
    //used for UriMatcher
    private static final int QUESTIONS = 10;
    private static final int QUESTIONS_ID = 20;

    private static final String AUTHORITY = "jeremy.rallyproject";
    private static final String BASE_PATH = "questions";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/questions";
    private static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/question";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, QUESTIONS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", QUESTIONS_ID);
    }

    private QuestionDatabaseHelper database;
    @Override
    public boolean onCreate(){
        database = new QuestionDatabaseHelper(getContext());
        return false;

    }
    @Override
    public Cursor query
}
