package com.pedromoniz.blissylisty.view.questionsListFeature

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pedromoniz.blissylisty.MainActivity
import com.pedromoniz.blissylisty.R
import com.pedromoniz.blissylisty.Utils.Failure
import kotlinx.android.synthetic.main.questions_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel




class QuestionsListFragment : Fragment(R.layout.questions_list_fragment) {

    private val viewModel: QuestionsListViewModel by viewModel()

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
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_ALWAYS)
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.share -> showShareDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showShareDialog() {
        val txtEmail = EditText(context)
        txtEmail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        AlertDialog.Builder(context)
            .setView(txtEmail)
            .setTitle("Share")
            .setMessage("Enter your email")
            .setPositiveButton(
                "Send"
            ) { _, _ ->
                viewModel.share(txtEmail.text.toString())
            }
            .setIcon(android.R.drawable.ic_menu_send)
            .show()
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
        viewModel.loadQuestions(filter = viewModel.filter.value)
    }

    private fun setupListAdapter() {
        val adapter = QuestionsListAdapter(viewModel)
        questionsListFragmentRecyclerView.adapter = adapter
        questionsListFragmentRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {

                    //todo, add some logic so it is called once. the logic could very well be on the side of the viewmodel loadquestions method

                    viewModel.loadQuestions(
                        viewModel.questions.value!!.size,
                        viewModel.filter.value
                    )
                }
            }
        })
        viewModel.questions.observe(this, Observer {
            adapter.setData(if (it.isNullOrEmpty()) emptyList() else it.toList())
        })
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> return//TODO()
            is Failure.ServerError -> return//TODO()
            null -> return //TODO()
        }
    }
}
