package com.example.languagelearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Module (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "module_name TEXT, module_description TEXT)");
        db.execSQL("CREATE TABLE Card (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "word TEXT, translation TEXT, module INTEGER, FOREIGN KEY(module) " +
                "REFERENCES Module(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Module");
        db.execSQL("DROP TABLE IF EXISTS Card");
        onCreate(db);
    }
}
