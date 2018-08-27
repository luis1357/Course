package com.example.mvpgithub.Data.Repository;

import android.content.Context;

import com.example.mvpgithub.Data.Databases.GitHubDB;
import com.example.mvpgithub.Data.Entities.ResponseEntity;
import com.example.mvpgithub.Di.Components.DaggerResponseComponent;
import com.example.mvpgithub.Di.Modules.ContextModule;
import com.example.mvpgithub.Network.API.Github;
import com.example.mvpgithub.Pojo.UserResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class UserResponseRepository implements Repo<ResponseEntity> {

    @Inject
    GitHubDB dbDataSource ;

    @Inject
    Github apiDataSource;


    public UserResponseRepository(Context context) {
        DaggerResponseComponent.builder().contextModule(new ContextModule(context)).build().injectRepo(this);
    }

    public ResponseEntity get_users_from_db(String query) {
        return dbDataSource.responseDao().get_response_from_key(query);
    }

    public Call<UserResponse> get_users_from_net(String query) {
        return apiDataSource.search_user(query);
    }


    public void save_response_to_db(ResponseEntity response) {
        dbDataSource.responseDao().insert_response(response);
    }


    @Override
    public void insert(ResponseEntity item) {

    }

    @Override
    public void delete(ResponseEntity item) {

    }

    @Override
    public List<ResponseEntity> exec_query() {
        return null;
    }
}
