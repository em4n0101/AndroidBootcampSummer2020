package com.em4n0101.mymovies.utils

import android.content.Context
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.data.Movie

class Utilities {
    companion object {
        fun createMovies(context: Context) = arrayListOf(
            Movie(context.getString(R.string.movie_1_title), R.drawable.poster_godfather, context.getString(R.string.movie_1_genre), context.getString(R.string.movie_1_release_date), context.getString(R.string.movie_1_duration), context.getString(R.string.movie_1_summary)),
            Movie(context.getString(R.string.movie_2_title), R.drawable.poster_dark_knight, context.getString(R.string.movie_2_genre), context.getString(R.string.movie_2_release_date), context.getString(R.string.movie_2_duration), context.getString(R.string.movie_2_summary)),
            Movie(context.getString(R.string.movie_3_title), R.drawable.poster_list, context.getString(R.string.movie_3_genre), context.getString(R.string.movie_3_release_date), context.getString(R.string.movie_3_duration), context.getString(R.string.movie_3_summary)),
            Movie(context.getString(R.string.movie_4_title), R.drawable.poster_inception, context.getString(R.string.movie_4_genre), context.getString(R.string.movie_4_release_date), context.getString(R.string.movie_4_duration), context.getString(R.string.movie_4_summary)),
            Movie(context.getString(R.string.movie_5_title), R.drawable.poster_matrix, context.getString(R.string.movie_5_genre), context.getString(R.string.movie_5_release_date), context.getString(R.string.movie_5_duration), context.getString(R.string.movie_5_summary)),
            Movie(context.getString(R.string.movie_6_title), R.drawable.poster_parasite, context.getString(R.string.movie_6_genre), context.getString(R.string.movie_6_release_date), context.getString(R.string.movie_6_duration), context.getString(R.string.movie_6_summary)),
            Movie(context.getString(R.string.movie_7_title), R.drawable.poster_interstellar, context.getString(R.string.movie_7_genre), context.getString(R.string.movie_7_release_date), context.getString(R.string.movie_7_duration), context.getString(R.string.movie_7_summary)),
            Movie(context.getString(R.string.movie_8_title), R.drawable.poster_lion_king, context.getString(R.string.movie_8_genre), context.getString(R.string.movie_8_release_date), context.getString(R.string.movie_8_duration), context.getString(R.string.movie_8_summary)),
            Movie(context.getString(R.string.movie_9_title), R.drawable.poster_the_pianist, context.getString(R.string.movie_9_genre), context.getString(R.string.movie_9_release_date), context.getString(R.string.movie_9_duration), context.getString(R.string.movie_9_summary)),
            Movie(context.getString(R.string.movie_10_title), R.drawable.poster_gladiator, context.getString(R.string.movie_10_genre), context.getString(R.string.movie_10_release_date), context.getString(R.string.movie_10_duration), context.getString(R.string.movie_10_summary)),
            Movie(context.getString(R.string.movie_11_title), R.drawable.poster_city_lights, context.getString(R.string.movie_11_genre), context.getString(R.string.movie_11_release_date), context.getString(R.string.movie_11_duration), context.getString(R.string.movie_11_summary)),
            Movie(context.getString(R.string.movie_12_title), R.drawable.poster_intouchables, context.getString(R.string.movie_12_genre), context.getString(R.string.movie_12_release_date), context.getString(R.string.movie_12_duration), context.getString(R.string.movie_12_summary)),
            Movie(context.getString(R.string.movie_13_title), R.drawable.poster_whiplash, context.getString(R.string.movie_13_genre), context.getString(R.string.movie_13_release_date), context.getString(R.string.movie_13_duration), context.getString(R.string.movie_13_summary)),
            Movie(context.getString(R.string.movie_14_title), R.drawable.poster_the_prestige, context.getString(R.string.movie_14_genre), context.getString(R.string.movie_14_release_date), context.getString(R.string.movie_14_duration), context.getString(R.string.movie_14_summary)),
            Movie(context.getString(R.string.movie_15_title), R.drawable.poster_joker, context.getString(R.string.movie_15_genre), context.getString(R.string.movie_15_release_date), context.getString(R.string.movie_15_duration), context.getString(R.string.movie_15_summary)),
            Movie(context.getString(R.string.movie_16_title), R.drawable.poster_wall_e, context.getString(R.string.movie_16_genre), context.getString(R.string.movie_16_release_date), context.getString(R.string.movie_16_duration), context.getString(R.string.movie_16_summary)),
            Movie(context.getString(R.string.movie_17_title), R.drawable.poster_1917, context.getString(R.string.movie_17_genre), context.getString(R.string.movie_17_release_date), context.getString(R.string.movie_17_duration), context.getString(R.string.movie_17_summary)),
            Movie(context.getString(R.string.movie_18_title), R.drawable.poster_requiem_for_a_ream, context.getString(R.string.movie_18_genre), context.getString(R.string.movie_18_release_date), context.getString(R.string.movie_18_duration), context.getString(R.string.movie_18_summary)),
            Movie(context.getString(R.string.movie_19_title), R.drawable.poster_eternal_sunshine, context.getString(R.string.movie_19_genre), context.getString(R.string.movie_19_release_date), context.getString(R.string.movie_19_duration), context.getString(R.string.movie_19_summary))
        )
    }
}