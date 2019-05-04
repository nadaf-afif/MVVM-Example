package com.example.giantviewsample.view.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.giantviewsample.R;
import com.example.giantviewsample.model.Article;


public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    TextView newsTitleTextView;
    TextView authorNameTextView;
    TextView postedOnTextView;

    ImageView newsImageView;

    public NewsItemViewHolder( View itemView) {
        super(itemView);

        newsTitleTextView = itemView.findViewById(R.id.newsTitleTextView);
        authorNameTextView = itemView.findViewById(R.id.authorNameTextView);
        postedOnTextView = itemView.findViewById(R.id.postedOnTextView);

        newsImageView = itemView.findViewById(R.id.newsImageView);
    }


    public void setValues(Article newsArticle, Context context){

        newsTitleTextView.setText(newsArticle.getTitle());

        if (newsArticle.getAuthor()!= null) {
            authorNameTextView.setText(newsArticle.getAuthor());
        }else{
            authorNameTextView.setText(newsArticle.getSource().getName());
        }

        postedOnTextView.setText(newsArticle.getPublishedAt());

        Glide.with(context).load(newsArticle.getUrlToImage()).into(newsImageView);

    }


}
