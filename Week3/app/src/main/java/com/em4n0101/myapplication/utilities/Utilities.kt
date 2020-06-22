package com.em4n0101.myapplication.utilities

import android.content.Context
import com.em4n0101.myapplication.R
import com.em4n0101.myapplication.data.Person
import com.em4n0101.myapplication.data.Tutorial

class Utilities {
    companion object {
        fun getUserData(baseContext: Context) = Person (
            baseContext.getString(R.string.personName),
            baseContext.getString(R.string.personDescription),
            baseContext.getString(R.string.personEmail),
            baseContext.getString(R.string.personPhone),
            baseContext.getString(R.string.personLocation)
        )

        fun getRandomTutorial(baseContext: Context) = setOf(
            Tutorial(
                baseContext.getString(R.string.tutorial1Title),
                baseContext.getString(R.string.tutorial1Description),
                R.drawable.tutorial_one,
                baseContext.getString(R.string.tutorial1Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial2Title),
                baseContext.getString(R.string.tutorial2Description),
                R.drawable.tutorial_two,
                baseContext.getString(R.string.tutorial2Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial3Title),
                baseContext.getString(R.string.tutorial3Description),
                R.drawable.tutorial_three,
                baseContext.getString(R.string.tutorial3Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial4Title),
                baseContext.getString(R.string.tutorial4Description),
                R.drawable.tutorial_four,
                baseContext.getString(R.string.tutorial4Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial5Title),
                baseContext.getString(R.string.tutorial5Description),
                R.drawable.tutorial_five,
                baseContext.getString(R.string.tutorial5Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial6Title),
                baseContext.getString(R.string.tutorial6Description),
                R.drawable.tutorial_six,
                baseContext.getString(R.string.tutorial6Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial7Title),
                baseContext.getString(R.string.tutorial7Description),
                R.drawable.tutorial_seven,
                baseContext.getString(R.string.tutorial7Url)
            ),
            Tutorial(
                baseContext.getString(R.string.tutorial8Title),
                baseContext.getString(R.string.tutorial8Description),
                R.drawable.tutorial_eight,
                baseContext.getString(R.string.tutorial8Url)
            )
        ).random()
    }
}