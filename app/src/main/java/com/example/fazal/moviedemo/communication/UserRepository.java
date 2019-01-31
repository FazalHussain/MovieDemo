package com.example.fazal.moviedemo.communication;

import android.content.Context;

import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.models.response.SessionResponse;


public class UserRepository {
    private Context context;
    private IUserDataHandler userDataHandler;
    private static UserRepository userRepository = new UserRepository();
    private RestRequestHandler restRequestHandler;

    private UserRepository() {
        restRequestHandler = new RestRequestHandler();
    }

    public static UserRepository getInstance() {
        return userRepository;
    }

    /**
     * Create Session.
     *
     * @param context Holding the reference of an activity.
     * @param iUserDataHandler callback to be invoked when API Call initiate.
     */
    public void createSession(Context context, IUserDataHandler iUserDataHandler) {
        userDataHandler = iUserDataHandler;
        restRequestHandler.createSession(context, mDataHandler);
    }

    /**
     * Fetch Movie Listings.
     *
     * @param context Holding the reference of an activity.
     * @param iUserDataHandler callback to be invoked when API Call initiate.
     */
    public void getMovies(Context context, IUserDataHandler iUserDataHandler) {
        userDataHandler = iUserDataHandler;
        restRequestHandler.getMovies(context, mDataHandler);
    }

    private IResponseCallback mDataHandler = new IResponseCallback() {

        @Override
        public void onSuccess(Object object) {
            String className = object.getClass().getSimpleName();
            if (userDataHandler != null) {
                switch (className) {
                    case "SessionResponse":
                        userDataHandler.onSessionCreated((SessionResponse) object);
                        break;
                    case "MovieResponse":
                        userDataHandler.onMovieListingsFetched((MovieResponse) object);
                        break;
                }
            }
        }

        @Override
        public void onError(String error, int errorCode) {
            userDataHandler.onError(error, errorCode);
        }

    };
}
