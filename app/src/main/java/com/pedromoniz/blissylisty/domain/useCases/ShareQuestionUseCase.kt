package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway
import com.pedromoniz.blissylisty.Utils.UseCase.NoParams


class ShareQuestionUseCase(private val gateway: QuestionGateway) :
    UseCase<Boolean, NoParams>() {

    override suspend fun run(params: NoParams): Either<Failure, Boolean> {
//        return try {
//            val data = gateway.dummyCall()
//            return if (data.isValid()) {
//                Either.Right(data)
//            } else {
//                Either.Left(ShareQuestionFailure)
//            }
//        }
//        catch (exp: Exception) {
//            Either.Left(Failure.NetworkConnection)
//        }
        return Either.Left(Failure.NetworkConnection)
    }

    object ShareQuestionFailure : Failure.FeatureFailure()
}