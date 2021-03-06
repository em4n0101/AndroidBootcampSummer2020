package com.em4n0101.myapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.em4n0101.myapplication.data.Tutorial
import com.em4n0101.myapplication.utilities.Utilities
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * @author Emmanuel Cruz (em4n0101@gmail.com)
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val keyForCurrentStateCard = "keyForCurrentStateCard"
        const val keyForCurrentTutorialTitle = "keyForCurrentTutorialTitle"
        const val keyForCurrentTutorialDescription = "keyForCurrentTutorialDescription"
        const val keyForCurrentTutorialImageResource = "keyForCurrentTutorialImageResource"
        const val keyForCurrentTutorialUrl = "keyForCurrentTutorialUrl"
    }

    /**
     * Properties
     */
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet
    private var isFront: Boolean = true
    private var currentTutorial: Tutorial? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         *  When the orientation changes recover the previous state of the card (front/back)
         */
        if (savedInstanceState != null) {
            isFront = savedInstanceState.getBoolean(MainActivity.keyForCurrentStateCard, true)
            if (isFront) {
                switch_flip_card.text = getString(R.string.cardFront)
                cardView.alpha = 1.0F
                cardViewBack.alpha = 0.0F
            } else {
                switch_flip_card.text = getString(R.string.cardBack)
                cardView.alpha = 0.0F
                cardViewBack.alpha = 1.0F

                // If the card was on the back side we also get the tutorial data
                val tutorialTitle = savedInstanceState.getString(MainActivity.keyForCurrentTutorialTitle, "")
                val tutorialDescription = savedInstanceState.getString(MainActivity.keyForCurrentTutorialDescription, "")
                val tutorialImage = savedInstanceState.getInt(MainActivity.keyForCurrentTutorialImageResource, R.drawable.logo)
                val tutorialUrl = savedInstanceState.getString(MainActivity.keyForCurrentTutorialUrl, "")

                val tutorialSaved = Tutorial(tutorialTitle, tutorialDescription, tutorialImage, tutorialUrl)
                populateTutorialWith(tutorialSaved)
            }
        }

        populatePersonUI()

        // Animation setup
        val scale = applicationContext.resources.displayMetrics.density
        val cameraDistanceScaleMultiply = 8000
        cardView.cameraDistance = cameraDistanceScaleMultiply * scale
        cardViewBack.cameraDistance = cameraDistanceScaleMultiply * scale

        frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator) as AnimatorSet

        // Changes the side of the card
        switch_flip_card.setOnClickListener {
            val tempSwitch = it as? SwitchMaterial
            if (tempSwitch != null) {
                isFront = if (it.isChecked) {
                    frontAnim.setTarget(cardView)
                    backAnim.setTarget(cardViewBack)
                    frontAnim.start()
                    backAnim.start()
                    it.text = getString(R.string.cardBack)
                    enableElementsWhenIsFront()
                    populateTutorialWith(currentTutorial)
                    false
                } else {
                    frontAnim.setTarget(cardViewBack)
                    backAnim.setTarget(cardView)
                    backAnim.start()
                    frontAnim.start()
                    it.text = getString(R.string.cardFront)
                    enableElementsWhenIsFront()
                    true
                }
            } else {
                return@setOnClickListener
            }
        }

        // Generate a new tutorial when the card is on the back side
        button_generate_random_tutorial.setOnClickListener {
            populateTutorialWith(null)
        }

        // Go to the url of the tutorial when the card is on the back side
        card_inner_tutorial.setOnClickListener {
            if (currentTutorial != null) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(currentTutorial!!.url)
                startActivity(i)
            }
        }

        layout_email.setOnClickListener {
            goToEmailAction()
        }

        layout_location.setOnClickListener {
            goToMapAction()
        }

        layout_phone.setOnClickListener {
            goToPhoneAction()
        }
    }

    private fun enableElementsWhenIsFront() {
        card_inner_tutorial.isEnabled = isFront
        layout_phone.isEnabled = !isFront
        layout_email.isEnabled = !isFront
        layout_location.isEnabled = !isFront
    }

    private fun goToPhoneAction() {
        val phone = Utilities.getUserData(baseContext).phone
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(intent)
    }

    private fun goToMapAction() {
        val uri =
            java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", Utilities.getUserData(baseContext).latitude, Utilities.getUserData(baseContext).longitude)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(mapIntent)
    }

    private fun goToEmailAction() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(Utilities.getUserData(baseContext).email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject))
        startActivity(intent)
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

    /**
     * Create a random Tutorial object and update the back of the card with the info
     */
    private fun populateTutorialWith(tutorialInfo: Tutorial?) {
        if (tutorialInfo == null) {
            this.currentTutorial = Utilities.getRandomTutorial(baseContext)
        } else {
            this.currentTutorial = tutorialInfo
        }

        tutorial_title_text_view.text = currentTutorial?.title
        tutorial_description.text = currentTutorial?.description
        currentTutorial?.imageName?.let { tutorial_image_view.setImageResource(it) }
    }

    /**
     * Save the current side of the card
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(MainActivity.keyForCurrentStateCard, isFront)

        // Save also the current tutorial
        if (!isFront && currentTutorial != null) {
            outState.putString(MainActivity.keyForCurrentTutorialTitle, currentTutorial!!.title)
            outState.putString(MainActivity.keyForCurrentTutorialDescription, currentTutorial!!.description)
            outState.putInt(MainActivity.keyForCurrentTutorialImageResource, currentTutorial!!.imageName)
            outState.putString(MainActivity.keyForCurrentTutorialUrl, currentTutorial!!.url)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_show_about_info) {
            val title = getString(R.string.menu_title, BuildConfig.VERSION_NAME)
            Toast.makeText(baseContext, title, Toast.LENGTH_LONG).show()
        }
        return true
    }
}