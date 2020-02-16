package com.example.marsapp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.marsapp.R;

public class WeatherDetails extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button btnTEST;

    private ImageView backgroundImage;
    private TextView title;
    private TextView releaseDate;
    private TextView rating;
    private TextView overview;
    private Button buttonBack;
    private String baseImageURL = "https://image.tmdb.org/t/p/w500";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_cell);


    }*/

}
