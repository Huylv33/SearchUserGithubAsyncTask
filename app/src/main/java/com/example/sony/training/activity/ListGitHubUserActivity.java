package com.example.sony.training.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sony.training.Constant;
import com.example.sony.training.OnRecyclerViewItemClickListener;
import com.example.sony.training.R;
import com.example.sony.training.adapter.ListGitHubUserAdapter;
import com.example.sony.training.model.User;

import java.util.List;

public class ListGitHubUserActivity extends Activity implements OnRecyclerViewItemClickListener {

    private RecyclerView mRecyclerViewNewsFeed;
    private ListGitHubUserAdapter mGitHubUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_users);
        List<User> users = getIntent().getParcelableArrayListExtra(Constant.EXTRA_USER_LIST);
        mRecyclerViewNewsFeed = (RecyclerView) findViewById(R.id.recyclerItems);
        mGitHubUserAdapter = new ListGitHubUserAdapter(this);
        mGitHubUserAdapter.setOnRecyclerViewItemClickListener(this);
        mGitHubUserAdapter.updateData(users);
        mRecyclerViewNewsFeed.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewNewsFeed.setAdapter(mGitHubUserAdapter);
    }

    @Override
    public void onItemClick(User item) {
        // create an intent
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra(Constant.EXTRA_USER, item);
        this.startActivity(intent);
    }
}
