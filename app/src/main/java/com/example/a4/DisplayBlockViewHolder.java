package com.example.a4;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import androidx.recyclerview.widget.RecyclerView;


public class DisplayBlockViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    RatingBar ratingBar;

    public DisplayBlockViewHolder(View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.imageView);
        this.ratingBar = itemView.findViewById(R.id.imageRatingBar);
    }
}
