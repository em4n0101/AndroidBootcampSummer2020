package com.em4n0101.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mymovies.utils.Utilities
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get model
        val movieList = Utilities.createMovies(this)

        // setup recycler
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MoviesAdapter(movieList)
    }
}