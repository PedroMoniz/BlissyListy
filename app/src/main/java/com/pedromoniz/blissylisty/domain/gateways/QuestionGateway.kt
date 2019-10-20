package com.pedromoniz.blissylisty.domain.gateways

import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.useCases.GetQuestionsUseCase

interface QuestionGateway {
    suspend fun CheckQuestionsServerAvailability(): Boolean
    suspend fun GetQuestions(request: GetQuestionsUseCase.GetQuestionsRequest): List<QuestionEntity>
    suspend fun UpdateQuestion(question: QuestionEntity): QuestionEntity
    suspend fun GetQuestion(id: String): QuestionEntity

}