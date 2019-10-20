package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class GetQuestionsUseCase(private val gateway: QuestionGateway) :
    UseCase<List<QuestionEntity>, GetQuestionsUseCase.GetQuestionsRequest>() {

    override suspend fun run(params: GetQuestionsRequest): Either<Failure, List<QuestionEntity>> {
        return try {
            val questionsList = gateway.GetQuestions(params)

            //List could have been filtered so to allow the app to work even when some data is invalid

            return if (questionsList.all { it.isValid() }) {
                Either.Right(questionsList)
            } else {
                Either.Left(GetQuestionsFailure)
            }
        } catch (exp: Exception) {
            Either.Left(GetQuestionsFailure)
        }
    }

    object GetQuestionsFailure : Failure.FeatureFailure()

    data class GetQuestionsRequest(val offset: Int, val filter: String? = null)
}