package com.em4n0101.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.em4n0101.myapplication.utilities.Utilities
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populatePersonUI()
    }

    /**
     * Create a Person object and update the UI the his info
     */
    private fun populatePersonUI() {
        val person = Utilities.getUserData(baseContext)
        person_name_text_view.text = person.name
        description_text_view.text = person.description
        person_email_text_view.text = person.email
        person_phone_text_view.text = person.phone
        person_location_text_view.text = person.location
    }
}