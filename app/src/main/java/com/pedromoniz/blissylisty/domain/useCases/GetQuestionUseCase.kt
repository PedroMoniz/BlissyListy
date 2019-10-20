package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class GetQuestionUseCase(private val gateway: QuestionGateway) :
    UseCase<QuestionEntity, String>() {

    override suspend fun run(params: String): Either<Failure, QuestionEntity> {
        return try {
            val question = gateway.GetQuestion(params)
            return if (question.isValid()) {
                Either.Right(question)
            } else {
                Either.Left(GetQuestionFailure)
            }
        } catch (exp: Exception) {
            Either.Left(GetQuestionFailure)
        }
    }

    object GetQuestionFailure : Failure.FeatureFailure()
}