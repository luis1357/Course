package com.yeah.ruisu.roomexample;

import android.annotation.SuppressLint;
import android.arch.persistence.room.PrimaryKey;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yeah.ruisu.roomexample.data.database.BookDatabase;
import com.yeah.ruisu.roomexample.data.database.entities.Book;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity
{
    private EditText etBookISBN, etBookAuthor, etBookName;
    private BookDatabase MyBookDatabase;

    private String TAG = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBookISBN = findViewById(R.id.etBookISBN);
        etBookAuthor = findViewById(R.id.etBookAuthor);
        etBookName = findViewById(R.id.etBookName);
        MyBookDatabase = BookDatabase.getDatabase(this);
    }

    @SuppressLint("CheckResult")
    public void onSaveBook(View view)
    {
        final Book MyBook = new Book(etBookISBN.getText().toString(),
                                etBookName.getText().toString(),
                                etBookAuthor.getText().toString());

        Completable.fromAction(() -> MyBookDatabase.MyBookDao()
                                                    .saveBook(MyBook))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "onSaveBook: "),
                        Throwable::printStackTrace);
    }

    public void onGetAllBooks(View view)
    {
        MyBookDatabase.MyBookDao()
                .getAllBooks()
                .subscribeOn(Schedulers.io())
                .flattenAsObservable(list -> list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Book book)
                    {
                        Log.d(TAG, "onNext: " + book.toString());
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete()
                    {
                        Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
