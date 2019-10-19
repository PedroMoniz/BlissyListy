package com.pedromoniz.blissylisty.data.sources

interface QuestionSources {
    suspend fun CheckServerAvailability(): Boolean
}