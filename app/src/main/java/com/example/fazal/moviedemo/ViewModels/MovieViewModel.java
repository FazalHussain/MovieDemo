package com.example.fazal.moviedemo.ViewModels;

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
import com.example.fazal.moviedemo.models.response.SessionResponse;

import java.util.List;

/**
 * Movie View Model Class
 */
public class MovieViewModel extends AndroidViewModel {
    private MutableLiveData<DataWrapper<SessionResponse>> sessionResponseLiveData;
    private UserRepository repository;
    Context context;

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
            sessionResponseLiveData = new MutableLiveData<DataWrapper<SessionResponse>>();
        }
        repository.createSession(context, userDataHandler);
    }

    /**
     * Fetch the live data of DataWrapper of Type {@linkplain SessionResponse}
     *
     * @return The {@linkplain LiveData<DataWrapper<SessionResponse>}
     */
    public LiveData<DataWrapper<SessionResponse>> getSession() {
        if (sessionResponseLiveData == null) {
            sessionResponseLiveData = new MutableLiveData<DataWrapper<SessionResponse>>();
        }
        return sessionResponseLiveData;
    }

    /**
     * Call back listener that will be invoked when data received from the server
     */
    private IUserDataHandler userDataHandler = new IUserDataHandler() {

        @Override
        public void onSessionCreated(SessionResponse response) {
                sessionResponseLiveData.postValue(new DataWrapper<SessionResponse>(
                        response,
                        null,
                        0
                ));
        }

        @Override
        public void onError(String error, int errorCode) {
            sessionResponseLiveData.postValue(new DataWrapper<SessionResponse>(
                    null,
                    error,
                    errorCode
            ));
        }
    };
}
