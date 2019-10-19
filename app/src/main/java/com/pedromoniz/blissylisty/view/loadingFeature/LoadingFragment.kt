package com.pedromoniz.blissylisty.view.loadingFeature

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pedromoniz.blissylisty.R
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadingFragment : Fragment(R.layout.loading_fragment) {

    private val viewModel: LoadingViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}


