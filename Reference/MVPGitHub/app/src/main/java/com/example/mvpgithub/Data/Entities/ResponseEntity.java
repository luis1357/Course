package com.example.mvpgithub.Data.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ResponseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public ResponseEntity(long timestamp, String _query, String response) {
        this.timestamp = timestamp;
        this._query = _query;
        this.response = response;
    }

    @ColumnInfo(name = "_query")
    private String _query;

    @ColumnInfo(name = "response")
    private String response;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String get_query() {
        return _query;
    }

    public void set_query(String _query) {
        this._query = _query;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
