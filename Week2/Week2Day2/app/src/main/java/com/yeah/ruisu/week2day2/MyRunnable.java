package com.yeah.ruisu.week2day2;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class MyRunnable implements Runnable
{
    private Handler MyHandler = new Handler(Looper.getMainLooper());
    private Context MyContext;
    private String Book, Author, Year, Genre;

    public MyRunnable(Context InContext, String Book, String Author,
                      String Year, String Genre)
    {
        MyContext = InContext;
        this.Book = Book;
        this.Author = Author;
        this.Year = Year;
        this.Genre = Genre;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread());

        /* We create the database helper. */
        final DatabaseHelper MyDataHelper = new DatabaseHelper(MyContext);

        /* We Insert the data. */
        MyHandler.post(new Runnable() {
            @Override
            public void run()
            {
                MyDataHelper.InsertData(Book, Author, Year, Genre);
            }
        });

    }
}
