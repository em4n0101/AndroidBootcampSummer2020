package com.em4n0101.myapplication

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.em4n0101.myapplication.utilities.Utilities
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * Properties
     */
    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet
    private var isFront: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populatePersonUI()

        val scale = applicationContext.resources.displayMetrics.density
        val cameraDistanceScaleMultiply = 8000
        cardView.cameraDistance = cameraDistanceScaleMultiply * scale
        cardViewBack.cameraDistance = cameraDistanceScaleMultiply * scale

        frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator) as AnimatorSet

        switch_flip_card.setOnClickListener {
            val tempSwitch = it as? Switch
            if (tempSwitch != null) {
                isFront = if (it.isChecked) {
                    frontAnim.setTarget(cardView)
                    backAnim.setTarget(cardViewBack)
                    frontAnim.start()
                    backAnim.start()
                    it.text = getString(R.string.cardBack)
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