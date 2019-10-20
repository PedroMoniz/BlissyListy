package com.pedromoniz.blissylisty.data.sources

import com.pedromoniz.blissylisty.domain.entities.QuestionEntity

interface QuestionSources {
    suspend fun CheckServerAvailability(): Boolean
    suspend fun FetchQuestions(offset: String, filter: String?):List<QuestionEntity>
}