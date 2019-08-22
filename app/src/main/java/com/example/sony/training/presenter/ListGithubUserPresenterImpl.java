package com.example.sony.training.presenter;

import com.example.sony.training.OnFetchDataListener;
import com.example.sony.training.handler.FetchDataFromUrl;
import com.example.sony.training.view.ListGithubUserView;

public class ListGithubUserPresenterImpl implements ListGithubUserPresenter {
    private OnFetchDataListener mOnFetchDataListener;
    private ListGithubUserView mView;

    public ListGithubUserPresenterImpl(OnFetchDataListener onFetchDataListener) {
        this.mOnFetchDataListener = onFetchDataListener;
    }

    @Override
    public void setView(ListGithubUserView view) {
        this.mView = view;
    }

    @Override
    public void fetchUsers(String uri) {
        mView.showLoading();
        new FetchDataFromUrl(mOnFetchDataListener).execute(uri);
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
