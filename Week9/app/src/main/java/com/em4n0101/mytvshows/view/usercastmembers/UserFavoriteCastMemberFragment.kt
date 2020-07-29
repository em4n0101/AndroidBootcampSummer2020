package com.em4n0101.mytvshows.view.usercastmembers

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.app.MyTvShowsApplication
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.view.cast.CastMemberActivity
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.viewmodel.usercastmember.UserFavoriteCastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.usercastmember.UserFavoriteCastMemberViewModelFactory
import kotlinx.android.synthetic.main.fragment_user_favorite_cast_member.*

class UserFavoriteCastMemberFragment : Fragment() {
    private lateinit var viewModel: UserFavoriteCastMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_user_favorite_cast_member,
            container,
            false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            // get view model
            viewModel = ViewModelProvider(
                this,
                UserFavoriteCastMemberViewModelFactory(MyTvShowsApplication.showsRepository)
            )
                .get(UserFavoriteCastMemberViewModel::class.java)
            // setup recycler
            if (it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                favoriteCastMembersRecyclerView.layoutManager = GridLayoutManager(it, 2)
            } else {
                favoriteCastMembersRecyclerView.layoutManager = GridLayoutManager(it, 4)
            }

            addGetFavoriteCastMembersObservable()
        }
    }

    private fun addGetFavoriteCastMembersObservable() {
        val observer = Observer<List<Person>> {
            if (it != null) {
                updateUiWithCastMembersList(it)
            }
        }
        viewModel.getPersons().observe(viewLifecycleOwner, observer)
    }

    private fun updateUiWithCastMembersList(listOfCastMembers: List<Person>) {
        emptyActorsTextView.visibility = if (listOfCastMembers.isEmpty()) View.VISIBLE else View.GONE
        if (favoriteCastMembersRecyclerView != null) {
            val adapter = CastMemberAdapter(::listItemPressed)
            adapter.setData(listOfCastMembers)
            favoriteCastMembersRecyclerView.adapter = adapter
        }
    }

    private fun listItemPressed(person: Person) {
        view?.let {
            val intent = Intent(context, CastMemberActivity::class.java)
            intent.putExtra(ShowDetailActivity.EXTRA_PERSON, person)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): UserFavoriteCastMemberFragment {
            return UserFavoriteCastMemberFragment()
        }
    }
}