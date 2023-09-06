package com.example.finaltestshujabits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String EMPLOYEES_TABLE = "Employees";
    public static final String EMP_PHOTO = "photo";

    private static final String CREATE_EMPLOYEES_TABLE =
            "CREATE TABLE " + EMPLOYEES_TABLE + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMP_PHOTO + " BLOB NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEES_TABLE);
        onCreate(db);
    }

    public long insertImage(Image image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMP_PHOTO, Utility.getBytes(image.getPhoto()));
        long id = db.insert(EMPLOYEES_TABLE, null, values);
        db.close();
        return id;
    }

//    public Cursor getAllImages() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery("SELECT * FROM " + EMPLOYEES_TABLE, null);
//    }
}
