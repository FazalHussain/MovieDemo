package com.example.fazal.moviedemo.communication;

import com.example.fazal.moviedemo.constants.ApiTags;
import com.example.fazal.moviedemo.models.response.MovieResponse;
import com.example.fazal.moviedemo.models.response.SessionResponse;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface definition for rest service client
 */
public interface IRestClient {

    @GET(ApiTags.SESSION_END_POINT)
    Call<SessionResponse> createSession(@Query("api_key") String apiKey);

    @GET(ApiTags.MOVIE_LISTING_END_POINT)
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);
}