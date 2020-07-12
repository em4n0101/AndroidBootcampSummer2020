package com.em4n0101.mymovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        title = getString(R.string.app_name)
    }
}