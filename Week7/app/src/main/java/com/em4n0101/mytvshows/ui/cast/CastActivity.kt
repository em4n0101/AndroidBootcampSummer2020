package com.em4n0101.mytvshows.ui.cast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.formatPersonBirthday
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import com.em4n0101.mytvshows.viewModels.PersonsViewModel
import kotlinx.android.synthetic.main.activity_cast.*

class CastActivity : AppCompatActivity() {
    private var checkBoxFavorite: CheckBox? = null
    private lateinit var personsViewModel: PersonsViewModel
    private var currentPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast)

        // get view model
        personsViewModel = ViewModelProvider(this).get(PersonsViewModel::class.java)

        // Get show pass from previous activity
        val person: Person? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_PERSON)
        person?.let {
            currentPerson = it
            updateUiWithPerson(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.show_detail_menu, menu)

        val starMenuItem = menu?.findItem(R.id.actionFavoriteItem)
        checkBoxFavorite = starMenuItem?.actionView as CheckBox
        checkBoxFavorite?.let {
            setupFavoriteToggle(it, currentPerson)
        }

        return true
    }

    private fun setupFavoriteToggle(checkBox: CheckBox?, person: Person?){
        if (checkBox != null && person != null) {
            checkBox.setOnCheckedChangeListener { _, _ ->
                personsViewModel.savePerson(person)
            }
        }
    }

    private fun updateUiWithPerson(person: Person) {
        setupImageForViewHolder(person.image, castImageView, loaderAnimationShowPosterView, useOriginalImage = true)
        castNameTextView.text = person.name
        castBirthdayTextView.text = formatPersonBirthday(person)
        castCountryTextView.text = person.country?.name ?: ""
    }
}