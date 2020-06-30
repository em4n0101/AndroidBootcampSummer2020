package com.em4n0101.mymovies.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.login.LoginActivity
import com.em4n0101.mymovies.repositories.SharedPrefsRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewUsername.text = SharedPrefsRepository.getUserName()
        buttonSignOut.setOnClickListener {
            navigateToSignIn()
        }
        buttonAbout.setOnClickListener {
            showAbout()
        }
        buttonChangeProfileImage.setOnClickListener {
            chooseProfileImage()
        }

        activity?.title = getString(R.string.profile)

        // Get profile image
        val profileImage = SharedPrefsRepository.getProfileImage()
        if (profileImage != null && profileImage.isNotEmpty()) {
            imageViewProfile.setImageURI(Uri.parse(profileImage))
        }
    }

    private fun chooseProfileImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), REQUEST_PROFILE_IMAGE)
    }

    private fun showAbout() {
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.about_title))
                .setMessage(resources.getString(R.string.about_message))
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_PROFILE_IMAGE) {
            imageViewProfile.setImageURI(data?.data)
            SharedPrefsRepository.saveProfileImage(data?.data.toString())
        }
    }

    private fun navigateToSignIn() {
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.sign_out_title))
                .setNegativeButton(resources.getString(R.string.sign_out_button_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.sign_out_button_ok)) { _, _ ->
                    SharedPrefsRepository.clearSharedPrefs()

                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .show()
        }
    }

    companion object {
        private const val REQUEST_PROFILE_IMAGE = 1233

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}