package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.useCases.GetQuestionsUseCase
import com.pedromoniz.blissylisty.domain.useCases.ShareQuestionSearchQueryUseCase
import com.pedromoniz.blissylisty.view.questionsListFeature.QuestionsListFragmentDirections

class QuestionsPagedListViewModel(
    val getQuestionsUseCase: GetQuestionsUseCase,
    val shareQuestionSearchQueryUseCase: ShareQuestionSearchQueryUseCase
) : BaseViewModel(),
    DataSourceDelegate<QuestionEntity> {

    private val _filter = MutableLiveData<String?>().apply { value = null }
    val filter: LiveData<String?> = _filter
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val questionsUIDataSourceFactory: QuestionsUIDataSourceFactory =
        QuestionsUIDataSourceFactory(this)

    val questions: LiveData<PagedList<QuestionEntity>> =
        LivePagedListBuilder(questionsUIDataSourceFactory, pagedListConfig).build()

    override fun requestPageData(
        startPosition: Int,
        loadSize: Int,
        onResult: (List<QuestionEntity>) -> Unit
    ) {
        getQuestionsUseCase(
            viewModelScope,
            GetQuestionsUseCase.GetQuestionsRequest(startPosition, filter.value)
        ) {
            it.either(
                ::handleFailure
            )
            { list -> onResult(list) }
        }
    }

    fun onQuestionSelected(questionId: String) {
        handleNavigation(
            QuestionsPagedListFragmentDirections.actionQuestionsPagedListFragmentToQuestionFragment(
                questionId
            )
        )
    }

    fun setFilter(query: String?) {
        _filter.value = query

        //now we would have to invalidate the data source
    }
}
