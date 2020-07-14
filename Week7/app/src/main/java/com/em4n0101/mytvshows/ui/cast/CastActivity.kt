package com.em4n0101.mytvshows.ui.cast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.formatPersonBirthday
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.activity_cast.*

class CastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast)

        // Get show pass from previous activity
        val person: Person? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_PERSON)
        person?.let {
            updateUiWithPerson(it)
        }
    }

    private fun updateUiWithPerson(person: Person) {
        setupImageForViewHolder(person.image, castImageView, loaderAnimationShowPosterView, useOriginalImage = true)
        castNameTextView.text = person.name
        castBirthdayTextView.text = formatPersonBirthday(person)
        castCountryTextView.text = person.country?.name ?: ""
    }
}