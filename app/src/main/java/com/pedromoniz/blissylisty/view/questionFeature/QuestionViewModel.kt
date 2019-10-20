package com.pedromoniz.blissylisty.view.questionFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.useCases.*

class QuestionViewModel(
    val getQuestionUseCase: GetQuestionUseCase,
    val shareQuestionUseCase: ShareQuestionUseCase,
    val answerAQuestionUseCase: AnswerAQuestionUseCase
) : BaseViewModel() {

    private val _question = MutableLiveData<QuestionEntity>().apply { value = null }
    val question: LiveData<QuestionEntity> = _question

    fun loadQuestion(questionID: String) {
        getQuestionUseCase(viewModelScope, questionID) {
            it.either(
                ::handleFailure,
                ::handleSuccessOnLoad
            )
        }
    }

    fun vote(choice: Int) {
        answerAQuestionUseCase(
            viewModelScope,
            AnswerAQuestionUseCase.voteRequest(choice, question.value!!)
        ) {
            it.either(
                ::handleFailure,
                ::handleSuccessOnAnswer
            )
        }
    }

    fun share(email: String) {
        shareQuestionUseCase(
            viewModelScope,
            ShareQuestionUseCase.shareQuestionRequest(email, question.value!!.id)
        ) {
            it.either(
                ::handleFailure,
                ::handleSuccessOnShare
            )
        }
    }

    private fun handleSuccessOnLoad(question: QuestionEntity) {
        _question.value = question
    }

    private fun handleSuccessOnAnswer(questionUpdated: QuestionEntity) {
        _question.value = questionUpdated
    }

    private fun handleSuccessOnShare(success: Boolean) {
    }
}
