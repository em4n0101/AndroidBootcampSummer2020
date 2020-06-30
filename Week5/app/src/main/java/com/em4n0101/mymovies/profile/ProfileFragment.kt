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
        activity?.title = getString(R.string.profile)
    }

    private fun navigateToSignIn() {
        SharedPrefsRepository.clearSharedPrefs()

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}