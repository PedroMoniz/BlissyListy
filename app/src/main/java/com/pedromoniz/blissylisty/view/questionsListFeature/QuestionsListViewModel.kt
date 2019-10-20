package com.pedromoniz.blissylisty.view.questionsListFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.useCases.GetQuestionsUseCase
import com.pedromoniz.blissylisty.domain.useCases.ShareQuestionSearchQueryUseCase

class QuestionsListViewModel(
    val getQuestionsUseCase: GetQuestionsUseCase,
    val shareQuestionSearchQueryUseCase: ShareQuestionSearchQueryUseCase
) : BaseViewModel() {


    private val _filter = MutableLiveData<String?>().apply { value = null }
    val filter: LiveData<String?> = _filter


    //todo, experiment with PagedList. Still have to investigate how I would use pagedList with a Clean Arquitecture
    //From what I investigated, they seem to have to be integrated directly and they handle everything directly.

    private val _questions = MutableLiveData<List<QuestionEntity>>().apply { value = emptyList() }
    val questions: LiveData<List<QuestionEntity>> = _questions


    fun loadQuestions(offset:Int=0,filter:String? = null) {
        getQuestionsUseCase(viewModelScope, GetQuestionsUseCase.GetQuestionsRequest(offset,filter)) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(questions: List<QuestionEntity>) {
        _questions.value = questions
    }

    fun onQuestionSelected(questionId: String) {
        handleNavigation(QuestionsListFragmentDirections.actionQuestionsListFragmentToQuestionFragment(questionId))
    }

    fun setFilter(query: String?) {
        _filter.value = query
        loadQuestions(filter = query)
    }
}
