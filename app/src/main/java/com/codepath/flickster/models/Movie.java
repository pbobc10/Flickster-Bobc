package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Owner on 7/19/2017.
 */

//import java.util.ArrayList;

public class Movie implements Serializable {

    public String getPosterPath() {
        return  String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdroppath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdroppath);
    }

    public String getDate() {
        return date;
    }

    String posterPath;
    String originalTitle;
    String overview;
    String backdroppath;
    String date;
    double voteAverage;


    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdroppath = jsonObject.getString("backdrop_path");
        this.date = jsonObject.getString("release_date");
        this.voteAverage = jsonObject.getDouble("vote_average");
    }

    public  static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for(int x = 0; x < array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
