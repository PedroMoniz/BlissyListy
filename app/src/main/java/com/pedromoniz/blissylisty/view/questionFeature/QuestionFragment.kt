package com.pedromoniz.blissylisty.view.questionFeature

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
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
        viewModel.loadQuestion(args.questionId)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
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
