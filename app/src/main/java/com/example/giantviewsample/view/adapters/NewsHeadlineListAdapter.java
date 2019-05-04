package com.example.giantviewsample.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giantviewsample.R;
import com.example.giantviewsample.model.Article;
import com.example.giantviewsample.view.viewHolders.FooterProgressViewHolder;
import com.example.giantviewsample.view.viewHolders.NewsItemViewHolder;
import com.example.giantviewsample.viewModel.MainActivityViewModel;

import java.util.List;

public class NewsHeadlineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Article> mNewsArticles;
    private Context mContext;
    private MainActivityViewModel mViewModel;

    private boolean mLoadMoreData = true;

    private boolean isTopicsAdapter;

    private static final int NORMAL_VIEW = 0;
    private static final int FOOTER_VIEW = 1;

    private LayoutInflater mInflater;

    public NewsHeadlineListAdapter(List<Article> newsArticles, Context context, MainActivityViewModel viewModel, boolean isTopicsAdapter) {
        this.mNewsArticles = newsArticles;
        this.mContext = context;
        this.mViewModel = viewModel;
        this.isTopicsAdapter = isTopicsAdapter;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == NORMAL_VIEW){
            View view = mInflater.inflate(R.layout.layout_news_list_item, viewGroup, false);
            return new NewsItemViewHolder(view);
        }else{
            View view = mInflater.inflate(R.layout.layout_item_footer, viewGroup, false);
            return new FooterProgressViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {

        if (viewHolder instanceof NewsItemViewHolder){
            NewsItemViewHolder holder = (NewsItemViewHolder) viewHolder;
            Article newsArticle = mNewsArticles.get(index);
            holder.setValues(newsArticle, mContext);
        }

        if (mLoadMoreData && index > getItemCount() - 4){
            mViewModel.mPageNumber++;

            if (isTopicsAdapter){
                mViewModel.getNewsByTopics();
            }else {
                mViewModel.getNewsHeadlines();
            }
        }

        if (viewHolder instanceof FooterProgressViewHolder){
            if (!mLoadMoreData) {
                ((FooterProgressViewHolder) viewHolder).hideProgressBar();
            }else{
                ((FooterProgressViewHolder) viewHolder).showProgressBar();
            }
        }

    }

    @Override
    public int getItemCount() {
        return mNewsArticles.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);

        if (position < mNewsArticles.size()){
            return NORMAL_VIEW;
        }else{
            return FOOTER_VIEW;
        }
    }

    public void setLoadMore(boolean value){
        mLoadMoreData = value;
    }

}
