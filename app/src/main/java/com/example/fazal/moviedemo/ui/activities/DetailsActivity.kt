package com.example.fazal.moviedemo.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.fazal.moviedemo.R
import com.example.fazal.moviedemo.constants.ApiTags
import com.example.fazal.moviedemo.constants.Constants
import com.example.fazal.moviedemo.models.data.MovieData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details2.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.movie_item_row.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.eralp.circleprogressview.ProgressAnimationListener





class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details2)
        setSupportActionBar(toolbar)
        mapData()

    }

    private fun mapData() {
        val movieData: MovieData = intent.getParcelableExtra(Constants.MOVIE_DATA)
        backdrop.background.alpha = 200
        supportActionBar?.title = movieData.originalTitle
        Picasso.get().
                load(ApiTags.BASE_URL_IMAGE + movieData.backdrop_path).
                into(backdrop)
        tvOverview.text = movieData.description
        circle_progress_view.setProgressWithAnimation(movieData.userScorePerc, 2500)

        tvRelDate.text = movieData.releaseDate

    }
}
