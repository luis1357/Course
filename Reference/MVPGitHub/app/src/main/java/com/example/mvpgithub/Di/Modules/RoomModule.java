package com.example.mvpgithub.Di.Modules;


import android.content.Context;

import com.example.mvpgithub.Data.Databases.GitHubDB;
import com.example.mvpgithub.Misc.Constants;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class RoomModule {

    @Provides
    GitHubDB getGithubDB(Context context) {
        return Room.databaseBuilder(context, GitHubDB.class, Constants.DB.DB_NAME).fallbackToDestructiveMigration().build();
    }

}
