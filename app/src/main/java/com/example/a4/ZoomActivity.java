package com.example.a4;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

public class ZoomActivity extends AppCompatActivity implements Observer {

    Model model;
    RatingBar imageRatingBar;
    ImageView imageView;
    Button backButton;
    int index;
    int adjustedScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_activity);

        model = MainActivity.getModel();
        model.addObserver(this);

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);


        imageView = findViewById(R.id.imageView);
        System.out.println("Screen height: " + getScreenHeight());
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            adjustedScreenHeight = getScreenHeight();
        } else {
            adjustedScreenHeight = getScreenHeight() - 180;
        }
        imageView.setImageBitmap(model.resizeBitmap(model.activeBlocks.get(index).getBitmap(), adjustedScreenHeight));


        imageRatingBar = findViewById(R.id.imageRatingBar);
        imageRatingBar.setRating(model.activeBlocks.get(index).getRating());
        imageRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    System.out.println("Zoom activity rating changed to " + rating);
                    ratingBar.setRating(rating);
                    model.activeBlocks.get(index).setRating((int) rating);
                    model.filterRating(model.curRating);
                }
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // From https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android
    public int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Nothing needed here
    }
}
