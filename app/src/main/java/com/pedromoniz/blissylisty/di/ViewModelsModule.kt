package com.pedromoniz.blissylisty.di

import com.pedromoniz.blissylisty.view.loadingFeature.LoadingViewModel
import com.pedromoniz.blissylisty.view.questionFeature.QuestionViewModel
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { LoadingViewModel(get()) }
    viewModel { QuestionViewModel(get(),get(),get()) }
    viewModel { QuestionsListViewModel(get(),get()) }
}