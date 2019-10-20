package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class ShareQuestionSearchQueryUseCase(private val gateway: QuestionGateway) :
    UseCase<Boolean, ShareQuestionSearchQueryUseCase.shareQueryRequest>() {


    override suspend fun run(params: shareQueryRequest): Either<Failure, Boolean> {
        return try {

            if(params.filter.isNullOrEmpty())
                Either.Left(ShareQuestionSearchQueryFailure)

            val url :String = "blissrecruitment://questions?question_filter="
            url.plus(params.filter)

            val isAvailable = gateway.Share(params.email, url)
            return if (isAvailable) {
                Either.Right(isAvailable)
            } else {
                Either.Left(ShareQuestionSearchQueryFailure)
            }
        } catch (exp: Exception) {
            Either.Left(ShareQuestionSearchQueryFailure)
        }
    }

    object ShareQuestionSearchQueryFailure : Failure.FeatureFailure()


    data class shareQueryRequest(val email: String, val filter: String?)
}