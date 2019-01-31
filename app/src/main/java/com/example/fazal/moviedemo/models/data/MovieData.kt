package com.example.fazal.moviedemo.models.data

import com.google.gson.annotations.SerializedName

/**
 * Movie Data Class
 */
data class MovieData(
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("id")
        val movieID: Long,
        @SerializedName("video")
        val movieVideo: Boolean,
        @SerializedName("vote_average")
        val userScore: Float,
        @SerializedName("title")
        val title: String,
        @SerializedName("popularity")
        val popularity: Float,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("backdrop_path")
        val backdrop_path: String,
        @SerializedName("overview")
        val description: String,
        @SerializedName("release_date")
        val releaseDate:String


        )