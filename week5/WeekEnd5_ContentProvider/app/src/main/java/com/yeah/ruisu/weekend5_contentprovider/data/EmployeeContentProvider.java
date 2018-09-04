package com.yeah.ruisu.weekend5_contentprovider.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;

public class EmployeeContentProvider extends ContentProvider
{
    public static final String TAG = EmployeeContentProvider.class.getCanonicalName();
    public static final UriMatcher myMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    String[] tableColumns = {
            EmployeeContract.COLUMN_ID,
            EmployeeContract.COLUMN_NAME,
            EmployeeContract.COLUMN_AGE,
            EmployeeContract.COLUMN_EMAIL,
            EmployeeContract.COLUMN_ADDRESS,
            EmployeeContract.COLUMN_PHONE
    };

    /***************************************************************
     * We Define a handle to the database helper object, then then *
     *  MainDatabaseHelper class is defined in the next snippet.   *
     ***************************************************************/
    private EmployeeDataBaseHelper employeeDataBaseHelper;

    /* We hold the database object. */
    private SQLiteDatabase db;

    /* We add the URIs which are going to be used by this provider. */
    static {
        myMatcher.addURI(EmployeeContract.AUTHORITY,
                            EmployeeContract.EMPLOYEE_TABLE_NAME,
                            EmployeeContract.EMPLOYEE);
        myMatcher.addURI(EmployeeContract.AUTHORITY,
                            EmployeeContract.EMPLOYEE_TABLE_NAME + "/#",
                            EmployeeContract.EMPLOYEE_ID);
    }

    @Override
    public boolean onCreate()
    {
        employeeDataBaseHelper = new EmployeeDataBaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder)
    {
        int matchedURIType = myMatcher.match(uri);

        if (projection != null &&
                !Arrays.asList(tableColumns).containsAll(Arrays.asList(projection)))
        {
            throw new IllegalArgumentException("No columns found in Projection!");
        }

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(EmployeeContract.EMPLOYEE_TABLE_NAME);

        switch (matchedURIType)
        {
            case EmployeeContract.EMPLOYEE:

                break;

            case EmployeeContract.EMPLOYEE_ID:
                queryBuilder.appendWhere(EmployeeContract.COLUMN_ID +
                                            "=" + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        db = employeeDataBaseHelper.getWritableDatabase();

        Cursor cursor = queryBuilder.query(db, projection,
                                            selection, selectionArgs,
                                            null, null,
                                            sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values)
    {
        db = employeeDataBaseHelper.getWritableDatabase();

        int matchedUriType = myMatcher.match(uri);

        long newId = 0;

        switch (matchedUriType)
        {
            case EmployeeContract.EMPLOYEE:
                newId = db.insert(EmployeeContract.EMPLOYEE_TABLE_NAME,
                                    null, values);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        /* We cn use this as well as                                            *
         *  Uri.parse(EmployeeContract.EMPLOYEE_URI.toString() + "/" + newId);  *
         *                                                                      */
        return ContentUris.withAppendedId(uri, newId);
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {

        int rowDeleted = 0;

        db = employeeDataBaseHelper.getWritableDatabase();

        int matchedUriType = myMatcher.match(uri);

        switch (matchedUriType)
        {

            case EmployeeContract.EMPLOYEE:

                rowDeleted = db.delete(EmployeeContract.EMPLOYEE_TABLE_NAME,
                                        selection,
                                        selectionArgs);
                break;

            case EmployeeContract.EMPLOYEE_ID:

                String idTobeDeleted = uri.getLastPathSegment();

                if (selection != null && !selection.isEmpty())
                {
                    rowDeleted = db.delete(
                            EmployeeContract.EMPLOYEE_TABLE_NAME,
                            EmployeeContract.COLUMN_ID + "=" +
                                    idTobeDeleted +
                                    " AND " +
                                    selection,
                            selectionArgs);
                }
                else
                {
                    rowDeleted = db.delete(EmployeeContract.EMPLOYEE_TABLE_NAME,
                            EmployeeContract.COLUMN_ID + "=" + idTobeDeleted, null);
                }

                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {
        int rowUpdated = 0;

        db = employeeDataBaseHelper.getWritableDatabase();

        int matchedUriType = myMatcher.match(uri);

        switch (matchedUriType)
        {
            case EmployeeContract.EMPLOYEE:
                rowUpdated = db.update(EmployeeContract.EMPLOYEE_TABLE_NAME,
                                        values,
                                        selection,
                                        selectionArgs);
                break;

            case EmployeeContract.EMPLOYEE_ID:

                String idTobeUpdated = uri.getLastPathSegment();

                if (selection != null && !selection.isEmpty())
                {
                    rowUpdated = db.update(EmployeeContract.EMPLOYEE_TABLE_NAME,
                                            values,
                                            EmployeeContract.COLUMN_ID +
                                                    "=" +
                                                    idTobeUpdated +
                                                    " and " +
                                                    selection,
                                            selectionArgs);
                }
                else
                {
                    rowUpdated = db.update(EmployeeContract.EMPLOYEE_TABLE_NAME,
                                            values,
                                            EmployeeContract.COLUMN_ID +
                                                    "=" +
                                                    idTobeUpdated,
                                            null);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowUpdated;
    }
}
