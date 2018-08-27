package com.example.mvpgithub.Presenter;

import android.content.Context;

import com.example.mvpgithub.Contracts.RepoListActivityContract;
import com.example.mvpgithub.Data.Entities.RepoListEntity;
import com.example.mvpgithub.Data.Repository.RepoListResponseRepository;
import com.example.mvpgithub.Pojo.RepoListResponse;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepoListActivityPresenter implements RepoListActivityContract.Presenter {
    private RepoListActivityContract.View view;
    private RepoListResponseRepository repoListResponseRepository;

    public RepoListActivityPresenter(Context context, RepoListActivityContract.View view) {
        this.view = view;
        repoListResponseRepository = new RepoListResponseRepository(context);
    }
    @Override
    public void initPresenter() {
    }

    @Override
    public void populate_view_repo_list(String user_name) {
        Single.fromCallable(() -> {
            RepoListEntity repoListEntity =  repoListResponseRepository.get_repos_from_db(user_name);
            if (repoListEntity == null)
                return Observable.empty();
            return repoListEntity;
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(repoListEntity -> {
                    if (!(repoListEntity instanceof RepoListEntity)) {
                        List<RepoListResponse> repoListResponses =  repoListResponseRepository.get_repos_from_api(user_name).execute().body();
                        RepoListEntity repoListEntity1 = new RepoListEntity(user_name, System.currentTimeMillis() / 1000, new Gson().toJson(repoListResponses));
                        repoListResponseRepository.save_repolist_to_db(repoListEntity1);
                        return Single.just(new Gson().fromJson(repoListEntity1.getRepo_list(), RepoListResponse[].class));
                    }
                    return Single.just(new Gson().fromJson(((RepoListEntity) repoListEntity).getRepo_list(), RepoListResponse[].class));
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoListResponse -> {
                    view.update_repo_recyler(Arrays.asList(repoListResponse));
                });

//
    }
}
