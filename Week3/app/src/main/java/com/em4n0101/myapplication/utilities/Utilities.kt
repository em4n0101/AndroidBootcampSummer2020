package com.em4n0101.myapplication.utilities

import android.content.Context
import com.em4n0101.myapplication.R
import com.em4n0101.myapplication.data.Person

class Utilities {
    companion object {
        fun getUserData(baseContext: Context) = Person (
            baseContext.getString(R.string.personName),
            baseContext.getString(R.string.personDescription),
            baseContext.getString(R.string.personEmail),
            baseContext.getString(R.string.personPhone),
            baseContext.getString(R.string.personLocation)
        )
    }
}