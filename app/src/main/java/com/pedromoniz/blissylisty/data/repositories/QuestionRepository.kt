package com.pedromoniz.blissylisty.data.repositories

import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway
import com.pedromoniz.blissylisty.domain.useCases.GetQuestionsUseCase
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


class QuestionRepository(
    private val questionLocalSources: QuestionSources,
    private val questionRemoteSources: QuestionSources
) : QuestionGateway {


    private var cachedQuestions: ConcurrentMap<String, QuestionEntity>? = ConcurrentHashMap()

    private var cachedKeyedQuestions: ConcurrentMap<String, QuestionEntity>? = ConcurrentHashMap()

    override suspend fun Share(email: String, url: String): Boolean {
        try {
            return questionRemoteSources.share(email,url)
        } catch (e: Exception) {
            throw Exception()
        }
    }

    override suspend fun CheckQuestionsServerAvailability(): Boolean {
        try {
            return questionRemoteSources.checkServerAvailability()
        } catch (e: Exception) {
            throw Exception()
        }
    }

    override suspend fun GetQuestions(request: GetQuestionsUseCase.GetQuestionsRequest): List<QuestionEntity> {
        try {

            //todo, not setting up room for time purposes, files were created all we needed was create local models and update the databases
            //todo, there is no real strategy for when to fetch and when to get, so we always fetch

            if (request.filter.isNullOrEmpty()) {
                val fetchedList =
                    questionRemoteSources.fetchQuestions(request.offset.toString(), request.filter)

                fetchedList.forEach {
                    cachedQuestions?.put(it.id, it)
                }
                cachedQuestions?.let { questions ->
                    return questions.values.toList()
                }
            } else {
                if (request.offset == 0)
                    cachedKeyedQuestions?.clear()

                val fetchedList =
                    questionRemoteSources.fetchQuestions(request.offset.toString(), request.filter)

                fetchedList.forEach {
                    cachedKeyedQuestions?.put(it.id, it)
                }
                cachedKeyedQuestions?.let { questions ->
                    return questions.values.toList()
                }
            }

            return emptyList()

        } catch (e: Exception) {
            throw Exception()
        }
    }

    override suspend fun GetQuestion(id: String): QuestionEntity {
        try {
            return questionRemoteSources.fetchQuestion(id)
        } catch (e: Exception) {
            throw Exception()
        }
    }

    override suspend fun UpdateQuestion(question: QuestionEntity): QuestionEntity {
        try {
            return questionRemoteSources.updateQuestion(question.id, question)
        } catch (e: Exception) {
            throw Exception()
        }
    }
}
