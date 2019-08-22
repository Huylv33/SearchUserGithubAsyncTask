package com.example.sony.training;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.sony.training.activity.ListGitHubUserActivity;
import com.example.sony.training.model.User;
import com.example.sony.training.presenter.ListGithubUserPresenter;
import com.example.sony.training.presenter.ListGithubUserPresenterImpl;
import com.example.sony.training.view.ListGithubUserView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFetchDataListener, ListGithubUserView {
    private ListGithubUserPresenter mPresenter;
    private EditText mKeywordEditText, mLimitNumberEditText;
    private Button mSearchButton;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKeywordEditText = (EditText) findViewById(R.id.keywordEdittext);
        mLimitNumberEditText = (EditText) findViewById(R.id.limitNumberEdittext);
        mSearchButton = (Button) findViewById(R.id.searchButton);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        mPresenter = new ListGithubUserPresenterImpl(this);
        mPresenter.setView(this);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = mKeywordEditText.getText().toString();
                String limit = mLimitNumberEditText.getText().toString();
                if (keyword.isEmpty() || limit.isEmpty()) {
                    return;
                }
                String uri =  "https://api.github.com/search/users?per_page=" + limit + "&q=" + keyword;
                mPresenter.fetchUsers(uri);
            }
        });
    }

    @Override
    public void onFetchDataSuccess(List<User> users) {
        ArrayList<User> items = (ArrayList<User>) users;
        dialog.dismiss();
        Intent intent = new Intent(this, ListGitHubUserActivity.class);
        intent.putParcelableArrayListExtra(Constant.EXTRA_USER_LIST,items);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
    }
}
