package com.example.mvpgithub.Data.Repository;

import android.content.Context;

import com.example.mvpgithub.Data.Databases.GitHubDB;
import com.example.mvpgithub.Data.Entities.RepoListEntity;
import com.example.mvpgithub.Di.Components.DaggerResponseComponent;
import com.example.mvpgithub.Di.Components.ResponseComponent;
import com.example.mvpgithub.Di.Modules.ContextModule;
import com.example.mvpgithub.Network.API.Github;
import com.example.mvpgithub.Pojo.RepoListResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class RepoListResponseRepository {
    @Inject
    Github github;

    @Inject
    GitHubDB gitHubDB;


    public RepoListResponseRepository(Context context) {
        DaggerResponseComponent.builder()
                .contextModule(new ContextModule(context))
                .build()
                .injectRepo(this);
    }



    public RepoListEntity get_repos_from_db(String user_id) {
        return gitHubDB.repoListDao().get_user_repos(user_id);
    }


    public Call<List<RepoListResponse>> get_repos_from_api(String user_id) {
        return github.get_user_repo(user_id);
    }

    public void save_repolist_to_db(RepoListEntity repoListEntity) {
        gitHubDB.repoListDao().add_repo_list(repoListEntity);
    }

}
