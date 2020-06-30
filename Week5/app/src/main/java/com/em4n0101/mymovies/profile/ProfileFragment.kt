package com.em4n0101.mymovies.profile

import android.content.Intent
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
        activity?.title = getString(R.string.profile)
    }

    private fun showAbout() {
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.about_title))
                .setMessage(resources.getString(R.string.about_message))
                .show()
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
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}