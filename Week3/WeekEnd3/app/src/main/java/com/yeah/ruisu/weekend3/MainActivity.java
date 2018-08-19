package com.yeah.ruisu.weekend3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yeah.ruisu.weekend3.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.weekend3.models.BooksData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity: ";

    EditText etBookSearcher;

    private List<Book> BookList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void BindControls()
    {
        etBookSearcher = findViewById(R.id.etBookSearcher);
    }

    public void SearchBooks(View view)
    {

        String KeyValue = etBookSearcher.getText().toString();

        Intent MyIntent = new Intent();

        MyIntent.setAction(Constants.MY_SERVICE_BROADCAST);
        MyIntent.putExtra("data", KeyValue);

        sendBroadcast(MyIntent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BooksData data)
    {
        FillLayout(data);
    }

    public void FillLayout (BooksData InBook)
    {
        FillBooksList(InBook);

        SaveBookData(InBook);

        ReadBookData();
    }

    public void FillBooksList (BooksData InBook)
    {
        BookList = new ArrayList<>();

        String BookTitle, BookAuthor,
                BookDescription, BookPic;

        for (int i = 0; i < 10; i++)
        {
            BookTitle = GetBookTitle(InBook, i);
            BookAuthor = GetBookAuthor(InBook, i);
            BookDescription = GetBookDescription(InBook, i);
            BookPic = GetBookPicURL(InBook, i);

            BookList.add(new Book(BookTitle,
                                    BookAuthor,
                                    BookDescription,
                                    BookPic));
        }

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        BookRecyclerAdapter MyBookRcyclrAdptr = new BookRecyclerAdapter(BookList);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyBookRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }

    public String GetBookTitle (BooksData InBook, int BkNmbr)
    {
        return InBook.getItems().get(BkNmbr).getVolumeInfo().getTitle();
    }

    public String GetBookAuthor (BooksData InBook, int BkNmbr)
    {
        return InBook.getItems().get(BkNmbr).getVolumeInfo().getAuthors().toString();
    }

    public String GetBookDescription (BooksData InBook, int BkNmbr)
    {
        return InBook.getItems().get(BkNmbr).getVolumeInfo().getDescription();
    }

    public String GetBookPicURL (BooksData InBook, int BkNmbr)
    {
        return InBook.getItems().get(BkNmbr).getVolumeInfo().getImageLinks().getThumbnail();
    }

    public void SaveBookData(BooksData InBook)
    {
        String filename = "testFilemost.srl";
        ObjectOutput out;

        try
        {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),
                                                                                "") +
                                                                File.separator +
                                                                filename));
            out.writeObject(InBook);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void ReadBookData()
    {
        ObjectInputStream input;
        String filename = "testFilemost.srl";

        try
        {
            input = new ObjectInputStream(new FileInputStream (
                                                        new File(
                                                                new File(getFilesDir(),
                                                                        "") +
                                                                        File.separator+filename)));

            BooksData myBookObject = (BooksData) input.readObject();
            Log.v("serialization",
                    "Title book 1 = " + myBookObject.getItems()
                                                            .get(0)
                                                            .getVolumeInfo()
                                                            .getTitle());
            input.close();

        }
        catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
