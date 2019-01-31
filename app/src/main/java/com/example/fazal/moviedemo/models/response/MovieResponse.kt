package com.example.fazal.moviedemo.models.response

import com.example.fazal.moviedemo.models.data.MovieData
import com.google.gson.annotations.SerializedName

/**
 * Movie Response Class
 */
data class MovieResponse(
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Long,
        @SerializedName("total_pages")
        val totalPages: Long,
        @SerializedName("results")
        val data: List<MovieData> = mutableListOf()
        )