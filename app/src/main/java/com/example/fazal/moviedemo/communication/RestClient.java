package com.example.fazal.moviedemo.communication;

import com.example.fazal.moviedemo.BuildConfig;
import com.example.fazal.moviedemo.constants.ApiTags;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClient {

    private static IRestClient retrofitCalls;

    static IRestClient getClient() {
        if (retrofitCalls == null) {
            OkHttpClient okHttpClient = new OkHttpClient();

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                    HttpLoggingInterceptor.Level.NONE);


            okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
            okHttpClient.interceptors().add(loggingInterceptor);
            Retrofit.Builder builder = new Retrofit.Builder();

            Retrofit client = builder.baseUrl(ApiTags.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitCalls = client.create(IRestClient.class);
        }
        return retrofitCalls;
    }
}