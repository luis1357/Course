package com.yeah.ruisu.week2day2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableOut extends Activity
{
    private Context MyContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_out);

        MyContext = this;

        /* We create the database helper. */
        DatabaseHelper MyDataHelper = new DatabaseHelper(MyContext);

        /* Insert Sample Data. */
        /*MyDataHelper.InsertData("The Last Wish",
                                "Andzrej Sapkowski",
                                "1993",
                                "Fantasy");*/

        /* Reference to tableLayout */
        TableLayout MyTblLyt = (TableLayout) findViewById(R.id.LytTbl);

        /* Add Header row. */
        TableRow rowHeader = new TableRow(MyContext);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams
                                                (
                                                    TableLayout.LayoutParams.MATCH_PARENT,
                                                    TableLayout.LayoutParams.WRAP_CONTENT
                                                )
                                    );
        String[] headerText =
                {
                        "ID",
                        "Book",
                        "Author",
                        "Year",
                        "Genre"
                };

        for (String c:headerText)
        {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams
                                                (
                                                    TableRow.LayoutParams.WRAP_CONTENT,
                                                    TableRow.LayoutParams.WRAP_CONTENT
                                                )
                                );
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }

        MyTblLyt.addView(rowHeader);

        /* Get Data from SQLite database and add them to the table, *
         *  open the database for reading.                          */
        SQLiteDatabase db = MyDataHelper.getReadableDatabase();

        /* Now we start the transaction. */
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_OUTLET;
            Cursor MyCursor = db.rawQuery(selectQuery, null);

            if (MyCursor.getCount() > 0)
            {
                while (MyCursor.moveToNext())
                {
                    /* We read the data from the columns. */
                    int outlet_id = MyCursor.getInt(MyCursor.getColumnIndex("outlet_id"));

                    String outlet_book = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_book")
                                                        );
                    String outlet_author = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_author")
                                                        );
                    String outlet_year = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_year")
                                                        );
                    String outlet_genre = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_genre")
                                                        );

                    /* Data Rows. */
                    TableRow row = new TableRow(MyContext);
                    row.setLayoutParams(new TableRow.LayoutParams(
                                                        TableLayout.LayoutParams.MATCH_PARENT,
                                                        TableLayout.LayoutParams.WRAP_CONTENT)
                                                        );

                    String[] colText =
                            {
                                    outlet_id + "",
                                    outlet_book,
                                    outlet_author,
                                    outlet_year,
                                    outlet_genre
                            };

                    for (String text:colText)
                    {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(
                                                            TableRow.LayoutParams.WRAP_CONTENT,
                                                            TableRow.LayoutParams.WRAP_CONTENT)
                                                            );
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(14);
                        tv.setPadding(5,5,5,5);
                        tv.setText(text);
                        row.addView(tv);
                    }

                    MyTblLyt.addView(row);

                }
            }

            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            /* We Finish the Transaction. */
            db.endTransaction();
            /* And we also close it. */
            db.close();
        }
    }

}
