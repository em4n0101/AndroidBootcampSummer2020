package com.em4n0101.mytvshows.view.cast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import androidx.lifecycle.Observer
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.formatPersonBirthday
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import com.em4n0101.mytvshows.viewmodel.cast.CastMemberViewModel
import kotlinx.android.synthetic.main.activity_cast.*
import org.koin.android.ext.android.inject

class CastMemberActivity : AppCompatActivity() {
    private val viewModel: CastMemberViewModel by inject()
    private var currentPerson: Person? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast)

        // Get show pass from previous activity
        val person: Person? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_PERSON)
        person?.let {
            currentPerson = it
            addSearchPersonInDBObservable(it)
            updateUiWithPerson(it)
        }

        favoriteAnimationView.setOnClickListener {
            currentPerson?.let {
                isFavorite = !isFavorite
                if (isFavorite) viewModel.savePerson(it) else viewModel.deletePersonByName(it.name)
                animateFavoriteView()
            }
        }
    }

    private fun addSearchPersonInDBObservable(person: Person) {
        val observer = Observer<Person?> {
            if (it != null) {
                isFavorite = true
                animateFavoriteView()
            }
        }
        viewModel.getPersonByName(person.name).observe(this, observer)
    }

    private fun animateFavoriteView() {
        currentPerson?.let {
            favoriteAnimationView.apply {
                if (isFavorite) {
                    playAnimation()
                } else {
                    cancelAnimation()
                    progress = 0.0f
                }
            }
        }
    }

    private fun updateUiWithPerson(person: Person) {
        setupImageForViewHolder(
            person.image, castImageView,
            loaderAnimationShowPosterView,
            useOriginalImage = true
        )
        castNameTextView.text = person.name
        castBirthdayTextView.text = formatPersonBirthday(person)
        castCountryTextView.text = person.country?.name ?: ""
    }
}