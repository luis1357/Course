package com.example.mvpgithub.Data.Dao;


import com.example.mvpgithub.Data.DataSources.DBDataSource;
import com.example.mvpgithub.Data.Entities.ResponseEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ResponseDao {
    @Query("SELECT * FROM ResponseEntity WHERE _query = :query; ")
    ResponseEntity get_response_from_key(String query);

    @Insert
    void insert_response(ResponseEntity responseEntity);
}
