package com.pedromoniz.blissylisty.data.sources.remote.question

import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.data.sources.remote.BlissyAPI
import com.pedromoniz.blissylisty.data.sources.remote.NetworkException
import com.pedromoniz.blissylisty.data.sources.remote.models.HealthStatusRemoteModel
import com.pedromoniz.blissylisty.data.sources.remote.models.QuestionRemoteModel
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import retrofit2.HttpException

class QuestionRemoteSource(
    private val blissyAPI: BlissyAPI
) : QuestionSources {


    override suspend fun CheckServerAvailability(): Boolean =
        request(
            {
                blissyAPI.checkHealthStatus()
            },
            { it.toBoolean() },
            HealthStatusRemoteModel.empty()
        )

    override suspend fun FetchQuestions(offset: String, filter: String?): List<QuestionEntity> =
        request(
            {
                blissyAPI.fetchQuestions("10", offset, filter)
            },
            { it.map { value: QuestionRemoteModel -> value.toQuestion() } },
            emptyList()
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
