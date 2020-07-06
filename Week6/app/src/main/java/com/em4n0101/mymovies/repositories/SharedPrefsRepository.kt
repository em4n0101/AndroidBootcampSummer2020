package com.em4n0101.mymovies.repositories

import android.content.Context
import com.em4n0101.mymovies.MyMoviesApplication

object SharedPrefsRepository {
    private const val SHARED_PREFS_REPOSITORY = "SHARED_PREFS_REPOSITORY"
    private const val SHARED_PREFS_KEY_LOGIN = "SHARED_PREFS_KEY_LOGIN"
    private const val SHARED_PREFS_KEY_USERNAME = "SHARED_PREFS_KEY_USERNAME"
    private const val SHARED_PREFS_KEY_PROFILE_IMAGE = "SHARED_PREFS_KEY_PROFILE_IMAGE"
    private val sharedPrefs = MyMoviesApplication.getAppContext().getSharedPreferences(SHARED_PREFS_REPOSITORY, Context.MODE_PRIVATE)

    // When the user sign in we save the name for displayed inside the app
    fun addUser(username: String) {
        sharedPrefs.edit().putBoolean(SHARED_PREFS_KEY_LOGIN, true).apply()
        sharedPrefs.edit().putString(SHARED_PREFS_KEY_USERNAME, username).apply()
    }

    // When you are successfully logged-in, store a boolean value into shared prefs that the user is logged-in.
    fun hasUserSignIn() = sharedPrefs.getBoolean(SHARED_PREFS_KEY_LOGIN, false)
    fun getUserName() = sharedPrefs.getString(SHARED_PREFS_KEY_USERNAME, "")

    // When the user sign out we need to remove the data saved
    fun clearSharedPrefs() {
        sharedPrefs.edit().putBoolean(SHARED_PREFS_KEY_LOGIN, false).apply()
        sharedPrefs.edit().putString(SHARED_PREFS_KEY_USERNAME, "").apply()
    }

    fun saveProfileImage(imageUrl: String) = sharedPrefs.edit().putString(
        SHARED_PREFS_KEY_PROFILE_IMAGE, imageUrl).apply()
    fun getProfileImage() = sharedPrefs.getString(SHARED_PREFS_KEY_PROFILE_IMAGE, "")
}