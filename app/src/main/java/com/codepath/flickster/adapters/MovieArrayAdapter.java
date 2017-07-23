package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Owner on 7/20/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    //Improving Performance with the ViewHolder Pattern
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //double rate = getItem(position).getVoteAverage();
        if (getItem(position).getVoteAverage()<= 5)
            return 0;
        else
            return 1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        // view lookup cache stored in tag
        ViewHolder viewHolder;

        //check the existing view being reused
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            // Get the data item type for this position
            int type = getItemViewType(position);
            // Inflate XML layout based on the type
            convertView = getInflatedLayoutForType(type);
            //find image View
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            //clear out image from convertView
            viewHolder.ivImage.setImageResource(0);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if( viewHolder.tvTitle !=null){
            // populate the data
            viewHolder.tvTitle.setText(movie.getOriginalTitle());
        }
        if( viewHolder.tvOverview !=null){
            viewHolder.tvOverview.setText(movie.getOverview());
        }


        int orientation = convertView.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.image_play).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivImage);

            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdroppath()).placeholder(R.drawable.image_play).transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivImage);
            // ...
        }


        // return the View
        return convertView;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int type) {
        if (type == 0) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        } else if (type == 1) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie_top, null);
        } else {
            return null;
        }

    }

}
