package com.example.mvpgithub.Di.Components;

import com.example.mvpgithub.Data.Repository.RepoListResponseRepository;
import com.example.mvpgithub.Data.Repository.UserResponseRepository;
import com.example.mvpgithub.Di.Modules.GitHubModule;
import com.example.mvpgithub.Di.Modules.RoomModule;

import dagger.Component;

@Component(modules = {GitHubModule.class, RoomModule.class})
public interface ResponseComponent {
    void injectRepo(UserResponseRepository userResponseRepository);
    void injectRepo(RepoListResponseRepository repoListResponse);
}
