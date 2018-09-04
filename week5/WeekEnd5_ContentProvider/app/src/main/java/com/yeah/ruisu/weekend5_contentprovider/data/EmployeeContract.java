package com.yeah.ruisu.weekend5_contentprovider.data;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class EmployeeContract
{
    public static final int EMPLOYEE = 10;
    public static final int EMPLOYEE_ID = 20;

    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DELETED = "deleted";

    /* AUTHORITY is a unique string for the app. */
    public static String AUTHORITY = "com.yeah.ruisu.weekend5_contentprovider";

    public static Uri EMPLOYEE_URI = Uri.parse("content://" +
                                                AUTHORITY +
                                                "/" +
                                                EMPLOYEE_TABLE_NAME);
    public static String EMPLOYEE_TABLE_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                                                        "/" +
                                                        AUTHORITY +
                                                        "/" +
                                                        EMPLOYEE_TABLE_NAME;
    public static String EMPLOYEE_TABLE_ITEM_CONTENT_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                                                            "/" +
                                                            AUTHORITY +
                                                            "/" +
                                                            EMPLOYEE_TABLE_NAME;

    private static final String CREATE_TABLE = " CREATE TABLE " +
            EMPLOYEE_TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_ADDRESS + " TEXT " +
            ")";

    public static void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
