package com.pedromoniz.blissylisty.data.sources.local.question

import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity


class QuestionLocalSource (
    private val questionDao: QuestionDao
) : QuestionSources {
    override suspend fun fetchQuestion(id: String): QuestionEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateQuestion(id: String, question: QuestionEntity): QuestionEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchQuestions(offset: String, filter: String?): List<QuestionEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun checkServerAvailability(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}