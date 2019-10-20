package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pedromoniz.blissylisty.MainActivity
import com.pedromoniz.blissylisty.R
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListAdapter
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListViewModel
import kotlinx.android.synthetic.main.questions_list_fragment.*
import kotlinx.android.synthetic.main.questions_paged_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

//The paged version works but was not fully tested and implemented(edge cases were dismissed and data is not being invalidated)
//In order to use it just go to the loading viewmodel and change the direction to this fragment

class QuestionsPagedListFragment : Fragment(R.layout.questions_paged_list_fragment) {

    private val viewModel: QuestionsPagedListViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.questions_list_menu, menu)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setFilter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                //todo, this could be improved with a help of a timer so it does not spam requests

                if (newText.isEmpty())
                    viewModel.setFilter(null)
                else
                    viewModel.setFilter(newText)

                return false
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel.navDirection.observe(this, Observer { directions ->
            findNavController().navigate(directions)
        })
        viewModel.failure.observe(this, Observer { failure ->
            handleFailure(failure)
        })

        setupListAdapter()
    }

    private fun setupListAdapter() {
        val adapter = QuestionsPagedListAdapter(viewModel)
        questionsPagedListFragmentRecyclerView.adapter = adapter

        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        viewModel.questions.observe(this, Observer(adapter::submitList))
    }


    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> return//TODO()
            is Failure.ServerError -> return//TODO()
            null -> return //TODO()
        }
    }
}
