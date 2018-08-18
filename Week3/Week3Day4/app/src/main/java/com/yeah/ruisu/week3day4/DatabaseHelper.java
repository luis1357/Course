package com.yeah.ruisu.week3day4;

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
    public static final String DATABASE_NAME = "Weather_Database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_OUTLET = "WeatherJSONTable";
    public static final String CREATE_TABLE_OUTLET =
                                "CREATE TABLE IF NOT EXISTS " +
                                    TABLE_OUTLET + " (" +
                                        "outlet_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        "outlet_JSON TEXT)";

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


        public void InsertData(String outlet_JSON)
    {
        /* Open the database for writing. */
        SQLiteDatabase db = this.getWritableDatabase();

        /* We start the transaction. */
        db.beginTransaction();

        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put ("outlet_JSON", outlet_JSON);

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