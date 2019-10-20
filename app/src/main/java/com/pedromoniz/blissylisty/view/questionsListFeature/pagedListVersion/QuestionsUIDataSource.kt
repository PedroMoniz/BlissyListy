package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

import androidx.annotation.MainThread
import androidx.paging.PositionalDataSource
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity

class QuestionsUIDataSource(
    private val dataSourceDelegate: DataSourceDelegate<QuestionEntity>
) : PositionalDataSource<QuestionEntity>() {

    @MainThread
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<QuestionEntity>) {
        dataSourceDelegate.requestPageData(
            params.requestedStartPosition,
            params.requestedLoadSize
        ) {
            callback.onResult(it, 0)
        }
    }

    @MainThread
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<QuestionEntity>) {
        dataSourceDelegate.requestPageData(params.startPosition, params.loadSize) {
            callback.onResult(it)
        }
    }
}