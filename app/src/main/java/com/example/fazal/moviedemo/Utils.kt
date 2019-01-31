package com.example.fazal.moviedemo

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Extension function
 * Inject toast in context class using extension function
 */
fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

/**
 *  Extension function
 *  Inject push fragment in context class using extension function
 */
fun Context.pushFragment(context: AppCompatActivity, fragment: Fragment, @IdRes containerID: Int) {
    context.supportFragmentManager.beginTransaction()
            .replace(containerID, fragment).addToBackStack(null).commit()
}