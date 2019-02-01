package com.example.fazal.moviedemo.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.example.fazal.moviedemo.R
import com.example.fazal.moviedemo.ViewModels.MovieViewModel
import com.example.fazal.moviedemo.pushFragment
import com.example.fazal.moviedemo.ui.fragments.FilterFragment
import com.example.fazal.moviedemo.ui.fragments.MovieDisplayFragment

class MainActivity : AppCompatActivity() {

    private var mCurrentActivity: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        mCurrentActivity = this

        movieViewModel.session.observe(mCurrentActivity!!, Observer { sessionResponseDataWrapper ->
                //Handle Success
                Log.d(TAG, sessionResponseDataWrapper.toString())
                pushFragment(mCurrentActivity!!, MovieDisplayFragment(), R.id.container)
        })

        movieViewModel.createSession()
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.filter_menu -> pushFragment(mCurrentActivity!!, FilterFragment(), R.id.container)
        }
        return true
    }

    /**
     * Set The Title of toolbar
     */
    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun hideMenuItem(position: Int) {
        
    }
}
