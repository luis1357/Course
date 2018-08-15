package com.yeah.ruisu.week3day2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Person_Database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_OUTLET = "PersonTable";
    public static final String CREATE_TABLE_OUTLET =
                                "CREATE TABLE IF NOT EXISTS " +
                                    TABLE_OUTLET + " (" +
                                        "outlet_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        "outlet_Name TEXT," +
                                        "outlet_Age TEXT," +
                                        "outlet_Gender TEXT," +
                                        "outlet_Nation TEXT)";

    public static final String DELETE_TABLE_OUTLET = "DROP TABLE IF EXISTS " +
                                                        TABLE_OUTLET;

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version)
    {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_OUTLET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(DELETE_TABLE_OUTLET);

        /* We create the table again. */
        onCreate(db);
    }

    public ArrayList<Person> getAllData() {
        ArrayList<Person> Personlist = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle")
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_OUTLET,
                null);

        while (res.moveToNext()) {
            String id = res.getString(0);
            String name = res.getString(1);
            String age = res.getString(2);
            String gender = res.getString(3);
            String nation = res.getString(4);

            Person newPerson = new Person(name, age, gender, nation);
            Personlist.add(newPerson);
        }

        return Personlist;
    }


        public void InsertData(String outlet_Name, String outlet_Age,
                           String outlet_Gender, String outlet_Nation)
    {
        /* Open the database for writing. */
        SQLiteDatabase db = this.getWritableDatabase();

        /* We start the transaction. */
        db.beginTransaction();

        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put ("outlet_Name", outlet_Name);
            values.put ("outlet_Age", outlet_Age);
            values.put ("outlet_Gender", outlet_Gender);
            values.put ("outlet_Nation", outlet_Nation);

            /* Insert Row. */
            long i = db.insert(TABLE_OUTLET, null, values);
            Log.i("Insert", i + "");

            /* Insert into table successful. */
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            /* We end the transaction. */
            db.endTransaction();

            /* And we close the database. */
            db.close();
        }
    }
}
