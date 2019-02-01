package com.example.fazal.moviedemo.models.data

import android.os.Parcel
import android.os.Parcelable
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
        val title: String? = "",
        @SerializedName("popularity")
        val popularity: Float,
        @SerializedName("poster_path")
        val posterPath: String? = "",
        @SerializedName("original_language")
        val originalLanguage: String? = "",
        @SerializedName("original_title")
        val originalTitle: String? = "",
        @SerializedName("backdrop_path")
        val backdrop_path: String? = "",
        @SerializedName("overview")
        val description: String? = "",
        @SerializedName("release_date")
        val releaseDate: String? = ""
) : Parcelable {
        constructor(source: Parcel) : this(
                source.readInt(),
                source.readLong(),
                1 == source.readInt(),
                source.readFloat(),
                source.readString(),
                source.readFloat(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeInt(voteCount)
                writeLong(movieID)
                writeInt((if (movieVideo) 1 else 0))
                writeFloat(userScore)
                writeString(title)
                writeFloat(popularity)
                writeString(posterPath)
                writeString(originalLanguage)
                writeString(originalTitle)
                writeString(backdrop_path)
                writeString(description)
                writeString(releaseDate)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<MovieData> = object : Parcelable.Creator<MovieData> {
                        override fun createFromParcel(source: Parcel): MovieData = MovieData(source)
                        override fun newArray(size: Int): Array<MovieData?> = arrayOfNulls(size)
                }
        }

        val userScorePerc: Float
                get() = if (userScore < 0) 0f else userScore * 10

}