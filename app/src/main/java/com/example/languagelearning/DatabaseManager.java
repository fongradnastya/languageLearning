package com.example.languagelearning;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DBHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertModule(Module module) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("module_name", module.getModuleName());
        values.put("module_description", module.getModuleDescription());
        db.insert("Module", null, values);
        db.close();
    }

    public void insertCard(String word, String translation, int module) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", word);
        values.put("translation", translation);
        values.put("module", module);
        db.insert("Card", null, values);
        db.close();
    }

    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Module", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String moduleName = cursor.getString(cursor.getColumnIndex("module_name"));
                @SuppressLint("Range") String moduleDescription = cursor.getString(cursor.getColumnIndex("module_description"));
                modules.add(new Module(moduleName, moduleDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return modules;
    }

    public Cursor getAllCards() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Card", null);
    }
}

