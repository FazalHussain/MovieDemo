package com.example.fazal.moviedemo.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.ViewModels.MovieViewModel;
import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.ui.helpers.adapters.MovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDisplayFragment extends Fragment {

    @BindView(R.id.movie_rv)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_display_fragment,
                container,
                false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        viewModel.getMoviesListings().observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(@Nullable MovieResponse response) {
                assert response != null;
                populateRecyclerView(response);
            }
        });


        viewModel.requestMoviesListings();

    }

    private void populateRecyclerView(MovieResponse response) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MovieAdapter adapter = new MovieAdapter(getActivity(), response.getData());
        recyclerView.setAdapter(adapter);


        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
