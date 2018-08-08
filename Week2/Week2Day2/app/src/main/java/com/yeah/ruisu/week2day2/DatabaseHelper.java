package com.yeah.ruisu.week2day2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Books";
    public static final int DATABASE_VERSION = 3;
    public static final String TABLE_OUTLET = "tbloutletdata";
    public static final String CREATE_TABLE_OUTLET = "CREATE TABLE IF NOT EXISTS " +
                                                    TABLE_OUTLET + " (" +
                                                    "outlet_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "outlet_book TEXT," +
                                                    "outlet_author TEXT," +
                                                    "outlet_year TEXT," +
                                                    "outlet_genre TEXT)";


    public static final String DELETE_TABLE_OUTLET = "DROP TABLE IF EXISTS " +
                                                    TABLE_OUTLET;

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
    public void onUpgrade(SQLiteDatabase db, int OldVer, int NewVer)
    {
        db.execSQL(DELETE_TABLE_OUTLET);

        /* We create the table again. */
        onCreate(db);
    }

    public void InsertData(String outlet_book, String outlet_author,
                            String outlet_year, String outlet_genre)
    {
        /* Open the database for writing. */
        SQLiteDatabase db = this.getWritableDatabase();

        /* We start the transaction. */
        db.beginTransaction();

        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put ("outlet_book", outlet_book);
            values.put ("outlet_author", outlet_author);
            values.put ("outlet_year", outlet_year);
            values.put ("outlet_genre", outlet_genre);

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
