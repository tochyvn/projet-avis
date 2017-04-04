package ngassam.tochap.lionel.advice_project.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ngassam.tochap.lionel.advice_project.Metier.Advice;

/**
 * Created by tochl on 01/04/2017.
 */

public class AdviceDb extends SQLiteOpenHelper {

    public static String TABLE = "Advice";

    public AdviceDb(Context context) {

        super(context, "ADVICE_DB", null, 1);
        Log.d("gjg", "hdgfff");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Database", "database");
        String query = "CREATE TABLE ADVICE (" +
                "_id integer PRIMARY KEY autoincrement, " +
                "title TEXT, " +
                "description TEXT, " +
                "note INTEGER, " +
                "auteur TEXT, " +
                "categorie TEXT, " +
                "longitude double, " +
                "latitude double" +
                ");";
        db.execSQL(query);
        Log.d("DB", query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAdvice(Advice advice) {
        Log.d("DB", "ADVICE_DB : Add Advice ...");

        ContentValues values = new ContentValues();
        values.put("title", advice.getTitle());
        values.put("description", advice.getDescription());
        values.put("note", advice.getNote());
        values.put("auteur", advice.getAuteur());
        values.put("categorie", advice.getCategorie());
        values.put("longitude", advice.getLongitude());
        values.put("latitude", advice.getLatitude());

        this.getWritableDatabase().insert(TABLE, null, values);

        Log.d("DB", "ADVICE_DB : Add Advice done.");
    }

    public Cursor fetchAll() {

        String[] columns = new String[] {
                "_id",
                "title",
                "description",
                "note",
                "auteur",
                "categorie",
                "longitude",
                "latitude"
        };

        String orderBy = "_id ASC";

        Cursor cursor = this.getReadableDatabase().query(
                TABLE, columns,
                null, null,
                null, null,
                orderBy
        );

        return cursor;
    }

    public Cursor fetchById(long id) {

        String[] cols = new String[] {
                "_id",
                "title",
                "description",
                "note",
                "auteur",
                "categorie",
                "longitude",
                "latitude"
        };

        String whereClause =  "_id = ?";

        String[] whereArgs = new String[] {
                String.valueOf(id)
        };

        Cursor cursor = this.getReadableDatabase().query(TABLE, cols,
                whereClause, whereArgs, null, null, null);

        return cursor;
    }

}
