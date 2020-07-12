package com.em4n0101.mymovies.entrypoint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.em4n0101.mymovies.MainActivity
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.login.LoginActivity
import com.em4n0101.mymovies.repositories.SharedPrefsRepository

class EntryPointActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_point)

        initComponents()
    }

    private fun initComponents() {
        if (SharedPrefsRepository.hasUserSignIn()) {
            navigateToMain()
        } else {
            navigateToSignIn()
        }
    }

    private fun navigateToSignIn() {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMain() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}