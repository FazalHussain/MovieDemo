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
import com.example.fazal.moviedemo.constants.ApiTags;
import com.example.fazal.moviedemo.models.data.MovieData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<MovieData> movieDataList;
    onItemClickListener listener;

    /**
     * Interface definition for on item click listener
     * that will be invoked when view has clicked
     */
    public interface onItemClickListener {
        /**
         * This method will be invoked when view has clicked
         *
         * @param position The item position that has been clicked.
         */
        void onItemClick(int position);
    }

    public MovieAdapter(Context context, List<MovieData> movieDataList,
                        onItemClickListener listener) {
        this.context = context;
        this.movieDataList = movieDataList;
        this.listener = listener;
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
                load(ApiTags.BASE_URL_IMAGE + data.getPosterPath()).
                into(viewHolder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    /**
     * The item view holder inner class
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    /**
     * Refresh the list again
     *
     * @param data
     */
    public void reset(List<MovieData> data){
        movieDataList = data;
        notifyDataSetChanged();
    }

}
