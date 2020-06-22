package com.em4n0101.mymovies.utils

import android.content.Context
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.data.Movie

class Utilities {
    companion object {
        fun createMovies(context: Context) = arrayListOf(
            Movie(context.getString(R.string.movie_1_title), R.drawable.poster_godfather),
            Movie(context.getString(R.string.movie_2_title), R.drawable.poster_dark_knight),
            Movie(context.getString(R.string.movie_3_title), R.drawable.poster_list),
            Movie(context.getString(R.string.movie_4_title), R.drawable.poster_inception),
            Movie(context.getString(R.string.movie_5_title), R.drawable.poster_matrix),
            Movie(context.getString(R.string.movie_6_title), R.drawable.poster_parasite),
            Movie(context.getString(R.string.movie_7_title), R.drawable.poster_interstellar),
            Movie(context.getString(R.string.movie_8_title), R.drawable.poster_lion_king),
            Movie(context.getString(R.string.movie_9_title), R.drawable.poster_the_pianist),
            Movie(context.getString(R.string.movie_10_title), R.drawable.poster_gladiator),
            Movie(context.getString(R.string.movie_11_title), R.drawable.poster_city_lights),
            Movie(context.getString(R.string.movie_12_title), R.drawable.poster_intouchables),
            Movie(context.getString(R.string.movie_13_title), R.drawable.poster_whiplash),
            Movie(context.getString(R.string.movie_14_title), R.drawable.poster_the_prestige),
            Movie(context.getString(R.string.movie_15_title), R.drawable.poster_joker),
            Movie(context.getString(R.string.movie_16_title), R.drawable.poster_wall_e),
            Movie(context.getString(R.string.movie_17_title), R.drawable.poster_1917),
            Movie(context.getString(R.string.movie_18_title), R.drawable.poster_requiem_for_a_ream),
            Movie(context.getString(R.string.movie_19_title), R.drawable.poster_eternal_sunshine)
        )
    }
}