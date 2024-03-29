package com.pedromoniz.blissylisty.data.sources.remote

import com.pedromoniz.blissylisty.data.sources.remote.models.StatusRemoteModel
import com.pedromoniz.blissylisty.data.sources.remote.models.QuestionRemoteModel
import retrofit2.http.*

interface BlissyAPI {
    companion object {
        private const val HEALTH = "health"
        private const val PARAM_LIMIT = "limit"
        private const val PARAM_OFFSET = "offset"
        private const val PARAM_FILTER = "filter"
        private const val QUESTIONS_LIST = "questions"
        private const val QUESTION = "questions/{id}"
        private const val SHARE = "share"
        private const val PARAM_EMAIL = "destination_email"
        private const val PARAM_URL = "content_url"
    }

    @Headers("Content-Type: application/json")
    @GET(HEALTH)
    suspend fun checkHealthStatus(): StatusRemoteModel


    @Headers("Content-Type: application/json")
    @POST(SHARE)
    suspend fun share(
        @Query(PARAM_EMAIL) email: String,
        @Query(PARAM_URL) content: String
    ): StatusRemoteModel

    @Headers("Content-Type: application/json")
    @GET(QUESTIONS_LIST)
    suspend fun fetchQuestions(
        @Query(PARAM_LIMIT) limit: String,
        @Query(PARAM_OFFSET) offset: String,
        @Query(PARAM_FILTER) filter: String?
    ): List<QuestionRemoteModel>

    @Headers("Content-Type: application/json")
    @GET(QUESTION)
    suspend fun fetchQuestion(
        @Path("id") id: String
    ): QuestionRemoteModel

    @Headers("Content-Type: application/json")
    @PUT(QUESTION)
    suspend fun updateQuestion(
        @Path("id") id: String,
        @Body question: QuestionRemoteModel
    ): QuestionRemoteModel
}