package com.example.mvpgithub.Presenter;



import android.content.Context;

import com.example.mvpgithub.Contracts.MainActivityContract;
import com.example.mvpgithub.Data.Entities.ResponseEntity;
import com.example.mvpgithub.Data.Repository.UserResponseRepository;
import com.example.mvpgithub.Pojo.UserResponse;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private UserResponseRepository userResponseRepository;

    public MainActivityPresenter(Context context, MainActivityContract.View view) {
        this.view = view;
        userResponseRepository = new UserResponseRepository(context);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void populate_users(String user_id) {
        Single.fromCallable(() -> {
           ResponseEntity responseEntity = userResponseRepository.get_users_from_db(user_id);
           if (responseEntity == null)
               return Observable.empty();
           return responseEntity;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap( item -> {
                    if ( item instanceof ResponseEntity)
                        return Single.just(new Gson().fromJson(((ResponseEntity) item).getResponse(), UserResponse.class));
                    UserResponse userResponse = userResponseRepository.get_users_from_net(user_id).execute().body();
                    ResponseEntity responseEntity = new ResponseEntity(System.currentTimeMillis() / 1000,
                            user_id,
                            new Gson().toJson(userResponse, UserResponse.class));
                    userResponseRepository.save_response_to_db(responseEntity);
                    return Single.just(userResponse);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Object o) {
                        UserResponse userResponse = (UserResponse)o;
                        view.display_user_info(userResponse.getUsers());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }
}
