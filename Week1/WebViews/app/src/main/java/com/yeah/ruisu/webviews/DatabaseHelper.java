package com.yeah.ruisu.webviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDatabase";

    public static final String TABLE_NAME = "Contacts";
    public static final String CONTACT_NAME = "Name";
    public static final String CONTACT_NUMBER = "Number";

    public static final String TAG = "MyDBTag";

    public DatabaseHelper (Context InContext)
    {
        super(InContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                                CONTACT_NAME + " TEXT, " +
                                CONTACT_NUMBER + " TEXT PRIMARY KEY" +
                                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void SaveNewContact(MyContact InContact)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues MyContentValues = new ContentValues();

        MyContentValues.put(CONTACT_NAME, InContact.getName());
        MyContentValues.put(CONTACT_NUMBER, InContact.getNumber());

        int HadSaved = (int) MyDatabase.insert(TABLE_NAME, null, MyContentValues);

        Log.d(TAG, "SaveNewContact: " + HadSaved);
    }

    public List<MyContact> getContacts ()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        List<MyContact> contacts = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do {
               MyContact contact = new MyContact(cursor.getString(0),
                                                    cursor.getString(1));
               contacts.add(contact);
            } while (cursor.moveToNext());
        }

        return contacts;
    }
}
