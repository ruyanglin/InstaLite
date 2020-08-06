package com.example.a4;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import androidx.annotation.NonNull;

import java.util.Observable;
import java.util.Observer;

public class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<DisplayBlockViewHolder> implements Observer {

    Model model;

    public MyAdapter(Model model) {
        this.model = model;
        this.model.addObserver(this);
    }

    @NonNull
    @Override
    public DisplayBlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_block, parent, false);
        return new DisplayBlockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayBlockViewHolder holder, int position) {
        int index = model.displayBlocks.get(position).getId();

        holder.imageView.setImageBitmap(model.activeBlocks.get(index).bitmap);
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.ratingBar.setRating(model.activeBlocks.get(index).rating);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(model.context, ZoomActivity.class);
                intent.putExtra("index", index);
                Activity activity = (Activity) model.context;
                activity.startActivityForResult(intent, 1);
            }
        });

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    model.activeBlocks.get(position).setRating((int) rating);
                    model.filterRating(model.curRating);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.activeBlocks.size();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
