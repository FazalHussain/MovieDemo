package com.example.fazal.moviedemo.ui.helpers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.models.data.MovieData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<MovieData> movieDataList;

    public MovieAdapter(Context context, List<MovieData> movieDataList) {
        this.context = context;
        this.movieDataList = movieDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.movie_item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MovieData data = movieDataList.get(i);
        viewHolder.tvTitle.setText(data.getOriginalTitle());
        viewHolder.tvDescription.setText(data.getDescription());
        viewHolder.tvReleaseDate.setText(data.getReleaseDate());
        Picasso.get().
                load("https://image.tmdb.org/t/p/w154/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg").
                into(viewHolder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    /**
     * The item view holder inner class
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPoster)
        AppCompatImageView ivPoster;
        @BindView(R.id.tvTitle)
        AppCompatTextView tvTitle;
        @BindView(R.id.tvReleaseDate)
        TextView tvReleaseDate;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        /**
         * Constructor
         *
         * @param itemView The root view of layout.
         * @see ButterKnife#bind(View)
         */
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
