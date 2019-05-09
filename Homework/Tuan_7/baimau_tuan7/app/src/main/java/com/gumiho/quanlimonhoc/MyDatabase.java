package com.gumiho.quanlimonhoc;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper{

    private static String DB_NAME = "dbMonHoc.db";
    private static int DB_VERSION = 1;

    //Define table Monhoc
    private static final String TB_MONHOCS = "tbMonHoc";
    private static final String COL_MONHOC_ID = "monhoc_id";
    private static final String COL_MONHOC_MA = "monhoc_ma";
    private static final String COL_MONHOC_TEN = "monhoc_ten";
    private static final String COL_MONHOC_SOTIET = "monhoc_sotiet";

    public MyDatabase (Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_MONHOCS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptTBMonHocs = "CREATE TABLE " + TB_MONHOCS + "(" +
                COL_MONHOC_MA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COL_MONHOC_TEN + " TEXT," +
                COL_MONHOC_SOTIET + " TEXT)";

        //Execute script
        db.execSQL(scriptTBMonHocs);

    }

    public void getMonHocs (ArrayList<MonHoc> monHocs) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_MONHOCS,
                new String[]{COL_MONHOC_MA,COL_MONHOC_TEN,COL_MONHOC_SOTIET},null,
                null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                MonHoc monHoc = new MonHoc();
                monHoc.setMa(cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA)));
                monHoc.setTen(cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN)));
                monHoc.setSotiet(cursor.getString(cursor.getColumnIndex(COL_MONHOC_SOTIET)));
                monHocs.add(monHoc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    //Insert monhoc
    public void insertMonHocs (MonHoc monHoc) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_MONHOC_MA,monHoc.getMa());
        values.put(COL_MONHOC_TEN,monHoc.getTen());
        values.put(COL_MONHOC_SOTIET,monHoc.getSotiet());
        db.insert(TB_MONHOCS,null,values);

        db.close();
    }

    //Update monhoc
    public boolean updateMonHocs (MonHoc monHoc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_MONHOC_TEN,monHoc.getTen());
        values.put(COL_MONHOC_SOTIET,monHoc.getSotiet());
        return db.update(TB_MONHOCS,values,COL_MONHOC_MA + "=" + monHoc.getMa(),null) > 0;
    }

    public void deleteMonHocs (MonHoc monHoc) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_MONHOCS, COL_MONHOC_MA + "=?",new String[]{String.valueOf(monHoc.getMa())});
        db.close();
    }



}
