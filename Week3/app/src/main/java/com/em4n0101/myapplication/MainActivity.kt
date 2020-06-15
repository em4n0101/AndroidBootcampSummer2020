package com.em4n0101.myapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.em4n0101.myapplication.data.Tutorial
import com.em4n0101.myapplication.utilities.Utilities
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.activity_main.*

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

                // Get the tutorial data
                val tutorialTitle = savedInstanceState.getString(MainActivity.keyForCurrentTutorialTitle, "")
                val tutorialDescription = savedInstanceState.getString(MainActivity.keyForCurrentTutorialDescription, "")
                val tutorialImage = savedInstanceState.getInt(MainActivity.keyForCurrentTutorialImageResource, R.drawable.logo)
                val tutorialUrl = savedInstanceState.getString(MainActivity.keyForCurrentTutorialUrl, "")

                val tutorialSaved = Tutorial(tutorialTitle, tutorialDescription, tutorialImage, tutorialUrl)
                populateTutorialWith(tutorialSaved)
            }
        }

        populatePersonUI()

        val scale = applicationContext.resources.displayMetrics.density
        val cameraDistanceScaleMultiply = 8000
        cardView.cameraDistance = cameraDistanceScaleMultiply * scale
        cardViewBack.cameraDistance = cameraDistanceScaleMultiply * scale

        frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator) as AnimatorSet

        switch_flip_card.setOnClickListener {
            val tempSwitch = it as? SwitchMaterial
            if (tempSwitch != null) {
                isFront = if (it.isChecked) {
                    frontAnim.setTarget(cardView)
                    backAnim.setTarget(cardViewBack)
                    frontAnim.start()
                    backAnim.start()
                    it.text = getString(R.string.cardBack)
                    populateTutorialWith(currentTutorial)
                    false
                } else {
                    frontAnim.setTarget(cardViewBack)
                    backAnim.setTarget(cardView)
                    backAnim.start()
                    frontAnim.start()
                    it.text = getString(R.string.cardFront)
                    true
                }
            } else {
                return@setOnClickListener
            }
        }

        button_generate_random_tutorial.setOnClickListener {
            populateTutorialWith(null)
        }

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
}