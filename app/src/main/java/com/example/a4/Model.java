package com.example.a4;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.ClientError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    final String imageSetURL = "https://www.student.cs.uwaterloo.ca/~cs349/w20/assignments/images/";
    ArrayList<String> imageSetNames = new ArrayList<>();
    ArrayList<DisplayBlock> displayBlocks = new ArrayList<>();
    ArrayList<DisplayBlock> activeBlocks = new ArrayList<>();
    int counter = 0;
    int curRating = 0;

    RequestQueue requestQueue;
    Context context;


    public Model(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);

        addImageSet();
    }


    public void filterRating(float rating) {
        activeBlocks.clear();
        for (DisplayBlock block: displayBlocks) {
            if (block.getRating() >= rating) {
                activeBlocks.add(block);
            }
        }
        setChanged();
        notifyObservers();
    }


    public void loadImage(String url) {
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                DisplayBlock block = new DisplayBlock(url, counter, 0, resizeBitmap(response,  700));
                if (block.rating >= curRating) {
                    activeBlocks.add(block);
                }
                displayBlocks.add(block);
                counter++;
                setChanged();
                notifyObservers();
            }

        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ClientError) {
                    Toast.makeText(context,
                            "Error handling request " + error.networkResponse.statusCode + ": " + url,
                            Toast.LENGTH_LONG + 1).show();
                } else {
                    Toast.makeText(context, "Error handling request: " + error, Toast.LENGTH_LONG + 1).show();
                }
            }
        });
        requestQueue.add(request);
    }


    public Bitmap resizeBitmap(Bitmap bitmap, int height) {
        float ratioXY = (float) bitmap.getWidth() / (float) bitmap.getHeight();
        int widthW = (int) (height * ratioXY);
        return Bitmap.createScaledBitmap(bitmap, widthW, height, false);
    }


    public void addImageSet() {
        imageSetNames.add(imageSetURL + "bunny.jpg");
        imageSetNames.add(imageSetURL + "chinchilla.jpg");
        imageSetNames.add(imageSetURL + "deer.jpg");
        imageSetNames.add(imageSetURL + "doggo.jpg");
        imageSetNames.add(imageSetURL + "ducks.jpg");
        imageSetNames.add(imageSetURL + "fox.jpg");
        imageSetNames.add(imageSetURL + "hamster.jpg");
        imageSetNames.add(imageSetURL + "hedgehog.jpg");
        imageSetNames.add(imageSetURL + "husky.jpg");
        imageSetNames.add(imageSetURL + "kitten.png");
        imageSetNames.add(imageSetURL + "loris.jpg");
        imageSetNames.add(imageSetURL + "puppy.jpg");
        imageSetNames.add(imageSetURL + "running.jpg");
        imageSetNames.add(imageSetURL + "sleepy.png");
    }

    public void clearAllImages() {
        imageSetNames.clear();
        displayBlocks.clear();
        activeBlocks.clear();
        counter = 0;

        addImageSet();
        setChanged();
        notifyObservers();
    }

    public void addNewImage(String url) {
        loadImage(url);
        setChanged();
        notifyObservers();
    }

}
