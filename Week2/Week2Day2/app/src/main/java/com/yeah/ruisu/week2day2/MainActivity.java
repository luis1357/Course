package com.yeah.ruisu.week2day2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    /* First we declare the Text inputs, so we can get their data. */
    EditText etBook, etAuthor, etYear, etGenre;

    private Context MyContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyContext = this;

        /* We bind the controls. */
        BindControls();
    }

    /* This little function will let us bind the controls. */
    public void BindControls ()
    {
        etBook = findViewById(R.id.etBookName);
        etAuthor = findViewById(R.id.etAuthor);
        etYear = findViewById(R.id.etYear);
        etGenre = findViewById(R.id.etGenre);
    }

    public String GetTextFromEditText (EditText InText)
    {
        return InText.getText().toString();
    }

    public void GoToTable(View view)
    {
        startActivity(new Intent(MainActivity.this, TableOut.class));
    }

    public void AddNewBook(View view)
    {
        /* We create the database helper. */
        /*DatabaseHelper MyDataHelper = new DatabaseHelper(MyContext);*/

        /* We prepare the variables to hold the values. */
        String Book, Author, Year, Genre;

        /* We assign them the values. */
        Book = GetTextFromEditText(etBook);
        Author = GetTextFromEditText(etAuthor);
        Year = GetTextFromEditText(etYear);
        Genre = GetTextFromEditText(etGenre);

        /* Insert Sample Data. */
        /*MyDataHelper.InsertData(Book, Author, Year, Genre)*/;

        MyRunnable WriteToDB = new MyRunnable(MyContext, Book, Author, Year, Genre);
        Thread MyThread = new Thread(WriteToDB);
        MyThread.start();


    }
}
