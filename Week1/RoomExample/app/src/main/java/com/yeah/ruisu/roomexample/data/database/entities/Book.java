package com.yeah.ruisu.roomexample.data.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Book {
    @PrimaryKey
    @NonNull
    String ISBN;

    String Name;
    String Author;

    public Book(@NonNull String ISBN, String Name, String Author) {
        this.ISBN = ISBN;
        this.Name = Name;
        this.Author = Author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN = " + ISBN + '\'' +
                ", name = " + Name + '\'' +
                ", author = " + Author + '\'' +
                '}';
    }

    @NonNull
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(@NonNull String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
