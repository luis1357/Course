package com.example.mvpgithub.Data.Dao;

import com.example.mvpgithub.Data.Entities.RepoListEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RepoListDao {
    @Query("SELECT * FROM RepoListEntity WHERE user_id = :user_id;")
    RepoListEntity get_user_repos(String user_id);

    @Insert
    void add_repo_list(RepoListEntity entity);
}
