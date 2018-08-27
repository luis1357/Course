package com.example.mvpgithub.Data.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RepoListEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String user_id;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "repo_list")
    private String repo_list;

    public RepoListEntity(String user_id, long timestamp, String repo_list) {
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.repo_list = repo_list;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRepo_list() {
        return repo_list;
    }

    public void setRepo_list(String repo_list) {
        this.repo_list = repo_list;
    }


}
