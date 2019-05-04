package com.example.giantviewsample.view.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.giantviewsample.R;

public class FooterProgressViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar footerProgressBar;
    private TextView footerMessageTextView;

    public FooterProgressViewHolder(@NonNull View itemView) {
        super(itemView);
        footerMessageTextView = itemView.findViewById(R.id.footerMessageTextView);
        footerProgressBar = itemView.findViewById(R.id.progressBar);
    }


    public void hideProgressBar() {
        footerProgressBar.setVisibility(View.GONE);
        footerMessageTextView.setText("No more news available");
    }

    public void showProgressBar() {
        footerProgressBar.setVisibility(View.VISIBLE);
        footerMessageTextView.setText("Loading news ...");
    }
}
