package com.example.fazal.moviedemo.ViewModels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.communication.IUserDataHandler;
import com.example.fazal.moviedemo.communication.UserRepository;
import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.models.response.SessionResponse;

import java.util.List;

/**
 * Movie View Model Class
 */
public class MovieViewModel extends AndroidViewModel {
    private MutableLiveData<SessionResponse> sessionResponseLiveData;
    private MutableLiveData<MovieResponse> movieResponseLiveData;
    private UserRepository repository;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    /**
     * Constructor
     *
     * @param application Holding the reference of application
     */
    public MovieViewModel(@NonNull Application application) {
        super(application);
        context = application;
        repository = UserRepository.getInstance();
    }

    /**
     * Create Session
     */
    public void createSession() {
        if (sessionResponseLiveData == null) {
            sessionResponseLiveData = new MutableLiveData<SessionResponse>();
        }
        repository.createSession(context, userDataHandler);
    }

    /**
     * Request For Movie Listings
     */
    public void requestMoviesListings() {
        if (movieResponseLiveData == null) {
            movieResponseLiveData = new MutableLiveData<>();
        }
        repository.getMovies(context, userDataHandler);
    }

    /**
     * Fetch the live data of DataWrapper of Type {@linkplain SessionResponse}
     *
     * @return The {@linkplain LiveData<SessionResponse>}
     */
    public LiveData<SessionResponse> getSession() {
        if (sessionResponseLiveData == null) {
            sessionResponseLiveData = new MutableLiveData<>();
        }
        return sessionResponseLiveData;
    }

    /**
     * Fetch the Movie Listings
     *
     * @return The {@linkplain LiveData<MovieResponse>}
     */
    public LiveData<MovieResponse> getMoviesListings() {
        if (movieResponseLiveData == null) {
            movieResponseLiveData = new MutableLiveData<>();
        }
        return movieResponseLiveData;
    }

    /**
     * Call back listener that will be invoked when data received from the server
     */
    private IUserDataHandler userDataHandler = new IUserDataHandler() {

        @Override
        public void onSessionCreated(SessionResponse response) {
                sessionResponseLiveData.postValue(response);
        }

        @Override
        public void onMovieListingsFetched(MovieResponse response) {
            movieResponseLiveData.postValue(response);
        }

        @Override
        public void onError(String error, int errorCode) {
            //Handle Error
        }
    };
}
