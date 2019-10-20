package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class AnswerAQuestionUseCase(private val gateway: QuestionGateway) :
    UseCase<QuestionEntity, AnswerAQuestionUseCase.voteRequest>() {

    override suspend fun run(params: voteRequest): Either<Failure, QuestionEntity> {
        return try {
            //this one is strange, we are voting but not really

            params.question.choices.forEach { it.choice = "0" }
            params.question.choices[params.vote].choice = "1"

            val question = gateway.UpdateQuestion(params.question)
            return if (question.isValid()) {
                Either.Right(question)
            } else {
                Either.Left(AnswerAQuestionFailure)
            }
        } catch (exp: Exception) {
            Either.Left(AnswerAQuestionFailure)
        }
    }

    object AnswerAQuestionFailure : Failure.FeatureFailure()

    data class voteRequest(val vote: Int, val question: QuestionEntity)
}
