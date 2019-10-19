package com.pedromoniz.blissylisty.domain.gateways

interface QuestionGateway {
    suspend fun CheckQuestionsServerAvailability(): Boolean

}