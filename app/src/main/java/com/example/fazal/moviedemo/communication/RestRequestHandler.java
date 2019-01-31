package com.example.fazal.moviedemo.communication;

import android.content.Context;
import com.example.fazal.moviedemo.BuildConfig;
import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.models.response.SessionResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

class RestRequestHandler {

    /**
     * Create Session
     *
     * @param context holding the reference of an activity.
     * @param mDataCallback Call back to be invoked when response received,
     */
    void createSession(final Context context, final IResponseCallback mDataCallback) {
        IRestClient mRestClient = RestClient.getClient();
        Call<SessionResponse> workerResponseCall = mRestClient.createSession(
                BuildConfig.ApiKey);
        workerResponseCall.enqueue(new GenericCallback<SessionResponse>(mDataCallback, context));
    }

    /**
     * Fetch Movie Listings
     *
     * @param context holding the reference of an activity.
     * @param mDataCallback Call back to be invoked when response received,
     */
    void getMovies(final Context context, final IResponseCallback mDataCallback) {
        IRestClient mRestClient = RestClient.getClient();
        Call<MovieResponse> workerResponseCall = mRestClient.getMovies(
                BuildConfig.ApiKey);
        workerResponseCall.enqueue(new GenericCallback<MovieResponse>(mDataCallback, context));
    }

    /**
     * Generic Callback listener for Retrofit Response.
     * @param <T> The type of Response received
     */
    private class GenericCallback<T> implements Callback<T> {

        IResponseCallback iResponseCallback;
        Context context;

        public GenericCallback(IResponseCallback iResponseCallback, Context context) {
            this.iResponseCallback = iResponseCallback;
            this.context = context;
        }

        @Override
        public void onResponse(Response<T> response, Retrofit retrofit) {
            if (response.isSuccess()) {
                iResponseCallback.onSuccess(response.body());
            }
        }

        @Override
        public void onFailure(Throwable t) {
            iResponseCallback.onError(context.getString(R.string.error_message), 0);
        }
    }
}
