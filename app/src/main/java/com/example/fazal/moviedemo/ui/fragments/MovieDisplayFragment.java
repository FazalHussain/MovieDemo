package com.example.fazal.moviedemo.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.ViewModels.MovieViewModel;
import com.example.fazal.moviedemo.constants.Constants;
import com.example.fazal.moviedemo.models.data.MovieData;
import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.ui.activities.DetailsActivity;
import com.example.fazal.moviedemo.ui.activities.MainActivity;
import com.example.fazal.moviedemo.ui.helpers.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDisplayFragment extends Fragment {

    @BindView(R.id.movie_rv)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MainActivity mCurrentActivity;
    private ArrayList<MovieData> list;
    private MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_display_fragment,
                container,
                false);
        Bundle bundle = getArguments();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        mCurrentActivity.setToolbarTitle(getString(R.string.movie_listings));

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

        list = new ArrayList<>(response.getData());

        adapter = new MovieAdapter(getActivity(), list, new MovieAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mCurrentActivity, DetailsActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        filterData(response.getData());

        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void filterData(List<MovieData> data) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            final int maxYear = Integer.parseInt(bundle.getString(Constants.MAX_YEAR_FILTER));
            int minYear = Integer.parseInt(bundle.getString(Constants.MIN_YEAR_FILTER));
            List<MovieData> filterList = Stream.of(data).filter(obj ->
                    ((Integer.valueOf(obj.getReleaseDate().split("-")[0]) >= minYear) &&
                            (Integer.valueOf(obj.getReleaseDate().split("-")[0]) <= maxYear)))
                    .collect(Collectors.toList());
            list.clear();
            list.addAll(filterList);
            adapter.reset(list);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item=menu.findItem(R.id.filter_menu);
        item.setVisible(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
