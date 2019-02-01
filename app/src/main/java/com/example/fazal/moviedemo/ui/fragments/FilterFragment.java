package com.example.fazal.moviedemo.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.constants.Constants;
import com.example.fazal.moviedemo.ui.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.fazal.moviedemo.UtilsKt.pushFragment;

public class FilterFragment extends Fragment {

    @BindView(R.id.etMinYear)
    AppCompatEditText etMinYear;

    @BindView(R.id.etMaxYear)
    AppCompatEditText etMaxYear;

    MainActivity mCurrentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        mCurrentActivity.setToolbarTitle(getString(R.string.filter_by));
    }

    @Override
    public void onDestroyView() {
        mCurrentActivity.setToolbarTitle(getString(R.string.movie_listings));
        super.onDestroyView();
    }

    @OnClick(R.id.filterBtn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filterBtn:
                applyFilter();
                break;
        }
    }

    private void applyFilter() {
        if (isValid()) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.MIN_YEAR_FILTER, etMinYear.getText().toString());
            bundle.putString(Constants.MAX_YEAR_FILTER, etMaxYear.getText().toString());
            MovieDisplayFragment fragment = new MovieDisplayFragment();
            fragment.setArguments(bundle);
            pushFragment(mCurrentActivity ,mCurrentActivity, fragment, R.id.container);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.filter_menu);
        item.setVisible(false);
    }

    private boolean isValid() {
        if (etMinYear.length() == 0 || etMaxYear.length() == 0)
            return false;
        return true;
    }
}
