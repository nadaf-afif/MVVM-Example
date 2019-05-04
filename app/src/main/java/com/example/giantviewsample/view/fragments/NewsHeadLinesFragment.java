package com.example.giantviewsample.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giantviewsample.R;
import com.example.giantviewsample.model.Article;
import com.example.giantviewsample.model.NewsDataModel;
import com.example.giantviewsample.view.adapters.NewsHeadlineListAdapter;
import com.example.giantviewsample.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadLinesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MainActivityViewModel mViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Article> mNewsArticles = new ArrayList<>();
    private NewsHeadlineListAdapter mAdapter;

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragmet_new_headline, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        initObserver();
        fetchNewsInformation();
    }

    private void initViews(View view) {

        mRecyclerView = view.findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    private void fetchNewsInformation() {
        mViewModel.getNewsHeadlines();
    }

    private void initObserver() {

        mViewModel.mNewsModelMutableData.observe(this, new Observer<NewsDataModel>() {
            @Override
            public void onChanged(@Nullable NewsDataModel newsDataModel) {
                mSwipeRefreshLayout.setRefreshing(false);
                setDataToViewPager(newsDataModel);
            }
        });
    }

    private void setDataToViewPager(NewsDataModel newsDataModel) {

        if (mViewModel.mPageNumber == 1) {
            mNewsArticles.clear();
        }

        mNewsArticles.addAll(newsDataModel.getArticles());

        if (mAdapter == null) {
            mAdapter = new NewsHeadlineListAdapter(mNewsArticles, getActivity(), mViewModel, false);
        }

        mRecyclerView.swapAdapter(mAdapter, false);

        if (newsDataModel.getArticles().size() == 0 && mAdapter != null) {
            mAdapter.setLoadMore(false);
        }

    }

    @Override
    public void onRefresh() {
        mViewModel.mPageNumber = 1;
        fetchNewsInformation();

        if (mAdapter !=null){
            mAdapter.setLoadMore(true);
        }
    }
}
