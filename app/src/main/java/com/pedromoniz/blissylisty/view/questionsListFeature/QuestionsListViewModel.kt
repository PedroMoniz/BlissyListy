package com.pedromoniz.blissylisty.view.questionsListFeature

import androidx.lifecycle.ViewModel
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.useCases.FetchQuestionsUseCase
import com.pedromoniz.blissylisty.domain.useCases.ShareQuestionSearchQueryUseCase

class QuestionsListViewModel(
    val fetchQuestionsUseCase: FetchQuestionsUseCase,
    val shareQuestionSearchQueryUseCase: ShareQuestionSearchQueryUseCase
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}
