package com.pedromoniz.blissylisty.view.questionFeature

import androidx.lifecycle.ViewModel
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.useCases.*

class QuestionViewModel(
    val getQuestionUseCase: GetQuestionUseCase,
    val shareQuestionUseCase: ShareQuestionUseCase,
    val answerAQuestionUseCase: AnswerAQuestionUseCase
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}
