package com.example.mvpgithub.Data.Repository;

import java.util.List;

public interface Repo<T> {
    void insert(T item);
    void delete(T item);
    List<T> exec_query();
}
