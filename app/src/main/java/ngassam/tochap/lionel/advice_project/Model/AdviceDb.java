package ngassam.tochap.lionel.advice_project.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tochl on 01/04/2017.
 */

public class AdviceDb extends SQLiteOpenHelper {

    public AdviceDb(Context context) {
        super(context, "ADVICE_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE ADVICE (" +
                "_id integer PRIMARY KEY autoincrement, " +
                "title TEXT, " +
                "description TEXT, " +
                "note INTEGER, " +
                "auteur TEXT, " +
                "categorie TEXT" +
                ");";
        db.execSQL(query);
        Log.d("DB", query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAdvice() {

    }
}
