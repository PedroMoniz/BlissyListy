package com.pedromoniz.blissylisty.view.questionFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pedromoniz.blissylisty.R
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.databinding.QuestionFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class QuestionFragment : Fragment() {

    private lateinit var viewDataBinding: QuestionFragmentBinding
    private val viewModel: QuestionViewModel by viewModel()

    private val args: QuestionFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.question_fragment, container, false)
        viewDataBinding = QuestionFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.loadQuestion(args.questionID)

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            //todo, this part may have to be improved when entering via a link
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.navDirection.observe(this, Observer { directions ->
            findNavController().navigate(directions)
        })
        viewModel.failure.observe(this, Observer { failure ->
            handleFailure(failure)
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
