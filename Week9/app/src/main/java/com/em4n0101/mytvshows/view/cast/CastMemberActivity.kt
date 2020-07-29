package com.em4n0101.mytvshows.view.cast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.app.MyTvShowsApplication
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.formatPersonBirthday
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import com.em4n0101.mytvshows.viewmodel.cast.CastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.cast.CastMemberViewModelFactory
import kotlinx.android.synthetic.main.activity_cast.*

class CastMemberActivity : AppCompatActivity() {
    private var checkBoxFavorite: CheckBox? = null
    private lateinit var viewModel: CastMemberViewModel
    private var currentPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast)

        // get view model
        viewModel = ViewModelProvider(
            this,
            CastMemberViewModelFactory(MyTvShowsApplication.showsRepository)
        )
            .get(CastMemberViewModel::class.java)

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
            if (currentPerson != null) {
                setupFavoriteToggle(it, currentPerson)
                addSearchPersonInDBObservable(currentPerson!!)
            }
        }
        return true
    }

    private fun addSearchPersonInDBObservable(person: Person) {
        val observer = Observer<Person?> {
            if (it != null) checkBoxFavorite?.isChecked = true
        }
        viewModel.getPersonByName(person.name).observe(this, observer)
    }

    /**
     * Depending on the state of the checkbox save or delete person
     */
    private fun setupFavoriteToggle(checkBox: CheckBox?, person: Person?){
        if (checkBox != null && person != null) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.savePerson(person)
                } else {
                    viewModel.deletePersonByName(person.name)
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