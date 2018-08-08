package com.yeah.ruisu.roomexample.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.yeah.ruisu.roomexample.data.database.entities.Book;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface BookDao
{
    @Insert
    void saveBook(Book InBook);

    @Query("SELECT * FROM Book")
    Single<List<Book>> getAllBooks();

}
