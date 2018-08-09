package com.yeah.ruisu.week2day3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAnimal extends AppCompatActivity
{
    private List<Animal> AnimalLst;
    private Context MyContext;
    public String InCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animal);

        MyContext = this;

        InCategory = "mammals";

        FillCards();

    }

    public void FillCards()
    {
        AnimalLst = new ArrayList<>();

        /* We create the database helper. */
        DatabaseHelper MyDataHelper = new DatabaseHelper(MyContext);

        /* Get Data from SQLite database and add them to the table, *
         *  open the database for reading.                          */
        SQLiteDatabase db = MyDataHelper.getReadableDatabase();

        /* Now we start the transaction. */
        db.beginTransaction();

        try
        {
            /*String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_OUTLET +
                                    " WHERE outlet_Category = " + InCategory;*/

            String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_OUTLET +
                    " WHERE outlet_Category like outlet_Category";

            //String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_OUTLET;
            Cursor MyCursor = db.rawQuery(selectQuery, null);

            if (MyCursor.getCount() > 0)
            {
                while (MyCursor.moveToNext())
                {
                    /* We read the data from the columns. */
                    int outlet_id = MyCursor.getInt(MyCursor.getColumnIndex("outlet_id"));

                    String outlet_Category = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_Category")
                                                        );
                    String outlet_Animal = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_Animal")
                                                        );
                    String outlet_Description = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_Description")
                                                        );
                    String outlet_Image = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_Image")
                                                        );
                    String outlet_Sound = MyCursor.getString(
                                                        MyCursor.getColumnIndex(
                                                                "outlet_Sound")
                                                        );

                    String[] colText =
                            {
                                    outlet_id + "",
                                    outlet_Category,
                                    outlet_Animal,
                                    outlet_Description,
                                    outlet_Image,
                                    outlet_Sound
                            };

                    for (int i = 0; i < colText.length / 6; i+=6)
                    {
                        String Category = colText[i + 1];
                        //String Animal = colText[i + 2];
                        String Description = colText[i + 3];
                        //String Image = colText[i + 4];
                        String Sound = colText[i + 5];

                        int AnimalId = this.getResources()
                                            .getIdentifier("@string/" +
                                                            colText[i + 2],
                                                            "string",
                                                            this.getPackageName());

                        int ImageId = this.getResources()
                                            .getIdentifier(colText[i + 4],
                                                            "drawable",
                                                            this.getPackageName());

                        AnimalLst.add(new Animal(Category, AnimalId,
                                                Description, ImageId,
                                                Sound));

                        Toast.makeText(MyContext, Category, Toast.LENGTH_SHORT).show();
                    }
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

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        AnimalRecyclerAdapter MyAnmlRcyclrAdptr = new AnimalRecyclerAdapter(AnimalLst);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyAnmlRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }
}
