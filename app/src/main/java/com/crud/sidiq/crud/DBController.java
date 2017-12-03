package com.crud.sidiq.crud;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public DBController(Context applicationcontext) {
        super(applicationcontext, "kampus.db", null, 1);
        Log.d(LOGCAT,"Created");
    }

    @Override
	/* kbuat tabel kampus
	 */
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE kampus ( kampusId INTEGER PRIMARY KEY," +
                " kampusName TEXT,alamat TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"kampus Created");
    }
    @Override
	/*upgrade tabelkampus
	 */
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS kampus";
        database.execSQL(query);
        onCreate(database);
    }
    /* kode untuk insert data ke tabel kampus
     *
     */
    public void addKampus(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kampusName", queryValues.get("kampusName"));
        values.put("alamat", queryValues.get("alamat"));
        database.insert("kampus", null, values);
        database.close();
    }





    /* getAllKampus
     * menampilkan data seluruh kamus ke listview
     *
     */
    public ArrayList<HashMap<String, String>> getAllkampus() {
        ArrayList<HashMap<String, String>> kampusList;
        kampusList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM kampus";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("kampusId", cursor.getString(0));
                map.put("kampusName", cursor.getString(1));
                map.put("alamat", cursor.getString(2));
                kampusList.add(map);
            } while (cursor.moveToNext());
        }

        // return contact list
        return kampusList;
    }


}