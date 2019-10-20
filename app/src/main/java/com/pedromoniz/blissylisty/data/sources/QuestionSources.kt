package com.pedromoniz.blissylisty.data.sources

import com.pedromoniz.blissylisty.domain.entities.QuestionEntity

interface QuestionSources {
    suspend fun checkServerAvailability(): Boolean
    suspend fun fetchQuestions(offset: String, filter: String?):List<QuestionEntity>
    suspend fun fetchQuestion(id: String): QuestionEntity
    suspend fun updateQuestion(id: String, question: QuestionEntity): QuestionEntity
    suspend fun share(email: String, url: String): Boolean
}