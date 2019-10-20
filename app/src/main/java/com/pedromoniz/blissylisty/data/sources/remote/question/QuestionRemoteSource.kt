package com.pedromoniz.blissylisty.data.sources.remote.question

import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.data.sources.remote.BlissyAPI
import com.pedromoniz.blissylisty.data.sources.remote.NetworkException
import com.pedromoniz.blissylisty.data.sources.remote.models.ChoiceRemoteModel
import com.pedromoniz.blissylisty.data.sources.remote.models.StatusRemoteModel
import com.pedromoniz.blissylisty.data.sources.remote.models.QuestionRemoteModel
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import retrofit2.HttpException

class QuestionRemoteSource(
    private val blissyAPI: BlissyAPI
) : QuestionSources {
    override suspend fun share(email: String, url: String): Boolean =
        request(
            {
                blissyAPI.share(email,url)
            },
            { it.toBoolean() },
            StatusRemoteModel.empty()
        )

    override suspend fun checkServerAvailability(): Boolean =
        request(
            {
                blissyAPI.checkHealthStatus()
            },
            { it.toBoolean() },
            StatusRemoteModel.empty()
        )

    override suspend fun fetchQuestions(offset: String, filter: String?): List<QuestionEntity> =
        request(
            {
                blissyAPI.fetchQuestions("10", offset, filter)
            },
            { it.map { value: QuestionRemoteModel -> value.toQuestion() } },
            emptyList()
        )

    override suspend fun fetchQuestion(id: String): QuestionEntity = request(
        {
            blissyAPI.fetchQuestion(id)
        },
        { it.toQuestion() },
        QuestionRemoteModel.empty()
    )

    override suspend fun updateQuestion(id: String, question: QuestionEntity): QuestionEntity = request(
        {
            val questionRemoteModel = QuestionRemoteModel(
                question.id,
                question.question,
                question.image_url,
                question.thumb_url,
                question.published_at,
                question.choices.map { choiceEntity ->
                    ChoiceRemoteModel(choiceEntity.choice, choiceEntity.votes)

                })
            blissyAPI.updateQuestion(id,questionRemoteModel)
        },
        { it.toQuestion() },
        QuestionRemoteModel.empty()
    )

    private suspend fun <T, F, R : T> request(
        response: suspend () -> R,
        transform: (T) -> F,
        default: T
    ): F {
        return try {
            transform(
                response() ?: default
            )
        } catch (ex: Exception) {
            when (ex) {
                is HttpException -> {
                    //todo, If I needed to handle the response body of the error:
//                    val converter = provideRetrofit()
//                        .responseBodyConverter<Error>(
//                            Error::class.java, arrayOfNulls<Annotation>(0)
//                        )
//                    val error: Error? = converter.convert(ex.response()!!.errorBody()!!)
//                    error?.let { throw ServerStatusException(it) }
                    throw NetworkException()
                }
                else -> throw NetworkException()
            }
        }
    }
}
