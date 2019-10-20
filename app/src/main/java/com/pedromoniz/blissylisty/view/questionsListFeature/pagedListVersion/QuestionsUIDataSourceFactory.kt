package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity




class QuestionsUIDataSourceFactory(val delegate: DataSourceDelegate<QuestionEntity>) :
    DataSource.Factory<Int, QuestionEntity>() {


    private val mutableLiveData: MutableLiveData<QuestionsUIDataSource>? = null

    override fun create(): DataSource<Int, QuestionEntity> {
        val questionsUIDataSource = QuestionsUIDataSource(delegate)
        mutableLiveData?.postValue(questionsUIDataSource)
        return questionsUIDataSource
    }

}