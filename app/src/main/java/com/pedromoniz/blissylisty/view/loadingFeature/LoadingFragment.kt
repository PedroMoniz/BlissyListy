package com.pedromoniz.blissylisty.view.loadingFeature

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.domain.useCases.CheckServerHealthStatusUseCase
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoadingFragment : Fragment(com.pedromoniz.blissylisty.R.layout.loading_fragment) {

    private val viewModel: LoadingViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navDirection.observe(this, Observer { directions ->
            findNavController().navigate(directions)
        })
        viewModel.failure.observe(this, Observer { failure ->
            handleFailure(failure)
        })

        viewModel.checkServerStatus()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is CheckServerHealthStatusUseCase.CheckServerHealthStatusFailure -> showTryAgainDialog()
            is Failure.NetworkConnection -> return//TODO()
            is Failure.ServerError -> return//TODO()
            null -> return //TODO()
        }
    }

    private fun showTryAgainDialog() {
        AlertDialog.Builder(context)
            .setTitle("Try again")
            .setMessage("Server health was not OK")
            .setPositiveButton(
                "Re Check"
            ) { _, _ ->
                viewModel.checkServerStatus()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}


