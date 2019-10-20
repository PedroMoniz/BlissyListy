package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway
import com.pedromoniz.blissylisty.Utils.UseCase.NoParams


class ShareQuestionUseCase(private val gateway: QuestionGateway) :
    UseCase<Boolean, ShareQuestionUseCase.shareQuestionRequest>() {

    override suspend fun run(params: shareQuestionRequest): Either<Failure, Boolean> {
        return try {

            val url :String = "blissrecruitment://questions?question_id="
            url.plus(params.id)

            val isAvailable = gateway.Share(params.email, url)
            return if (isAvailable) {
                Either.Right(isAvailable)
            } else {
                Either.Left(ShareQuestionFailure)
            }
        } catch (exp: Exception) {
            Either.Left(ShareQuestionFailure)
        }
    }

    object ShareQuestionFailure : Failure.FeatureFailure()

    data class shareQuestionRequest(val email: String, val id: String)
}