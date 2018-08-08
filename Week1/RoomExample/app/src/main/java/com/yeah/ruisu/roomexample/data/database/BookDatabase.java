package com.yeah.ruisu.roomexample.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.yeah.ruisu.roomexample.data.database.dao.BookDao;
import com.yeah.ruisu.roomexample.data.database.entities.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase
{
    private static BookDatabase INSTANCE;
    public abstract BookDao MyBookDao();



    public static BookDatabase getDatabase(Context InContext)
    {
        if(INSTANCE == null)
        {
            synchronized (BookDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(InContext.getApplicationContext(),
                                                        BookDatabase.class,
                                                        "word_database").build();
                }
            }
        }

        return INSTANCE;

    }

}
