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

    public void insertCard(Word word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("word", word.getWord());
        values.put("translation", word.getTranslation());
        values.put("module", word.getModule());
        db.insert("Card", null, values);
        db.close();
    }

    public void insertCards(List<Word> words) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Word word : words) {
            ContentValues values = new ContentValues();
            values.put("word", word.getWord());
            values.put("translation", word.getTranslation());
            values.put("module", word.getModule());
            db.insert("Card", null, values);
        }
        db.close();
    }


    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Module", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String moduleName = cursor.getString(
                        cursor.getColumnIndex("module_name"));
                List<Word> words = getCardsByModule(moduleName);
                @SuppressLint("Range") String moduleDescription = cursor.getString(
                        cursor.getColumnIndex("module_description"));
                modules.add(new Module(moduleName, moduleDescription, words));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return modules;
    }
    public Module getModuleByName(String moduleName) {
        Module module = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Module WHERE module_name = ?", new String[]{moduleName});

        if (cursor.moveToFirst()) {
            List<Word> words = getCardsByModule(moduleName);
            @SuppressLint("Range") String moduleDescription = cursor.getString(
                    cursor.getColumnIndex("module_description"));
            module = new Module(moduleName, moduleDescription, words);
            System.out.println(words);
        }
        cursor.close();
        db.close();
        return module;
    }
    public List<Word> getAllCards() {
        List<Word> words = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Card", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String word = cursor.getString(
                        cursor.getColumnIndex("word"));
                @SuppressLint("Range") String translation = cursor.getString(
                        cursor.getColumnIndex("translation"));
                @SuppressLint("Range") String module = cursor.getString(
                        cursor.getColumnIndex("module"));
                words.add(new Word(word, translation, module));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return words;
    }
    public List<Word> getCardsByModule(String moduleName) {
        List<Word> words = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Card WHERE module = ?",
                new String[]{moduleName});
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String word = cursor.getString(
                        cursor.getColumnIndex("word"));
                @SuppressLint("Range") String translation = cursor.getString(
                        cursor.getColumnIndex("translation"));
                @SuppressLint("Range") String module = cursor.getString(
                        cursor.getColumnIndex("module"));
                words.add(new Word(word, translation, module));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return words;
    }

    public void deleteModule(String moduleName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Module", "module_name=?", new String[]{moduleName});
        db.close();
    }
}

