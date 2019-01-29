package com.example.fazal.moviedemo.communication;

import android.content.Context;
import com.example.fazal.moviedemo.BuildConfig;
import com.example.fazal.moviedemo.R;
import com.example.fazal.moviedemo.models.response.SessionResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RestRequestHandler {

    private IRestClient mRestClient;

    public void createSession(final Context context, final IResponseCallback mDataCallback) {
        mRestClient = RestClient.getClient();
        Call<SessionResponse> workerResponseCall = mRestClient.createSession(
                BuildConfig.ApiKey);
        workerResponseCall.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Response<SessionResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mDataCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mDataCallback.onError(context.getString(R.string.error_message), 0);
            }
        });
    }
}
