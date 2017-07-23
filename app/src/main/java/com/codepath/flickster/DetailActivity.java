package com.codepath.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {
    TextView title;
    TextView oveview;
    TextView date;
    RatingBar ratingBar;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView) findViewById(R.id.tvTitle);
        oveview = (TextView) findViewById(R.id.tvOverView);
        date = (TextView) findViewById(R.id.tvDate);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        image = (ImageView) findViewById(R.id.ivMovie);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        title.setText(movie.getOriginalTitle());
        date.setText(movie.getDate());
        oveview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getVoteAverage());
        image.setImageResource(0);

        Picasso.with(getApplicationContext()).load(movie.getPosterPath()).placeholder(R.drawable.image_play).transform(new RoundedCornersTransformation(10, 10)).into(image);

    }
}
