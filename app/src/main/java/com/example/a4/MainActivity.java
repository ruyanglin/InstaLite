package com.example.a4;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    static Model model;
    MyAdapter adapter;
    RecyclerView recyclerView;
    RatingBar filterRatingBar;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        model = new Model(this);
        model.addObserver(this);


        recyclerView = findViewById(R.id.recyclerView);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        } else{
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        }

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        filterRatingBar = findViewById(R.id.ratingBar);
        filterRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    ratingBar.setRating(rating);
                    model.curRating = (int) rating;
                    model.filterRating(rating);
                }
            }
        });

        adapter = new MyAdapter(model);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        } else{
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }

    // Clear Images click action
    public void clearImagesOnClick(View view) {
        model.clearAllImages();
    }

    // Load Image Set click action
    public void loadImageSetOnClick(View view) {
        for (String url: model.imageSetNames) {
            model.loadImage(url);
        }
    }


    // Implementation of FloatingActionButton click action
    public void fabOnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = (ViewGroup) view.getRootView();
        View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.url_popup, viewGroup, false);
        final EditText urlInput = viewInflated.findViewById(R.id.urlText);

        builder.setTitle("Load Image from URL");
        builder.setView(viewInflated);

        builder.setPositiveButton("Load", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                model.addNewImage(urlInput.getText().toString());
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public static Model getModel() {
        return model;
    }


    @Override
    public void update(Observable o, Object arg) {
        adapter.notifyDataSetChanged();
    }
}
