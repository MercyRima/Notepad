package com.example.notetaking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper<insert> extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes( id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,noteText TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNote(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("title",note.getTittle());
        contentValues.put("noteText",note.getNoteText());
        //contentValues.put("noteText"note.getNote);
        long insert = sqLiteDatabase.insert("notes" ,null,contentValues);
        sqLiteDatabase.close();
        return insert;
    }
    public List<Note>getNotes(){
        List<Note>notesList =new ArrayList<Note>();
        String query = "SELECT * FROM notes";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.moveToFirst()==true) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTittle(cursor.getString(cursor.getColumnIndex("title")));
                note.setNoteText(cursor.getString(cursor.getColumnIndex("noteText")));
                notesList.add(note);

            }
            while (cursor.moveToNext()==true);
        }
        sqLiteDatabase.close();
        return notesList;
    }
}
