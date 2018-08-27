package com.example.mvpgithub.Data.Databases;

import com.example.mvpgithub.Data.Dao.RepoListDao;
import com.example.mvpgithub.Data.Dao.ResponseDao;
import com.example.mvpgithub.Data.DataSources.DBDataSource;
import com.example.mvpgithub.Data.Entities.RepoListEntity;
import com.example.mvpgithub.Data.Entities.ResponseEntity;
import com.example.mvpgithub.Misc.Constants;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ResponseEntity.class, RepoListEntity.class}, version = Constants.DB.VERSION_NUMBER, exportSchema = false)
public abstract class GitHubDB extends RoomDatabase implements DBDataSource {
    public abstract ResponseDao responseDao();
    public abstract RepoListDao repoListDao();

}
