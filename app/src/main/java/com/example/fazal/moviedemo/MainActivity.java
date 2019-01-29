package com.example.fazal.moviedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.fazal.moviedemo.ViewModels.DataWrapper;
import com.example.fazal.moviedemo.ViewModels.MovieViewModel;
import com.example.fazal.moviedemo.models.response.SessionResponse;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private MainActivity mCurrentActivity;
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieViewModel movieViewModel = ViewModelProviders.
                of(this).
                get(MovieViewModel.class);
        mCurrentActivity = this;

        movieViewModel.getSession().
                observe(mCurrentActivity, new Observer<DataWrapper<SessionResponse>>() {
            @Override
            public void onChanged(@Nullable DataWrapper<SessionResponse>
                                          sessionResponseDataWrapper) {
                if (sessionResponseDataWrapper.getErrorMessage() != null) {
                    //Handle Error
                    Log.d(TAG, sessionResponseDataWrapper.getErrorMessage());
                    if (sessionResponseDataWrapper.getErrorCode() ==
                            HttpURLConnection.HTTP_UNAUTHORIZED) {
                        finish();
                    }
                } else {
                    //Handle Success
                    Log.d(TAG, sessionResponseDataWrapper.toString());
                }
            }
        });


        movieViewModel.createSession();
    }
}
