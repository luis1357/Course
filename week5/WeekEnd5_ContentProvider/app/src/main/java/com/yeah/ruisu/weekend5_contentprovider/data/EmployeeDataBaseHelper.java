package com.yeah.ruisu.weekend5_contentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "employee.db";
    public static final int DATABASE_VERSION = 1;

    public EmployeeDataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            EmployeeContract.onCreate(db);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
