package com.example.flyingfishhunter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHighScore extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "HighScore.db";
    public static final String TABLE_NAME = "HighScores_table";
    public static final String TABLE_NAME_1 = "FishDesign_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FISHDESIGNVALUES";
    public static final String COL_3 = "SCORE";

    public DatabaseHighScore(Context context) {
        super(context, DATABASE_NAME , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER, SCORE INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_1 + "(ID INTEGER, FISHDESIGNVALUES TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(db);
    }

    public void insertData(int id,String score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        //cv.put(COL_2, name);
        cv.put(COL_3, score);
        long result = db.insert(TABLE_NAME, null, cv);
    }

    public void insertFishDesignData(int id,String fishDesign)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        //cv.put(COL_2, name);
        cv.put(COL_2, fishDesign);
        long result = db.insert(TABLE_NAME_1, null, cv);
    }

    public Cursor getData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getFishDesignData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_1 + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void updateData(String id, String score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues .put(COL_1, id);
        contentValues .put(COL_3, score);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
    }

    public void updateFishDesignData(String id, String fishDesign)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1 .put(COL_1, id);
        contentValues1 .put(COL_2, fishDesign);
        db.update(TABLE_NAME_1, contentValues1, "ID = ?", new String[]{id});
    }
}
