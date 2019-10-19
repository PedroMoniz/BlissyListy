package com.pedromoniz.blissylisty.view.questionFeature

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pedromoniz.blissylisty.R
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionFragment : Fragment() {

    private val viewModel: QuestionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}
