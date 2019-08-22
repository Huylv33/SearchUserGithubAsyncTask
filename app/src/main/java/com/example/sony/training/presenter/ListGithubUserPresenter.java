package com.example.sony.training.presenter;

import com.example.sony.training.view.ListGithubUserView;

public interface ListGithubUserPresenter {
    void setView(ListGithubUserView view);

    void fetchUsers(String uri);

    void destroy();
}
