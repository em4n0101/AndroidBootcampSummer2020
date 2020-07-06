package com.em4n0101.mymovies.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.em4n0101.mymovies.MainActivity
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.repositories.SharedPrefsRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonSignIn.setOnClickListener {
            if (credentialsAreValid())
                navigateToMain()
        }
    }

    private fun credentialsAreValid(): Boolean {
        var isNameValid = false
        var isPasswordValid = false

        if (editTextTextPersonName.text.isNullOrBlank() || editTextTextPersonName.text.length < 2) {
            errorInvalidName.visibility = View.VISIBLE
        } else {
            errorInvalidName.visibility = View.GONE
            isNameValid = true
        }

        if (editTextTextPassword.text.isNullOrBlank() || editTextTextPassword.text.length < 4) {
            errorInvalidPassword.visibility = View.VISIBLE
        } else {
            errorInvalidPassword.visibility = View.GONE
            isPasswordValid = true
        }

        return isNameValid && isPasswordValid
    }

    private fun navigateToMain() {
        SharedPrefsRepository.addUser(editTextTextPersonName.text.toString())

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}